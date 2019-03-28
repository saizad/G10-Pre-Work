package sa.zad.pre_work_g10.models;

import com.google.gson.annotations.SerializedName;

public class DataModel<M> {
    @SerializedName("data")
    public M data;
    @SerializedName("success")
    public boolean success;
    @SerializedName("status")
    public int status;
}
