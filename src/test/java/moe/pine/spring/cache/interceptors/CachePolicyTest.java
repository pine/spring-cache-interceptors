package moe.pine.spring.cache.interceptors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CachePolicyTest {
    @Test
    void toStringTest() {
        CachePolicy cachePolicy = new CachePolicy();
        cachePolicy.setPublic(true);
        cachePolicy.setPrivate(true);
        cachePolicy.setNoCache(true);
        cachePolicy.setOnlyIfCached(true);
        cachePolicy.setMaxAge(12345L);
        cachePolicy.setSMaxAge(23456L);
        cachePolicy.setMaxStale(new CachePolicy.MaxStale(34567L));
        cachePolicy.setMinFresh(45678L);
        cachePolicy.setStaleWhileRevalidate(5L);
        cachePolicy.setStaleIfError(6L);
        cachePolicy.setMustRevalidate(true);
        cachePolicy.setProxyRevalidate(true);
        cachePolicy.setImmutable(true);
        cachePolicy.setNoStore(true);
        cachePolicy.setNoTransform(true);

        assertEquals("CachePolicy{public=true, private=true, noCache=true, onlyIfCached=true, maxAge=12345, sMaxAge=23456, maxStale=MaxStale{seconds=34567}, minFresh=45678, staleWhileRevalidate=5, staleIfError=6, mustRevalidate=true, proxyRevalidate=true, immutable=true, noStore=true, noTransform=true}", cachePolicy.toString());
    }

    @Test
    void maxStale_hashCode_equals() {
        CachePolicy.MaxStale maxStale1 = new CachePolicy.MaxStale(12345L);
        CachePolicy.MaxStale maxStale2 = new CachePolicy.MaxStale(12345L);

        assertEquals(maxStale1.hashCode(), maxStale1.hashCode());
        assertEquals(maxStale1.hashCode(), maxStale2.hashCode());
    }

    @Test
    void maxStale_hashCode_null() {
        assertEquals(CachePolicy.MaxStale.ENABLED.hashCode(), CachePolicy.MaxStale.ENABLED.hashCode());
    }

    @Test
    void maxStale_hashCode_notEquals() {
        CachePolicy.MaxStale maxStale1 = new CachePolicy.MaxStale(12345L);
        CachePolicy.MaxStale maxStale2 = new CachePolicy.MaxStale(12346L);

        assertNotEquals(maxStale1.hashCode(), maxStale2.hashCode());
    }

    @Test
    void maxStale_equalsTest_equals() {
        CachePolicy.MaxStale maxStale1 = new CachePolicy.MaxStale(12345L);
        CachePolicy.MaxStale maxStale2 = new CachePolicy.MaxStale(12345L);

        assertEquals(maxStale1, maxStale1);
        assertEquals(maxStale1, maxStale2);
        assertEquals(CachePolicy.MaxStale.ENABLED, CachePolicy.MaxStale.ENABLED);
    }

    @Test
    void maxStale_equalsTest_notEquals() {
        CachePolicy.MaxStale maxStale1 = new CachePolicy.MaxStale(12345L);
        CachePolicy.MaxStale maxStale2 = new CachePolicy.MaxStale(12346L);

        assertNotEquals(maxStale1, maxStale2);
        assertNotEquals(maxStale1, CachePolicy.MaxStale.ENABLED);
        assertNotEquals(maxStale1, null);
    }

    @Test
    void maxStale_toStringTest() {
        CachePolicy.MaxStale maxStale = new CachePolicy.MaxStale(12345L);
        assertEquals("MaxStale{seconds=12345}", maxStale.toString());
    }

    @Test
    void maxStale_toStringTest_null() {
        assertEquals("MaxStale{seconds=null}", CachePolicy.MaxStale.ENABLED.toString());
    }
}
