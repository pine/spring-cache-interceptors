package moe.pine.spring.cache.interceptors;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CacheInterceptor implements HandlerInterceptor {
    public static final List<CacheHeader> DEFAULT_HEADERS =
            Collections.unmodifiableList(
                    Arrays.asList(
                            CacheHeaders.CACHE_CONTROL,
                            CacheHeaders.PRAGMA
                    ));

    // -------------------------------------------------------------------

    private final CachePolicy cachePolicy;
    private final List<CacheHeader> cacheHeaders;
    private final Clock clock;

    public CacheInterceptor(
            final CachePolicy cachePolicy
    ) {
        this(cachePolicy, DEFAULT_HEADERS);
    }

    public CacheInterceptor(
            final CachePolicy cachePolicy,
            final List<CacheHeader> cacheHeaders
    ) {
        this(cachePolicy, cacheHeaders, Clock.systemDefaultZone());
    }

    public CacheInterceptor(
            final CachePolicy cachePolicy,
            final List<CacheHeader> cacheHeaders,
            final Clock clock
    ) {
        final List<CacheHeader> unmodifiableCacheHeaders =
                Collections.unmodifiableList(new ArrayList<>(cacheHeaders));

        this.cachePolicy = cachePolicy;
        this.cacheHeaders = unmodifiableCacheHeaders;
        this.clock = clock;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        for (final CacheHeader cacheHeader : getCacheHeaders()) {
            final String name = cacheHeader.getName();
            if (!StringUtils.hasLength(name)) {
                continue;
            }

            final Optional<String> valueOpt = cacheHeader.buildValue(getCachePolicy(), getClock());
            valueOpt.ifPresent(value -> response.addHeader(name, value));
        }

        return true;
    }

    protected CachePolicy getCachePolicy() {
        return cachePolicy;
    }

    protected List<CacheHeader> getCacheHeaders() {
        return cacheHeaders;
    }

    protected Clock getClock() {
        return clock;
    }
}
