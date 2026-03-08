package lrucache;

public class LRUCacheDemo {
    public void initalizeLRUCache() {
        LRUCache<String, String> cache = new LRUCache<>(5);
        cache.displayCacheAndValue();
        cache.put("1", "1");
        cache.put("2", "1");
        cache.put("3", "1");
        cache.put("4", "1");
        cache.put("5", "1");
    }
}
