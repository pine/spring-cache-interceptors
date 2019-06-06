package moe.pine.spring.cache.interceptors;

import org.springframework.http.HttpHeaders;

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
        String build(CachePolicy cachePolicy);
    }

    static class CacheHeaderImpl implements CacheHeader {
        private final String name;
        private final CacheHeaderValueBuilder valueBuilder;

        CacheHeaderImpl(
                final String name,
                final CacheHeaderValueBuilder joiner) {
            this.name = name;
            this.valueBuilder = joiner;
        }

        public String getName() {
            return name;
        }

        public String buildValue(CachePolicy cachePolicy) {
            return valueBuilder.build(cachePolicy);
        }
    }

    // -------------------------------------------------------------------

    static class CacheControlValueBuilder implements CacheHeaderValueBuilder {
        @Override
        public String build(CachePolicy cachePolicy) {
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
        public String build(CachePolicy cachePolicy) {
            if (cachePolicy.isNoCache()) {
                return "no-cache";
            }
            return null;
        }
    }

    // -------------------------------------------------------------------

    static class ExpiresValueBuilder implements CacheHeaderValueBuilder {
        @Override
        public String build(CachePolicy cachePolicy) {
            return null;
        }
    }
}

