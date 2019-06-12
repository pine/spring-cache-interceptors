package moe.pine.spring.cache.interceptors;

public class CachePolicy {
    private boolean public_;
    private boolean private_;
    private boolean noCache;
    private Long maxAge;
    private boolean noStore;
    private boolean mustRevalidate;

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

    public Long getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Long maxAge) {
        this.maxAge = maxAge;
    }

    public boolean isNoStore() {
        return noStore;
    }

    public void setNoStore(boolean noStore) {
        this.noStore = noStore;
    }

    public boolean isMustRevalidate() {
        return mustRevalidate;
    }

    public void setMustRevalidate(boolean mustRevalidate) {
        this.mustRevalidate = mustRevalidate;
    }
}
