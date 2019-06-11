package moe.pine.spring.cache.interceptors;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CacheHeadersTest {
    @Test
    public void cacheControlTest() {
        assertEquals("Cache-Control", CacheHeaders.CACHE_CONTROL.getName());
    }
}
