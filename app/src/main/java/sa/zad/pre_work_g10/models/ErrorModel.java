package sa.zad.pre_work_g10.models;

import com.google.gson.annotations.SerializedName;

public class ErrorModel {

    @SerializedName("data")
    public Data data;
    @SerializedName("success")
    public boolean success;
    @SerializedName("status")
    public int status;

    public static class Data {
        @SerializedName("error")
        public String error;
        @SerializedName("request")
        public String request;
        @SerializedName("method")
        public String method;
    }
}
