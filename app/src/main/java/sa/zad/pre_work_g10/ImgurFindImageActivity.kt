package sa.zad.pre_work_g10

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_imgur_find_image.*
import sa.zad.easyretrofit.Utils
import sa.zad.pre_work_g10.models.ErrorModel

class ImgurFindImageActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imgur_find_image)

        if (Utils.isNotNull(supportActionBar))
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        search_image.setOnClickListener {

            search_image.isEnabled = false
            searching.visibility = View.VISIBLE
            error.visibility = View.GONE

            apiService.galleryModel(image_hash.text.toString())
                .apiException({
                    showErrorMessage(it.data.error)
                }, ErrorModel::class.java)
                .exception {
                    showErrorMessage(it.message)
                }
                .doFinally {
                    search_image.isEnabled = true
                    searching.visibility = View.GONE
                }
                .subscribe{
                    startActivity(FoundImageActivity.getActivityIntent(it, this))
                }
        }
    }

    private fun showErrorMessage(errorMessage: String?){
        error.visibility = View.VISIBLE
        error.text = errorMessage
    }
}
