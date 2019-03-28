package sa.zad.pre_work_g10;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import sa.zad.easyretrofit.Utils;

public abstract class BaseActivity extends AppCompatActivity {

    protected ApiService apiService;
    protected UserDB userDB;

    private PublishSubject<ActivityResult> activityResultPublishSubject = PublishSubject.create();

    public static Intent getActivityIntent(Class<?> activity, Context context) {
        return new Intent(context, activity);
    }

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDB = PreWorkG10Application.getInstance().getUserDB();
        apiService = PreWorkG10Application.getInstance().service();
        if(Utils.isNotNull(getSupportActionBar()))
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case  R.id.logout_menu:
                userDB.logout();
                Intent intent = getActivityIntent(AuthActivity.class, this);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void log(String logText) {
        log(this.getLocalClassName(), logText);
    }

    public void log(String tag, String logText) {
        Log.d(this.getLocalClassName(), logText);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        activityResultPublishSubject.onNext(new ActivityResult(requestCode, resultCode, data));
    }

    protected Observable<Intent> result(int requestCode) {
        return activityResultPublishSubject.filter(activityResult ->
                activityResult.resultCode == RESULT_OK
                        && activityResult.requestCode == requestCode
                        && Utils.isNotNull(activityResult.data))
                .map(activityResult -> activityResult.data);
    }

    public void toast(String toastText) {
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }

    public static class ActivityResult {

        public final int requestCode;
        public final int resultCode;
        @Nullable
        public final Intent data;

        ActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            this.requestCode = requestCode;
            this.resultCode = resultCode;
            this.data = data;
        }
    }


}
