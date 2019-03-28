package sa.zad.pre_work_g10;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.gson.Gson;
import sa.zad.pre_work_g10.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sa-ZAD on 2017-11-21.
 */

public class UserDB extends AppSharedPreferences {

    private static final String KEY_STAY_LOGGED_IN_USER = "stay_logged_in_user";
    private static final String KEY_USERS = "users_list";
    private static final String KEY_CURRENT_USER = "current_user";
    private final UserList userList;

    public UserDB(SharedPreferences sharedPreferences, Gson gson) {
        super(sharedPreferences, gson);
        userList = getSharedPrefObject(KEY_USERS, UserList.class, new UserList());
    }

    public boolean isUserLoggedIn(){
        final User currentUser = getLoggedInUser();
        return Utils.isNotNull(currentUser) && currentUser.getValidation().isUserValid() && isUserValid(currentUser);
    }

    public User getLoggedInUser(){
        return getSharedPrefObject(KEY_STAY_LOGGED_IN_USER, User.class);
    }

    public User getCurrentUser(){
        return getSharedPrefObject(KEY_CURRENT_USER, User.class);
    }

    public void addUser(@NonNull User user) throws Exception {
        addUser(user, false);
    }

    public void addUser(@NonNull User user, boolean stayLoggedIn) throws Exception {
        if (Utils.isNull(getExistingUser(user))) {
            userList.users.add(user);
            putSharedPrefObject(KEY_USERS, userList);
            setCurrentUser(user);
            if (stayLoggedIn) {
                setStayLoggedInUser(user);
            }
            return;
        }
        throw new Exception("User already exists");
    }

    public boolean login(@NonNull User user) {
        return login(user, false);
    }

    public boolean login(@NonNull User user, boolean stayLoggedIn) {
        boolean valid = isUserValid(user);
        if (valid) {
            setCurrentUser(user);
        }
        if (valid && stayLoggedIn) {
            setStayLoggedInUser(user);
        }
        return valid;
    }

    public boolean isUserValid(@NonNull User user){
        final User foundUser = getExistingUser(user);
        return Utils.isNotNull(foundUser) && foundUser.getPassword().equals(user.getPassword());
    }

    @Nullable
    public User getExistingUser(@NonNull User user) {
        final int userIndex = existingUserIndex(user);
        if(userIndex >= 0){
            return userList.users.get(userIndex);
        }
        return null;
    }

    public void logout() {
        setStayLoggedInUser(null);
        setCurrentUser(null);
    }

    private void setStayLoggedInUser(@Nullable User user) {
        putSharedPrefObject(KEY_STAY_LOGGED_IN_USER, user);
    }

    private void setCurrentUser(@Nullable User user) {
        putSharedPrefObject(KEY_CURRENT_USER, user);
    }

    private static class UserList {
        private List<User> users = new ArrayList<>();
    }

    public void updateUser(@NonNull User user){
        setCurrentUser(user);
        if(isUserLoggedIn()){
            setStayLoggedInUser(user);
        }
        final int userIndex = existingUserIndex(user);
        userList.users.set(userIndex, user);
    }

    public int existingUserIndex(@NonNull User user){
        for (int i = 0; i < userList.users.size(); i++) {
            User indexUser = userList.users.get(i);
            if(indexUser.getUsername().equalsIgnoreCase(user.getUsername())){
                return i;
            }
        }
        return -1;
    }
}
