package moe.pine.spring.cache.interceptors;

import org.junit.Test;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
                .onlyIfCached()
                .maxAge(12345L)
                .sMaxAge(23456L)
                .maxStale(CachePolicy.MaxStale.ENABLED)
                .minFresh(34567L)
                .staleWhileRevalidate(88888L)
                .staleIfError(99999L)
                .mustRevalidate()
                .proxyRevalidate()
                .immutable()
                .noStore()
                .noTransform()
                .build();
        assertEquals(
                Optional.of("public, private, no-cache, only-if-cached, max-age=12345, s-maxage=23456, max-stale, min-fresh=34567, stale-while-revalidate=88888, stale-if-error=99999, must-revalidate, proxy-revalidate, immutable, no-store, no-transform"),
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

    @Test
    public void expiresTest() {
        assertEquals("Expires", CacheHeaders.EXPIRES.getName());

        final CachePolicy emptyCachePolicy = new CachePolicyBuilder().build();
        assertEquals(
                Optional.empty(),
                CacheHeaders.EXPIRES.buildValue(emptyCachePolicy, CLOCK));

        final CachePolicy cachePolicy = new CachePolicyBuilder()
                .maxAge(TimeUnit.DAYS.toSeconds(1))
                .build();
        assertEquals(
                Optional.of("Thu, 13 Jun 2019 03:00:00 GMT"),
                CacheHeaders.EXPIRES.buildValue(cachePolicy, CLOCK));
    }
}
