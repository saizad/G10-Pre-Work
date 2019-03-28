package sa.zad.pre_work_g10

import android.os.Bundle
import android.view.Menu
import kotlinx.android.synthetic.main.activity_auth.*
import sa.zad.pre_work_g10.models.User

class AuthActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        if(Utils.isNotNull(supportActionBar))
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        signup.setOnClickListener {
            val validatedFields = validateFields()
            if(validatedFields.validation.isUserValid){
                try {
                    userDB.addUser(validatedFields, stay_logged_in.isChecked)
                    launchSuccessActivity()
                } catch (e: Exception){
                    error.text = e.message
                }
            }
        }

        login.setOnClickListener {
            val validatedFields = validateFields()
            if(validatedFields.validation.isUserValid){
                if(!userDB.login(validatedFields, stay_logged_in.isChecked)){
                    error.text = "User not found"
                }else{
                    launchSuccessActivity()
                }
            }
        }
    }

    private fun validateFields(): User{
        val user = User(username.text.toString(), password.text.toString())
        if(!user.validation.isUserValid){
            error.text = user.validation.error
        }
        return user
    }

    private fun launchSuccessActivity(){
        startActivity(getActivityIntent(ImgurFindImageActivity::class.java, this))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }
}
