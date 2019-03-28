package sa.zad.pre_work_g10;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class AppLaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_launch);
        if(userDB.isUserLoggedIn()){
            startActivity(getActivityIntent(ImgurFindImageActivity.class, this));
        }else {
            startActivity(getActivityIntent(AuthActivity.class, this));
        }
    }
}
