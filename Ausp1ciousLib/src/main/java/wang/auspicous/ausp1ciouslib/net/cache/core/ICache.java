package wang.auspicous.ausp1ciouslib.net.cache.core;

public interface ICache {
    void put(String key, Object value, long time);

    String get(String key);

    boolean contains(String key);

    void remove(String key);

    void clear();
}
