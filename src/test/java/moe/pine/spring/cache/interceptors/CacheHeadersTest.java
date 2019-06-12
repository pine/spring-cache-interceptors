package moe.pine.spring.cache.interceptors;

import org.junit.Test;

import java.time.Clock;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;

public class CacheHeadersTest {
    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.of("Asia/Tokyo");
    private static final Clock CLOCK =
            Clock.fixed(
                    ZonedDateTime
                            .of(2019, 6, 12, 12, 0, 0, 0, ZONE_OFFSET)
                            .toInstant(),
                    ZONE_OFFSET);

    @Test
    public void cacheControlTest() {
        assertEquals("Cache-Control", CacheHeaders.CACHE_CONTROL.getName());

        final CachePolicy cachePolicy = new CachePolicyBuilder()
                .build();
        CacheHeaders.CACHE_CONTROL.buildValue(cachePolicy, CLOCK);
    }
}
