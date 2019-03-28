package sa.zad.pre_work_g10.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Comments {

    @SerializedName("data")
    public List<Children> data;
    @SerializedName("success")
    public boolean success;
    @SerializedName("status")
    public int status;

    public static class Children {
        @SerializedName("id")
        public int id;
        @SerializedName("image_id")
        public String imageId;
        @SerializedName("comment")
        public String comment;
        @SerializedName("author")
        public String author;
        @SerializedName("author_id")
        public int authorId;
        @SerializedName("on_album")
        public boolean onAlbum;
        @SerializedName("album_cover")
        public String albumCover;
        @SerializedName("ups")
        public int ups;
        @SerializedName("downs")
        public int downs;
        @SerializedName("points")
        public int points;
        @SerializedName("datetime")
        public int datetime;
        @SerializedName("parent_id")
        public int parentId;
        @SerializedName("deleted")
        public boolean deleted;
        @SerializedName("vote")
        public String vote;
        @SerializedName("platform")
        public String platform;
        @SerializedName("children")
        public List<Children> children;
    }
}
