package sa.zad.pre_work_g10

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_found_image.*
import sa.zad.easyretrofit.CachePolicy
import sa.zad.pre_work_g10.models.ErrorModel
import sa.zad.pre_work_g10.models.GalleryModel


class FoundImageActivity : BaseActivity() {


    private var galleryModel: GalleryModel? = null

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_found_image)
        galleryModel = intent.getParcelableExtra(GALLERY_MODEL)
        title = galleryModel!!.data.title

        val mLayoutManager = LinearLayoutManager(applicationContext)
        comments.layoutManager = mLayoutManager

        Glide.with(this)
            .load(galleryModel!!.data.images[0].link)
            .into(imageView)


        fetchComments("best")

        add_comment.setOnClickListener {
            startActivityForResult(
                FoundImageActivity.getActivityIntent(galleryModel!!, this, CommentActivity::class.java),
                COMMENT_ACTIVITY_RESULT_CODE
            )
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == COMMENT_ACTIVITY_RESULT_CODE) {
            fetchComments("new")
            toast("Comment successfully added")
        }
    }

    @SuppressLint("CheckResult")
    private fun fetchComments(sort: String) {
        loading_comments.visibility = View.VISIBLE
        apiService.comments(galleryModel!!.data.id, sort, CachePolicy.SERVER_ONLY)
            .apiException({
                toast(it.data.error)
            }, ErrorModel::class.java)
            .exception {
                toast(it.message)
            }
            .doFinally {
                loading_comments.visibility = View.GONE
            }
            .subscribe {
                var data = it.data
                comments.adapter = CommentsAdapter(data)
            }
    }

    companion object {

        val GALLERY_MODEL = "gallery_model"
        private val COMMENT_ACTIVITY_RESULT_CODE = 1

        fun getActivityIntent(
            galleryModel: GalleryModel,
            context: Context,
            activityClass: Class<*> = FoundImageActivity::class.java
        ): Intent {
            return Intent(context, activityClass).putExtra(
                GALLERY_MODEL,
                galleryModel as Parcelable
            )
        }
    }
}
