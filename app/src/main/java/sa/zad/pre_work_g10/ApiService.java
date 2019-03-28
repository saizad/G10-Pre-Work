package sa.zad.pre_work_g10;

import retrofit2.http.*;
import sa.zad.easyretrofit.CachePolicy;
import sa.zad.easyretrofit.observables.NeverErrorObservable;
import sa.zad.pre_work_g10.models.*;

import static sa.zad.easyretrofit.EasyRetrofitClient.CACHE_POLICY_HEADER;

public interface ApiService {

    @Headers("Authorization:  Client-ID " + BuildConfig.CLIENT_ID)
    @GET("https://api.imgur.com/3/gallery/{imageHash}")
    NeverErrorObservable<GalleryModel> galleryModel(@Path(value = "imageHash", encoded = true) String imageHash);

    @Headers("Authorization:  Client-ID " + BuildConfig.CLIENT_ID)
    @GET("https://api.imgur.com/3/gallery/{galleryHash}/comments/{commentSort}")
    NeverErrorObservable<Comments> comments(@Path(value = "galleryHash", encoded = true) String galleryHash, @Path(value = "commentSort", encoded = true) String commentSort, @Header(CACHE_POLICY_HEADER) @CachePolicy int cachePolicy);

    @POST("https://api.imgur.com/3/comment")
    NeverErrorObservable<DataModel<CommentResponse>> createComment(@Body CommentBody commentBody, @Header("Authorization") String accessToken);
}
