package moe.pine.spring.cache.interceptors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CachePolicyTest {
    @Test
    void equalsTest_equals() {
        CachePolicy cachePolicy1 = new CachePolicy(
                true,
                true,
                true,
                true,
                1L,
                2L,
                new CachePolicy.MaxStale(3L),
                4L,
                5L,
                6L,
                true,
                true,
                true,
                true,
                true
        );
        CachePolicy cachePolicy2 = new CachePolicy(
                true,
                true,
                true,
                true,
                1L,
                2L,
                new CachePolicy.MaxStale(3L),
                4L,
                5L,
                6L,
                true,
                true,
                true,
                true,
                true
        );

        assertEquals(cachePolicy1, cachePolicy1);
        assertEquals(cachePolicy1, cachePolicy2);
    }

    @Test
    void equalsTest_notEquals() {
        CachePolicy cachePolicy1 = new CachePolicy(
                true,
                true,
                true,
                true,
                1L,
                2L,
                new CachePolicy.MaxStale(3L),
                4L,
                5L,
                6L,
                true,
                true,
                true,
                true,
                true
        );
        CachePolicy cachePolicy2 = new CachePolicy(
                true,
                true,
                true,
                true,
                1L,
                2L,
                new CachePolicy.MaxStale(3L),
                4L,
                5L,
                6L,
                true,
                true,
                true,
                true,
                false
        );

        assertNotEquals(cachePolicy1, null);
        assertNotEquals(cachePolicy1, cachePolicy2);
    }

    @Test
    void hashCodeTest_equals() {
        CachePolicy cachePolicy1 = new CachePolicy(
                true,
                true,
                true,
                true,
                1L,
                2L,
                new CachePolicy.MaxStale(3L),
                4L,
                5L,
                6L,
                true,
                true,
                true,
                true,
                true
        );
        CachePolicy cachePolicy2 = new CachePolicy(
                true,
                true,
                true,
                true,
                1L,
                2L,
                new CachePolicy.MaxStale(3L),
                4L,
                5L,
                6L,
                true,
                true,
                true,
                true,
                true
        );

        assertEquals(cachePolicy1.hashCode(), cachePolicy1.hashCode());
        assertEquals(cachePolicy1.hashCode(), cachePolicy2.hashCode());
    }

    @Test
    void hashCodeTest_notEquals() {
        CachePolicy cachePolicy1 = new CachePolicy(
                true,
                true,
                true,
                true,
                1L,
                2L,
                new CachePolicy.MaxStale(3L),
                4L,
                5L,
                6L,
                true,
                true,
                true,
                true,
                true
        );
        CachePolicy cachePolicy2 = new CachePolicy(
                true,
                true,
                true,
                true,
                1L,
                2L,
                new CachePolicy.MaxStale(3L),
                4L,
                5L,
                6L,
                true,
                true,
                true,
                true,
                false
        );

        assertNotEquals(cachePolicy1.hashCode(), cachePolicy2.hashCode());
    }

    @Test
    void toStringTest() {
        CachePolicy cachePolicy = new CachePolicy(
                true,
                true,
                true,
                true,
                1L,
                2L,
                new CachePolicy.MaxStale(3L),
                4L,
                5L,
                6L,
                true,
                true,
                true,
                true,
                true
        );

        assertEquals("CachePolicy{public=true, private=true, noCache=true, onlyIfCached=true, maxAge=1, sMaxAge=2, maxStale=MaxStale{seconds=3}, minFresh=4, staleWhileRevalidate=5, staleIfError=6, mustRevalidate=true, proxyRevalidate=true, immutable=true, noStore=true, noTransform=true}", cachePolicy.toString());
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
