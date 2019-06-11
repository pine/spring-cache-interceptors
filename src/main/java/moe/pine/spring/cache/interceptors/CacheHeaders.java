package moe.pine.spring.cache.interceptors;

import org.springframework.http.HttpHeaders;

import java.time.Clock;
import java.util.ArrayList;

public abstract class CacheHeaders {
    public static CacheHeader CACHE_CONTROL =
            new CacheHeaderImpl(
                    HttpHeaders.CACHE_CONTROL,
                    new CacheControlValueBuilder());

    public static CacheHeader PRAGMA =
            new CacheHeaderImpl(
                    HttpHeaders.PRAGMA,
                    new PragmaValueBuilder());

    public static CacheHeader EXPIRES =
            new CacheHeaderImpl(
                    HttpHeaders.EXPIRES,
                    new ExpiresValueBuilder());

    // -------------------------------------------------------------------

    interface CacheHeaderValueBuilder {
        String build(CachePolicy cachePolicy, Clock clock);
    }

    static class CacheHeaderImpl implements CacheHeader {
        private final String name;
        private final CacheHeaderValueBuilder valueBuilder;

        CacheHeaderImpl(
                final String name,
                final CacheHeaderValueBuilder joiner
        ) {
            this.name = name;
            this.valueBuilder = joiner;
        }

        public String getName() {
            return name;
        }

        public String buildValue(
                final CachePolicy cachePolicy,
                final Clock clock
        ) {
            return valueBuilder.build(cachePolicy, clock);
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
            if (cachePolicy.getMaxAge() != null) {
                directives.add("max-age=" + cachePolicy.getMaxAge());
            }
            if (cachePolicy.isNoStore()) {
                directives.add("no-store");
            }
            if (cachePolicy.isMustRevalidate()) {
                directives.add("must-revalidate");
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
            return null;
        }
    }
}

