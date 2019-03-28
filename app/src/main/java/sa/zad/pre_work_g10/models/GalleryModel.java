package sa.zad.pre_work_g10.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GalleryModel implements Serializable, Parcelable {

    @SerializedName("data")
    public Data data;
    @SerializedName("success")
    public boolean success;
    @SerializedName("status")
    public int status;

    protected GalleryModel(Parcel in) {
        data = in.readParcelable(Data.class.getClassLoader());
        success = in.readByte() != 0;
        status = in.readInt();
    }

    public static final Creator<GalleryModel> CREATOR = new Creator<GalleryModel>() {
        @Override
        public GalleryModel createFromParcel(Parcel in) {
            return new GalleryModel(in);
        }

        @Override
        public GalleryModel[] newArray(int size) {
            return new GalleryModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(data, flags);
        dest.writeByte((byte) (success ? 1 : 0));
        dest.writeInt(status);
    }


    public static class Processing implements Serializable, Parcelable {
        public static final Creator<Processing> CREATOR = new Creator<Processing>() {
            @Override
            public Processing createFromParcel(Parcel in) {
                return new Processing(in);
            }

            @Override
            public Processing[] newArray(int size) {
                return new Processing[size];
            }
        };
        @SerializedName("status")
        public String status;

        protected Processing(Parcel in) {
            status = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(status);
        }

        @Override
        public int describeContents() {
            return 0;
        }
    }

    public static class Images implements Serializable, Parcelable {
        public static final Creator<Images> CREATOR = new Creator<Images>() {
            @Override
            public Images createFromParcel(Parcel in) {
                return new Images(in);
            }

            @Override
            public Images[] newArray(int size) {
                return new Images[size];
            }
        };
        @SerializedName("id")
        public String id;
        @SerializedName("title")
        public String title;
        @SerializedName("description")
        public String description;
        @SerializedName("datetime")
        public int datetime;
        @SerializedName("type")
        public String type;
        @SerializedName("animated")
        public boolean animated;
        @SerializedName("width")
        public int width;
        @SerializedName("height")
        public int height;
        @SerializedName("size")
        public int size;
        @SerializedName("views")
        public int views;
        @SerializedName("bandwidth")
        public String bandwidth;
        @SerializedName("vote")
        public String vote;
        @SerializedName("favorite")
        public boolean favorite;
        @SerializedName("nsfw")
        public String nsfw;
        @SerializedName("section")
        public String section;
        @SerializedName("account_url")
        public String accountUrl;
        @SerializedName("account_id")
        public String accountId;
        @SerializedName("is_ad")
        public boolean isAd;
        @SerializedName("in_most_viral")
        public boolean inMostViral;
        @SerializedName("has_sound")
        public boolean hasSound;
        @SerializedName("ad_type")
        public int adType;
        @SerializedName("ad_url")
        public String adUrl;
        @SerializedName("edited")
        public String edited;
        @SerializedName("in_gallery")
        public boolean inGallery;
        @SerializedName("link")
        public String link;
        @SerializedName("mp4_size")
        public int mp4Size;
        @SerializedName("mp4")
        public String mp4;
        @SerializedName("gifv")
        public String gifv;
        @SerializedName("hls")
        public String hls;
        @SerializedName("processing")
        public Processing processing;
        @SerializedName("comment_count")
        public String commentCount;
        @SerializedName("favorite_count")
        public String favoriteCount;
        @SerializedName("ups")
        public String ups;
        @SerializedName("downs")
        public String downs;
        @SerializedName("points")
        public String points;
        @SerializedName("score")
        public String score;

        protected Images(Parcel in) {
            id = in.readString();
            title = in.readString();
            description = in.readString();
            datetime = in.readInt();
            type = in.readString();
            animated = in.readByte() != 0;
            width = in.readInt();
            height = in.readInt();
            size = in.readInt();
            views = in.readInt();
            bandwidth = in.readString();
            vote = in.readString();
            favorite = in.readByte() != 0;
            nsfw = in.readString();
            section = in.readString();
            accountUrl = in.readString();
            accountId = in.readString();
            isAd = in.readByte() != 0;
            inMostViral = in.readByte() != 0;
            hasSound = in.readByte() != 0;
            adType = in.readInt();
            adUrl = in.readString();
            edited = in.readString();
            inGallery = in.readByte() != 0;
            link = in.readString();
            mp4Size = in.readInt();
            mp4 = in.readString();
            gifv = in.readString();
            hls = in.readString();
            commentCount = in.readString();
            favoriteCount = in.readString();
            ups = in.readString();
            downs = in.readString();
            points = in.readString();
            score = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(title);
            dest.writeString(description);
            dest.writeInt(datetime);
            dest.writeString(type);
            dest.writeByte((byte) (animated ? 1 : 0));
            dest.writeInt(width);
            dest.writeInt(height);
            dest.writeInt(size);
            dest.writeInt(views);
            dest.writeString(bandwidth);
            dest.writeString(vote);
            dest.writeByte((byte) (favorite ? 1 : 0));
            dest.writeString(nsfw);
            dest.writeString(section);
            dest.writeString(accountUrl);
            dest.writeString(accountId);
            dest.writeByte((byte) (isAd ? 1 : 0));
            dest.writeByte((byte) (inMostViral ? 1 : 0));
            dest.writeByte((byte) (hasSound ? 1 : 0));
            dest.writeInt(adType);
            dest.writeString(adUrl);
            dest.writeString(edited);
            dest.writeByte((byte) (inGallery ? 1 : 0));
            dest.writeString(link);
            dest.writeInt(mp4Size);
            dest.writeString(mp4);
            dest.writeString(gifv);
            dest.writeString(hls);
            dest.writeString(commentCount);
            dest.writeString(favoriteCount);
            dest.writeString(ups);
            dest.writeString(downs);
            dest.writeString(points);
            dest.writeString(score);
        }
    }


    public static class Data   implements Serializable, Parcelable{
        @SerializedName("id")
        public String id;
        @SerializedName("title")
        public String title;
        @SerializedName("description")
        public String description;
        @SerializedName("datetime")
        public int datetime;
        @SerializedName("cover")
        public String cover;
        @SerializedName("cover_width")
        public int coverWidth;
        @SerializedName("cover_height")
        public int coverHeight;
        @SerializedName("account_url")
        public String accountUrl;
        @SerializedName("account_id")
        public int accountId;
        @SerializedName("privacy")
        public String privacy;
        @SerializedName("layout")
        public String layout;
        @SerializedName("views")
        public int views;
        @SerializedName("link")
        public String link;
        @SerializedName("ups")
        public int ups;
        @SerializedName("downs")
        public int downs;
        @SerializedName("points")
        public int points;
        @SerializedName("score")
        public int score;
        @SerializedName("is_album")
        public boolean isAlbum;
        @SerializedName("vote")
        public String vote;
        @SerializedName("favorite")
        public boolean favorite;
        @SerializedName("nsfw")
        public boolean nsfw;
        @SerializedName("section")
        public String section;
        @SerializedName("comment_count")
        public int commentCount;
        @SerializedName("favorite_count")
        public int favoriteCount;
        @SerializedName("topic")
        public String topic;
        @SerializedName("topic_id")
        public int topicId;
        @SerializedName("images_count")
        public int imagesCount;
        @SerializedName("in_gallery")
        public boolean inGallery;
        @SerializedName("is_ad")
        public boolean isAd;
        @SerializedName("ad_type")
        public int adType;
        @SerializedName("ad_url")
        public String adUrl;
        @SerializedName("in_most_viral")
        public boolean inMostViral;
        @SerializedName("include_album_ads")
        public boolean includeAlbumAds;
        @SerializedName("images")
        public List<Images> images;

        protected Data(Parcel in) {
            id = in.readString();
            title = in.readString();
            description = in.readString();
            datetime = in.readInt();
            cover = in.readString();
            coverWidth = in.readInt();
            coverHeight = in.readInt();
            accountUrl = in.readString();
            accountId = in.readInt();
            privacy = in.readString();
            layout = in.readString();
            views = in.readInt();
            link = in.readString();
            ups = in.readInt();
            downs = in.readInt();
            points = in.readInt();
            score = in.readInt();
            isAlbum = in.readByte() != 0;
            vote = in.readString();
            favorite = in.readByte() != 0;
            nsfw = in.readByte() != 0;
            section = in.readString();
            commentCount = in.readInt();
            favoriteCount = in.readInt();
            topic = in.readString();
            topicId = in.readInt();
            imagesCount = in.readInt();
            inGallery = in.readByte() != 0;
            isAd = in.readByte() != 0;
            adType = in.readInt();
            adUrl = in.readString();
            inMostViral = in.readByte() != 0;
            includeAlbumAds = in.readByte() != 0;
            images = in.createTypedArrayList(Images.CREATOR);
        }

        public static final Creator<Data> CREATOR = new Creator<Data>() {
            @Override
            public Data createFromParcel(Parcel in) {
                return new Data(in);
            }

            @Override
            public Data[] newArray(int size) {
                return new Data[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(title);
            dest.writeString(description);
            dest.writeInt(datetime);
            dest.writeString(cover);
            dest.writeInt(coverWidth);
            dest.writeInt(coverHeight);
            dest.writeString(accountUrl);
            dest.writeInt(accountId);
            dest.writeString(privacy);
            dest.writeString(layout);
            dest.writeInt(views);
            dest.writeString(link);
            dest.writeInt(ups);
            dest.writeInt(downs);
            dest.writeInt(points);
            dest.writeInt(score);
            dest.writeByte((byte) (isAlbum ? 1 : 0));
            dest.writeString(vote);
            dest.writeByte((byte) (favorite ? 1 : 0));
            dest.writeByte((byte) (nsfw ? 1 : 0));
            dest.writeString(section);
            dest.writeInt(commentCount);
            dest.writeInt(favoriteCount);
            dest.writeString(topic);
            dest.writeInt(topicId);
            dest.writeInt(imagesCount);
            dest.writeByte((byte) (inGallery ? 1 : 0));
            dest.writeByte((byte) (isAd ? 1 : 0));
            dest.writeInt(adType);
            dest.writeString(adUrl);
            dest.writeByte((byte) (inMostViral ? 1 : 0));
            dest.writeByte((byte) (includeAlbumAds ? 1 : 0));
            dest.writeTypedList(images);
        }
    }
}
