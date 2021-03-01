package moe.pine.spring.cache.interceptors;

import org.springframework.lang.Nullable;

import java.util.Objects;

public class CachePolicy {
    private final boolean public_;
    private final boolean private_;
    private final boolean noCache;
    private final boolean onlyIfCached;

    @Nullable
    private final Long maxAge;

    @Nullable
    private final Long sMaxAge;

    @Nullable
    private final MaxStale maxStale;

    @Nullable
    private final Long minFresh;

    @Nullable
    private final Long staleWhileRevalidate;

    @Nullable
    private final Long staleIfError;

    private final boolean mustRevalidate;
    private final boolean proxyRevalidate;
    private final boolean immutable;
    private final boolean noStore;
    private final boolean noTransform;

    public CachePolicy(
            boolean public_,
            boolean private_,
            boolean noCache,
            boolean onlyIfCached,
            @Nullable Long maxAge,
            @Nullable Long sMaxAge,
            @Nullable MaxStale maxStale,
            @Nullable Long minFresh,
            @Nullable Long staleWhileRevalidate,
            @Nullable Long staleIfError,
            boolean mustRevalidate,
            boolean proxyRevalidate,
            boolean immutable,
            boolean noStore,
            boolean noTransform
    ) {
        this.public_ = public_;
        this.private_ = private_;
        this.noCache = noCache;
        this.onlyIfCached = onlyIfCached;
        this.maxAge = maxAge;
        this.sMaxAge = sMaxAge;
        this.maxStale = maxStale;
        this.minFresh = minFresh;
        this.staleWhileRevalidate = staleWhileRevalidate;
        this.staleIfError = staleIfError;
        this.mustRevalidate = mustRevalidate;
        this.proxyRevalidate = proxyRevalidate;
        this.immutable = immutable;
        this.noStore = noStore;
        this.noTransform = noTransform;
    }

    public boolean isPublic() {
        return public_;
    }

    public boolean isPrivate() {
        return private_;
    }

    public boolean isNoCache() {
        return noCache;
    }

    public boolean isOnlyIfCached() {
        return onlyIfCached;
    }

    @Nullable
    public Long getMaxAge() {
        return maxAge;
    }

    @Nullable
    public Long getSMaxAge() {
        return sMaxAge;
    }

    @Nullable
    public MaxStale getMaxStale() {
        return maxStale;
    }

    @Nullable
    public Long getMinFresh() {
        return minFresh;
    }

    @Nullable
    public Long getStaleWhileRevalidate() {
        return staleWhileRevalidate;
    }

    @Nullable
    public Long getStaleIfError() {
        return staleIfError;
    }

    public boolean isMustRevalidate() {
        return mustRevalidate;
    }

    public boolean isProxyRevalidate() {
        return proxyRevalidate;
    }

    public boolean isImmutable() {
        return immutable;
    }

    public boolean isNoStore() {
        return noStore;
    }

    public boolean isNoTransform() {
        return noTransform;
    }

    @Override
    public String toString() {
        return "CachePolicy{" +
                "public=" + public_ +
                ", private=" + private_ +
                ", noCache=" + noCache +
                ", onlyIfCached=" + onlyIfCached +
                ", maxAge=" + maxAge +
                ", sMaxAge=" + sMaxAge +
                ", maxStale=" + maxStale +
                ", minFresh=" + minFresh +
                ", staleWhileRevalidate=" + staleWhileRevalidate +
                ", staleIfError=" + staleIfError +
                ", mustRevalidate=" + mustRevalidate +
                ", proxyRevalidate=" + proxyRevalidate +
                ", immutable=" + immutable +
                ", noStore=" + noStore +
                ", noTransform=" + noTransform +
                '}';
    }

    // -------------------------------------------------------------------

    public static class MaxStale {
        public static final MaxStale ENABLED = new MaxStale(null);

        @Nullable
        private final Long seconds;

        public MaxStale(@Nullable Long seconds) {
            this.seconds = seconds;
        }

        @Nullable
        public Long getSeconds() {
            return seconds;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MaxStale maxStale = (MaxStale) o;
            return Objects.equals(seconds, maxStale.seconds);
        }

        @Override
        public int hashCode() {
            return Objects.hash(seconds);
        }

        @Override
        public String toString() {
            return "MaxStale{" +
                    "seconds=" + seconds +
                    '}';
        }
    }
}
