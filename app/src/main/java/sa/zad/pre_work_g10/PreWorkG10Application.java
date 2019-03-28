package sa.zad.pre_work_g10;

import android.app.Application;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import sa.zad.easyretrofit.EasyRetrofit;

public class PreWorkG10Application extends Application {


    private static PreWorkG10Application INSTANCE;
    private ApiService service;
    private EasyRetrofit easyRetrofit;
    private UserDB userDB;

    public PreWorkG10Application() {
        INSTANCE = this;
    }

    public static PreWorkG10Application getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        }
        return new PreWorkG10Application();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        easyRetrofit = new EasyRetrofit(this);
        service = easyRetrofit.provideRetrofit().create(ApiService.class);
        userDB = new UserDB(PreferenceManager.getDefaultSharedPreferences(this), new Gson());
    }


    public ApiService service() {
        return service;
    }

    public UserDB getUserDB() {
        return userDB;
    }
}
