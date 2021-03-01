package moe.pine.spring.cache.interceptors;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public final class CacheHeaders {
    public static final CacheHeader CACHE_CONTROL =
            new CacheHeaderImpl(
                    HttpHeaders.CACHE_CONTROL,
                    new CacheControlValueBuilder());

    public static final CacheHeader PRAGMA =
            new CacheHeaderImpl(
                    HttpHeaders.PRAGMA,
                    new PragmaValueBuilder());

    public static final CacheHeader EXPIRES =
            new CacheHeaderImpl(
                    HttpHeaders.EXPIRES,
                    new ExpiresValueBuilder());

    private CacheHeaders() {
    }

    // -------------------------------------------------------------------

    @FunctionalInterface
    interface CacheHeaderValueBuilder {
        @Nullable
        String build(CachePolicy cachePolicy, Clock clock);
    }

    static class CacheHeaderImpl implements CacheHeader {
        private final String name;
        private final CacheHeaderValueBuilder valueBuilder;

        CacheHeaderImpl(
                final String name,
                final CacheHeaderValueBuilder valueBuilder
        ) {
            this.name = name;
            this.valueBuilder = valueBuilder;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Optional<String> buildValue(
                final CachePolicy cachePolicy,
                final Clock clock
        ) {
            final String value = valueBuilder.build(cachePolicy, clock);
            if (!StringUtils.hasLength(value)) {
                return Optional.empty();
            }
            return Optional.of(value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final CacheHeaderImpl that = (CacheHeaderImpl) o;
            return name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "CacheHeader{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    // -------------------------------------------------------------------

    static class CacheControlValueBuilder implements CacheHeaderValueBuilder {
        @Override
        public String build(
                final CachePolicy cachePolicy,
                final Clock clock
        ) {
            final ArrayList<String> directives = new ArrayList<>();
            if (cachePolicy.isPublic()) {
                directives.add("public");
            }
            if (cachePolicy.isPrivate()) {
                directives.add("private");
            }
            if (cachePolicy.isNoCache()) {
                directives.add("no-cache");
            }
            if (cachePolicy.isOnlyIfCached()) {
                directives.add("only-if-cached");
            }
            if (cachePolicy.getMaxAge() != null) {
                directives.add("max-age=" + cachePolicy.getMaxAge());
            }
            if (cachePolicy.getSMaxAge() != null) {
                directives.add("s-maxage=" + cachePolicy.getSMaxAge());
            }
            final CachePolicy.MaxStale maxStale = cachePolicy.getMaxStale();
            if (maxStale != null) {
                final Long seconds = maxStale.getSeconds();
                if (seconds != null) {
                    directives.add("max-stale=" + seconds);
                } else {
                    directives.add("max-stale");
                }
            }
            if (cachePolicy.getMinFresh() != null) {
                directives.add("min-fresh=" + cachePolicy.getMinFresh());
            }
            if (cachePolicy.getStaleWhileRevalidate() != null) {
                directives.add("stale-while-revalidate=" + cachePolicy.getStaleWhileRevalidate());
            }
            if (cachePolicy.getStaleIfError() != null) {
                directives.add("stale-if-error=" + cachePolicy.getStaleIfError());
            }
            if (cachePolicy.isMustRevalidate()) {
                directives.add("must-revalidate");
            }
            if (cachePolicy.isProxyRevalidate()) {
                directives.add("proxy-revalidate");
            }
            if (cachePolicy.isImmutable()) {
                directives.add("immutable");
            }
            if (cachePolicy.isNoStore()) {
                directives.add("no-store");
            }
            if (cachePolicy.isNoTransform()) {
                directives.add("no-transform");
            }
            return String.join(", ", directives);
        }
    }

    // -------------------------------------------------------------------

    static class PragmaValueBuilder implements CacheHeaderValueBuilder {
        @Override
        public String build(
                final CachePolicy cachePolicy,
                final Clock clock
        ) {
            if (cachePolicy.isNoCache()) {
                return "no-cache";
            }
            return null;
        }
    }

    // -------------------------------------------------------------------

    static class ExpiresValueBuilder implements CacheHeaderValueBuilder {
        @Override
        public String build(
                final CachePolicy cachePolicy,
                final Clock clock
        ) {
            final Long maxAge = cachePolicy.getMaxAge();
            if (maxAge != null) {
                final OffsetDateTime dt =
                        OffsetDateTime.now(clock)
                                .plusSeconds(maxAge)
                                .withOffsetSameInstant(ZoneOffset.UTC);
                return dt.format(DateTimeFormatter.RFC_1123_DATE_TIME);
            }
            return null;
        }
    }
}

