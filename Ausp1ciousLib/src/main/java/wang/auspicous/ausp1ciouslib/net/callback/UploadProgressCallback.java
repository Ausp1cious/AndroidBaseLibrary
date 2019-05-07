package wang.auspicous.ausp1ciouslib.net.callback;

import java.util.Map;


public interface UploadProgressCallback<R> extends ICallback<R> {

    @Override
    default void onError(String requestUrl, Map<String, Object> requestParams, Throwable throwable) {

    }

    @Override
    default void onStart() {

    }

    @Override
    default void onFinish() {

    }
}
