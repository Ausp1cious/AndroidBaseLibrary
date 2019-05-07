package wang.auspicous.ausp1ciouslib.net.api;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Multipart;
import retrofit2.http.OPTIONS;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApiService {
    @GET()
    Flowable<ResponseBody> get(@Url String url, @QueryMap Map<String, Object> maps);

    @FormUrlEncoded
    @POST()
    Flowable<ResponseBody> post(@Url() String url, @FieldMap Map<String, Object> maps);

    @POST()
    Flowable<ResponseBody> postBody(@Url() String url, @Body RequestBody requestBody);

    @HEAD()
    Flowable<ResponseBody> head(@Url String url, @QueryMap Map<String, Object> maps);

    @OPTIONS()
    Flowable<ResponseBody> options(@Url String url, @QueryMap Map<String, Object> maps);

    @FormUrlEncoded
    @PUT()
    Flowable<ResponseBody> put(@Url() String url, @FieldMap Map<String, Object> maps);

    @FormUrlEncoded
    @PATCH()
    Flowable<ResponseBody> patch(@Url() String url, @FieldMap Map<String, Object> maps);

    @FormUrlEncoded
    @DELETE()
    Flowable<ResponseBody> delete(@Url() String url, @FieldMap Map<String, Object> maps);

    @Streaming
    @GET()
    Flowable<ResponseBody> downloadFile(@Url() String url, @QueryMap Map<String, Object> maps);

    @Multipart
    @POST()
    Flowable<ResponseBody> uploadFiles(@Url() String url, @Part() List<MultipartBody.Part> parts);
}
