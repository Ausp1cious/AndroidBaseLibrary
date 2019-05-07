package wang.auspicous.ausp1ciouslib.net.request;


import android.text.TextUtils;

import java.io.File;
import java.lang.reflect.Type;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import wang.auspicous.ausp1ciouslib.net.callback.DownloadProgressCallback;
import wang.auspicous.ausp1ciouslib.net.callback.ICallback;
import wang.auspicous.ausp1ciouslib.net.httpclient.HttpFactory;
import wang.auspicous.ausp1ciouslib.net.subscribe.DownloadSubscriber;


public final class DownloadRequest extends BaseRequest<DownloadRequest> {
    private File dir;
    private String filename;

    @Deprecated
    @Override
    public <T> void execute(ICallback<T> callback, FlowableTransformer<T, T> lifecycle) {
    }

    public void execute(DownloadProgressCallback callback) {

        if (null == dir || TextUtils.isEmpty(filename)) {
            throw new NullPointerException("dir or filename is null");
        }

        HttpFactory
                .getApiService()
                .downloadFile(suffixUrl, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DownloadSubscriber<ResponseBody>(new File(dir, filename), callback));
    }

    @Override
    protected <T> Flowable<T> readRemote(Type type) {
        return null;
    }

    public DownloadRequest setDir(File dir) {
        this.dir = dir;
        return this;
    }

    public DownloadRequest setFilename(String filename) {
        this.filename = filename;
        return this;
    }
}
