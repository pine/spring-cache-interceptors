package moe.pine.spring.cache.interceptors;

import org.springframework.lang.Nullable;

public class CachePolicy {
    private boolean public_;
    private boolean private_;
    private boolean noCache;
    private boolean onlyIfCached;

    @Nullable
    private Long maxAge;

    @Nullable
    private Long sMaxAge;

    @Nullable
    private MaxStale maxStale;

    @Nullable
    private Long minFresh;

    @Nullable
    private Long staleWhileRevalidate;

    @Nullable
    private Long staleIfError;

    private boolean mustRevalidate;
    private boolean proxyRevalidate;
    private boolean immutable;
    private boolean noStore;
    private boolean noTransform;

    public boolean isPublic() {
        return public_;
    }

    public void setPublic(boolean public_) {
        this.public_ = public_;
    }

    public boolean isPrivate() {
        return private_;
    }

    public void setPrivate(boolean private_) {
        this.private_ = private_;
    }

    public boolean isNoCache() {
        return noCache;
    }

    public void setNoCache(boolean noCache) {
        this.noCache = noCache;
    }

    public boolean isOnlyIfCached() {
        return onlyIfCached;
    }

    public void setOnlyIfCached(boolean onlyIfCached) {
        this.onlyIfCached = onlyIfCached;
    }

    @Nullable
    public Long getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(@Nullable Long maxAge) {
        this.maxAge = maxAge;
    }

    @Nullable
    public Long getSMaxAge() {
        return sMaxAge;
    }

    public void setSMaxAge(@Nullable Long sMaxAge) {
        this.sMaxAge = sMaxAge;
    }

    @Nullable
    public MaxStale getMaxStale() {
        return maxStale;
    }

    public void setMaxStale(@Nullable MaxStale maxStale) {
        this.maxStale = maxStale;
    }

    @Nullable
    public Long getMinFresh() {
        return minFresh;
    }

    public void setMinFresh(@Nullable Long minFresh) {
        this.minFresh = minFresh;
    }

    @Nullable
    public Long getStaleWhileRevalidate() {
        return staleWhileRevalidate;
    }

    public void setStaleWhileRevalidate(@Nullable Long staleWhileRevalidate) {
        this.staleWhileRevalidate = staleWhileRevalidate;
    }

    @Nullable
    public Long getStaleIfError() {
        return staleIfError;
    }

    public void setStaleIfError(@Nullable Long staleIfError) {
        this.staleIfError = staleIfError;
    }

    public boolean isMustRevalidate() {
        return mustRevalidate;
    }

    public void setMustRevalidate(boolean mustRevalidate) {
        this.mustRevalidate = mustRevalidate;
    }

    public boolean isProxyRevalidate() {
        return proxyRevalidate;
    }

    public void setProxyRevalidate(boolean proxyRevalidate) {
        this.proxyRevalidate = proxyRevalidate;
    }

    public boolean isImmutable() {
        return immutable;
    }

    public void setImmutable(boolean immutable) {
        this.immutable = immutable;
    }

    public boolean isNoStore() {
        return noStore;
    }

    public void setNoStore(boolean noStore) {
        this.noStore = noStore;
    }

    public boolean isNoTransform() {
        return noTransform;
    }

    public void setNoTransform(boolean noTransform) {
        this.noTransform = noTransform;
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
        public String toString() {
            return "MaxStale{" +
                    "seconds=" + seconds +
                    '}';
        }
    }
}
