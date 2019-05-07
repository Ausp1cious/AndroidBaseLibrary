package wang.auspicous.ausp1ciouslib.net.request;



import java.lang.reflect.Type;

import io.reactivex.Flowable;
import wang.auspicous.ausp1ciouslib.net.httpclient.HttpFactory;


public final class GetRequest extends BaseRequest<GetRequest> {
    @Override
    protected <T> Flowable<T> readRemote(Type type) {
        return HttpFactory.getApiService().get(suffixUrl, params).compose(applySchedulers());
    }
}
