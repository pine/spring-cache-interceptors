# spring-cache-interceptors
:leaves: Cache interceptors for SpringFramework

## Getting started

```gradle
repositories {
    jcenter()
}

depepdencies {
    implementation 'moe.pine:spring-cache-interceptors:0.1.0'
}
```

## Usage

```java
import moe.pine.spring.cache.interceptors.CacheInterceptor;
import moe.pine.spring.cache.interceptors.CachePolicy;
import moe.pine.spring.cache.interceptors.CachePolicyBuilder;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        final CachePolicy cachePolicy = new CachePolicyBuilder()
            .public_()
            .maxAge(3600L)
            .build();
        final CacheInterceptor cacheInterceptor = new CacheInterceptor(cachePolicy);

        registry
            .addInterceptor(cacheInterceptor)
            .addPathPatterns("/**");
    }
}
```


## Development
### Test

```
$ ./gradlew clean check
```

### Upload Bintray

```
$ export BINTRAY_USER=username
$ export BINTRAY_KEY=apiKey
$ ./gradlew clean assemble bintrayUpload
```

## License

MIT &copy; [Pine Mizune](https://profile.pine.moe)
