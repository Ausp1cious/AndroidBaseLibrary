package wang.auspicous.ausp1ciouslib.net.callback;


public interface RequestCallback<R> extends ICallback<R> {

    @Override
    default void onProgress(long currentLength, long totalLength, float percent) {
        //do nothing
    }
}
