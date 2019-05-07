package wang.auspicous.ausp1ciouslib.net.httpclient.func;

import java.net.ConnectException;
import java.net.SocketTimeoutException;


public class TimeoutRetryFunction extends RetryFunction {
    public TimeoutRetryFunction(int maxRetryCount, int retryDelayMillis) {
        super(maxRetryCount, retryDelayMillis);
    }

    @Override
    public boolean retry(Throwable throwable) {
        return throwable instanceof SocketTimeoutException || throwable instanceof ConnectException;
    }
}
