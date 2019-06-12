package moe.pine.spring.cache.interceptors;

import org.junit.Test;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class CacheHeadersTest {
    private static final ZoneId ZONE_ID = ZoneId.of("Asia/Tokyo");
    private static final Clock CLOCK =
            Clock.fixed(
                    ZonedDateTime
                            .of(2019, 6, 12, 12, 0, 0, 0, ZONE_ID)
                            .toInstant(),
                    ZONE_ID);

    @Test
    public void cacheControlTest() {
        assertEquals("Cache-Control", CacheHeaders.CACHE_CONTROL.getName());

        final CachePolicy emptyCachePolicy = new CachePolicyBuilder().build();
        assertEquals(
                Optional.empty(),
                CacheHeaders.CACHE_CONTROL.buildValue(emptyCachePolicy, CLOCK));

        final CachePolicy cachePolicy = new CachePolicyBuilder()
                .public_()
                .private_()
                .noCache()
                .maxAge(12345L)
                .noStore()
                .mustRevalidate()
                .build();
        assertEquals(
                Optional.of("public, private, no-cache, max-age=12345, no-store, must-revalidate"),
                CacheHeaders.CACHE_CONTROL.buildValue(cachePolicy, CLOCK));
    }

    @Test
    public void pragmaTest() {
        assertEquals("Pragma", CacheHeaders.PRAGMA.getName());

        final CachePolicy emptyCachePolicy = new CachePolicyBuilder().build();
        assertEquals(
                Optional.empty(),
                CacheHeaders.PRAGMA.buildValue(emptyCachePolicy, CLOCK));

        final CachePolicy cachePolicy = new CachePolicyBuilder()
                .noCache()
                .build();
        assertEquals(
                Optional.of("no-cache"),
                CacheHeaders.PRAGMA.buildValue(cachePolicy, CLOCK));
    }
}
