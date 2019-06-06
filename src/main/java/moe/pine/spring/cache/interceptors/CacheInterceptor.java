package moe.pine.spring.cache.interceptors;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Clock;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CacheInterceptor extends HandlerInterceptorAdapter {
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
        this.cachePolicy = cachePolicy;
        this.cacheHeaders = cacheHeaders;
        this.clock = clock;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        for (final CacheHeader cacheHeader : cacheHeaders) {
            final String name = cacheHeader.getName();
            if (StringUtils.isEmpty(name)) {
                continue;
            }

            final String value = cacheHeader.buildValue(cachePolicy);
            if (StringUtils.isEmpty(value)) {
                continue;
            }

            response.addHeader(name, value);
        }

        return true;
    }
}
