package moe.pine.spring.cache.interceptors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CachePolicyTest {
    @Test
    void maxStale_toStringTest() {
        CachePolicy.MaxStale maxStale = new CachePolicy.MaxStale(12345L);
        assertEquals("MaxStale{seconds=12345}", maxStale.toString());
    }

    @Test
    void maxStale_toStringTest_enabled() {
        assertEquals("MaxStale{seconds=null}", CachePolicy.MaxStale.ENABLED.toString());
    }
}
