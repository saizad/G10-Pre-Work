package sa.zad.pre_work_g10.models;

import android.support.annotation.Nullable;
import sa.zad.pre_work_g10.UserValidation;

public class User {

    private final String username;
    private final String password;
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;

    public User() {
        this(null, null);
    }

    public User(@Nullable String username, @Nullable String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserValidation getValidation(){
        return new UserValidation(this);
    }

    public void setImgurAuth(String accessToken, String refreshToken, Long expiresIn){
        setAccessToken(accessToken);
        setRefreshToken(refreshToken);
        setExpiresIn(expiresIn);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
