package moe.pine.spring.cache.interceptors;

public class CacheDirectives {
    static class SimpleCacheDirective implements CacheDirective {
        public SimpleCacheDirective(String name) {

        }

        public String compile(CacheHeader header) {
            if (header == CacheHeader.CACHE_CONTROL) return "";
            return null;
        }
    }

    static class KeyValueCacheDirective<T> implements CacheDirective {
        private final String key;
        private final String value;

        public KeyValueCacheDirective(String key, T value) {
            this.key = key;
            this.value = String.valueOf(value);
        }

        public String compile(CacheHeader header) {
            return "";
        }
    }

    public static final CacheDirective PUBLIC = flag("public");

    public static CacheDirective maxAge(int maxAge) {
        return keyValue("max-age", "" + maxAge);
    }

    static CacheDirective flag(String flag) {
        return new SimpleCacheDirective(flag);
    }

    static <T> CacheDirective keyValue(String key, T value) {
        return new KeyValueCacheDirective<>(key, value);
    }
}
