package wang.auspicous.ausp1ciouslib.net.httpclient.func;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Type;

import io.reactivex.functions.Function;
import okhttp3.ResponseBody;


public class ApiFunction<T> implements Function<ResponseBody, T> {
    private Type type;

    public ApiFunction(Type type) {
        this.type = type;
    }

    @Override
    public T apply(ResponseBody responseBody) throws Exception {
        String json;
        try {
            json = responseBody.string();
            if (type.equals(String.class)) {
                return (T) json;
            } else {
                return new GsonBuilder().disableHtmlEscaping().create().fromJson(json, type);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            responseBody.close();
        }
        return null;
    }
}
