package sa.zad.pre_work_g10;

import android.support.annotation.Nullable;
import sa.zad.pre_work_g10.models.User;

public class UserValidation {

    private final User user;

    public UserValidation(User user) {
        this.user = user;
    }

    public boolean isUserValid(){
        return isUsernameValid() && isPasswordValid();
    }

    public boolean isUsernameValid(){
        return !Utils.isNullOrEmpty(user.getUsername());
    }

    public boolean isPasswordValid(){
        return !Utils.isNullOrEmpty(user.getPassword());
    }

    /**
     *
     * @return error message or null if there is no error
     */
    @Nullable
    public String getError(){
        if(!isUsernameValid() && !isPasswordValid()){
            return "username and password is invalid";
        }else if(!isUsernameValid()){
            return "username is invalid";
        }else if(!isPasswordValid()){
            return "password is invalid";
        }
        return null;
    }
}
