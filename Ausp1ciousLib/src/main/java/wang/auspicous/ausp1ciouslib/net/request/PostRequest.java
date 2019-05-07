package wang.auspicous.ausp1ciouslib.net.request;


import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import wang.auspicous.ausp1ciouslib.net.MediaTypes;
import wang.auspicous.ausp1ciouslib.net.httpclient.HttpFactory;


public final class PostRequest extends BaseRequest<PostRequest> {
    private boolean postJson;

    @Override
    protected <T> Flowable<T> readRemote(Type type) {
        if (postJson) {
            return HttpFactory
                    .getApiService()
                    .postBody(suffixUrl, RequestBody.create(MediaTypes.APPLICATION_JSON_TYPE, new GsonBuilder().disableHtmlEscaping().create().toJson(params)))
                    .compose(applySchedulers());
        }
        return HttpFactory.getApiService().post(suffixUrl, params).compose(applySchedulers());
    }

    public PostRequest setPostJson(boolean postJson) {
        this.postJson = postJson;
        return this;
    }
}
