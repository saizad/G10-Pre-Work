package sa.zad.pre_work_g10

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_comment.*
import sa.zad.pre_work_g10.models.CommentBody
import sa.zad.pre_work_g10.models.ErrorModel
import sa.zad.pre_work_g10.models.GalleryModel
import sa.zad.pre_work_g10.models.User
import java.util.regex.Matcher
import java.util.regex.Pattern

@SuppressLint("SetJavaScriptEnabled")
class CommentActivity : BaseActivity() {

    private var mWebView: WebView? = null
    private val MAX_WORD_COUNT = 140
    private var dialog: Dialog? = null
    private var galleryModel: GalleryModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        dialog = Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        galleryModel = intent.getParcelableExtra(FoundImageActivity.GALLERY_MODEL)
        title = galleryModel!!.data.title

        comment_edittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                val remaining = MAX_WORD_COUNT - Utils.stripTrailingLeadingNewLines(s.toString()).length
                word_count.text = getString(R.string.word_count, remaining)
                submit_comment.isEnabled = remaining in 0..139
            }
        })

        submit_comment.setOnClickListener {
            submit_comment.isEnabled = false
            mWebView = WebView(this)
            setupWebView()
            mWebView!!.loadUrl("https://api.imgur.com/oauth2/authorize?client_id=" + BuildConfig.CLIENT_ID + "&response_type=token")
            mWebView!!.settings.javaScriptEnabled = true
            val paramsWebView = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
            dialog!!.addContentView(mWebView, paramsWebView)
            dialog!!.show()


        }
    }

    private fun setupWebView() {
        val accessTokenPattern = Pattern.compile("access_token=([^&]*)")
        val refreshTokenPattern = Pattern.compile("refresh_token=([^&]*)")
        val expiresInPattern = Pattern.compile("expires_in=(\\d+)")
        mWebView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                var tokensURL = false

                if (request.url.host.equals("imgur.com", true)) {

                    val url = request.url.toString()
                    tokensURL = true
                    log(url)

                    var matcher: Matcher
                    matcher = refreshTokenPattern.matcher(url)
                    matcher.find()
                    val refreshToken = matcher.group(1)

                    matcher = accessTokenPattern.matcher(url)
                    matcher.find()
                    val accessToken = matcher.group(1)

                    matcher = expiresInPattern.matcher(url)
                    matcher.find()
                    val expiresIn = java.lang.Long.parseLong(matcher.group(1))
                    view.destroy()

                    val currentUser = userDB.currentUser
                    currentUser.setImgurAuth(accessToken, refreshToken, expiresIn)
                    userDB.updateUser(currentUser)
                    dialog!!.dismiss()
                    createComment(currentUser)
                }
                return tokensURL
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun createComment(user: User) {

        commenting.visibility = View.VISIBLE
        error_comment.visibility = View.GONE
        val commentBody =
            CommentBody(Utils.stripTrailingLeadingNewLines(comment_edittext.text.toString()), galleryModel!!.data.id)

        apiService.createComment(commentBody, "Bearer " + user.accessToken)
            .apiException({
                showErrorMessage(it.data.error)
            }, ErrorModel::class.java)
            .exception {
                showErrorMessage(it.message)
            }
            .doFinally {
                submit_comment.isEnabled = true
                commenting.visibility = View.GONE
            }
            .subscribe {
                setResult(Activity.RESULT_OK)
                finish()
            }
    }

    private fun showErrorMessage(errorMessage: String?) {
        error_comment.visibility = View.VISIBLE
        error_comment.text = errorMessage
    }
}
