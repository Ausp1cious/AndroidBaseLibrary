package wang.auspicous.ausp1ciouslib.net;


import wang.auspicous.ausp1ciouslib.net.request.DownloadRequest;
import wang.auspicous.ausp1ciouslib.net.request.GetRequest;
import wang.auspicous.ausp1ciouslib.net.request.PostQueryRequest;
import wang.auspicous.ausp1ciouslib.net.request.PostRequest;
import wang.auspicous.ausp1ciouslib.net.request.UploadRequest;

/**
 * Created by xianghairui on 2018/12/13.
 */
public final class HttpUtils {

    public static GetRequest get() {
        return new GetRequest();
    }

    public static PostRequest post() {
        return new PostRequest();
    }

    public static PostQueryRequest postQuery() {
        return new PostQueryRequest();
    }

    public static DownloadRequest download() {
        return new DownloadRequest();
    }

    public static UploadRequest upload() {
        return new UploadRequest();
    }
}
