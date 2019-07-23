package wang.auspicous.ausp1cious.Presenters;

import android.text.TextUtils;
import android.util.Log;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import wang.auspicous.ausp1cious.AppApplication;
import wang.auspicous.ausp1cious.Presenters.Contracts.ControlMainContract;
import wang.auspicous.ausp1cious.base.SimpleCallBack;
import wang.auspicous.ausp1ciouslib.base.presenter.BasePresenterImpl;
import wang.auspicous.ausp1ciouslib.net.HttpUtils;
import wang.auspicous.ausp1ciouslib.net.httpclient.HttpFactory;

public class ControlMainPresenterImpl extends BasePresenterImpl<ControlMainContract.ControlMainView> implements ControlMainContract.ControlMainPresenter {
    private static final String TAG = "ControlMainPresenterImp";
    @Override
    public void setBaseUrl(String ip, String port) {
        AppApplication.getHttpFactory().newBuilder().baseUrl("http://"+ip + ":" + port).build();
        Log.i(TAG, "setBaseUrl: "+ HttpFactory.getHttpConfig().getBaseUrl());
    }

    @Override
    public void getPancamInfo() {
        fetchService(1, "live/admin", new SimpleCallBack<String>(getView()) {
            @Override
            public void onNext(String requestUrl, Map<String, Object> requestParams, String responseResult) {
                if (!TextUtils.isEmpty(responseResult) && getView() != null) {
                    getView().getPancamInfo(responseResult);
                }
            }

            @Override
            public void onError(String requestUrl, Map<String, Object> requestParams, Throwable throwable) {
                if (!TextUtils.isEmpty(throwable.getMessage()) && getView() != null) {
                    getView().getPancamInfo(throwable.getMessage());
                }
            }
        });
    }

    @Override
    public void getBitratiosInfo() {
        fetchService(1, "live/bitratios", new SimpleCallBack<String>(getView()) {
            @Override
            public void onNext(String requestUrl, Map<String, Object> requestParams, String responseResult) {
                if (!TextUtils.isEmpty(responseResult) && getView() != null) {
                    getView().getBitratiosInfo(responseResult);
                }
            }

            @Override
            public void onError(String requestUrl, Map<String, Object> requestParams, Throwable throwable) {
                if (!TextUtils.isEmpty(throwable.getMessage()) && getView() != null) {
                    getView().getPancamInfo(throwable.getMessage());
                }
            }
        });
    }

    @Override
    public void getSettingInfo() {
        fetchService(1, "live/settings", new SimpleCallBack<String>(getView()) {
            @Override
            public void onNext(String requestUrl, Map<String, Object> requestParams, String responseResult) {
                if (!TextUtils.isEmpty(responseResult) && getView() != null) {
                    getView().getSettingInfo(responseResult);
                }
            }

            @Override
            public void onError(String requestUrl, Map<String, Object> requestParams, Throwable throwable) {
                if (!TextUtils.isEmpty(throwable.getMessage()) && getView() != null) {
                    getView().getPancamInfo(throwable.getMessage());
                }
            }
        });
    }

    @Override
    public void setUpdate(String bitratios, String rtmp) {
        HttpUtils.postQuery().setSuffixUrl("live/update").addParams("rtmp", rtmp).addParams("bitrate",bitratios).execute(new SimpleCallBack<String>(getView()) {
            @Override
            public void onNext(String requestUrl, Map<String, Object> requestParams, String responseResult) {
                if (!TextUtils.isEmpty(responseResult) && getView() != null) {
                    getView().getSettingInfo(responseResult);
                }
            }

            @Override
            public void onError(String requestUrl, Map<String, Object> requestParams, Throwable throwable) {
                if (!TextUtils.isEmpty(throwable.getMessage()) && getView() != null) {
                    getView().getPancamInfo(throwable.getMessage());
                }
            }
        },getView().bindApiLifecycle());
    }

    @Override
    public void reboot() {
        fetchService(2, "reboot", new SimpleCallBack<String>(getView()) {
            @Override
            public void onNext(String requestUrl, Map<String, Object> requestParams, String responseResult) {

            }

            @Override
            public void onError(String requestUrl, Map<String, Object> requestParams, Throwable throwable) {
                if (!TextUtils.isEmpty(throwable.getMessage()) && getView() != null) {
                    getView().getPancamInfo(throwable.getMessage());
                }
            }
        });
    }

    private void fetchService(int type, String url,SimpleCallBack<String> callBack) {
        if (type == 1) {
            HttpUtils.get().setSuffixUrl(url).execute(callBack,getView().bindApiLifecycle());
        } else if (type == 2) {
            HttpUtils.post().setSuffixUrl(url).execute(callBack,getView().bindApiLifecycle());
        }
    }

    private void fetchService(int type, String url,Map<String,Object> params,SimpleCallBack<String> callBack) {
        if (type == 1) {
            HttpUtils.get().setSuffixUrl(url).setParams(params).execute(callBack,getView().bindApiLifecycle());
        } else if (type == 2) {
            HttpUtils.post().setSuffixUrl(url).setParams(params).execute(callBack,getView().bindApiLifecycle());
        }
    }


}
