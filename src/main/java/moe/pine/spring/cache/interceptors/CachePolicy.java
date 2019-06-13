package moe.pine.spring.cache.interceptors;

public class CachePolicy {
    private boolean public_;
    private boolean private_;
    private boolean noCache;
    private boolean onlyIfCached;
    private Long maxAge;
    private Long sMaxAge;
    private MaxStale maxStale;
    private Long minFresh;
    private Long staleWhileRevalidate;
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

    public Long getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Long maxAge) {
        this.maxAge = maxAge;
    }

    public Long getSMaxAge() {
        return sMaxAge;
    }

    public void setSMaxAge(Long sMaxAge) {
        this.sMaxAge = sMaxAge;
    }

    public MaxStale getMaxStale() {
        return maxStale;
    }

    public void setMaxStale(MaxStale maxStale) {
        this.maxStale = maxStale;
    }

    public Long getMinFresh() {
        return minFresh;
    }

    public void setMinFresh(Long minFresh) {
        this.minFresh = minFresh;
    }

    public Long getStaleWhileRevalidate() {
        return staleWhileRevalidate;
    }

    public void setStaleWhileRevalidate(Long staleWhileRevalidate) {
        this.staleWhileRevalidate = staleWhileRevalidate;
    }

    public Long getStaleIfError() {
        return staleIfError;
    }

    public void setStaleIfError(Long staleIfError) {
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

    // -------------------------------------------------------------------

    public static class MaxStale {
        public static final MaxStale ENABLED = new MaxStale(null);

        private final Long seconds;

        public static MaxStale of(long seconds) {
            return new MaxStale(seconds);
        }

        MaxStale(Long seconds) {
            this.seconds = seconds;
        }

        public Long getSeconds() {
            return seconds;
        }
    }
}
