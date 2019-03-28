package sa.zad.pre_work_g10.models;

import com.google.gson.annotations.SerializedName;

public class CommentBody {

    @SerializedName("comment") final private String comment;
    @SerializedName("image_id") final private String imageId;

    public CommentBody(String comment, String imageId) {
        this.comment = comment;
        this.imageId = imageId;
    }
}
