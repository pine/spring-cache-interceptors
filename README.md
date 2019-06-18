# spring-cache-interceptors
[![Build Status](https://travis-ci.com/pine/spring-cache-interceptors.svg?branch=master)](https://travis-ci.com/pine/spring-cache-interceptors)
[![codecov](https://codecov.io/gh/pine/spring-cache-interceptors/branch/master/graph/badge.svg)](https://codecov.io/gh/pine/spring-cache-interceptors)
[![Download](https://api.bintray.com/packages/pinemz/maven/spring-cache-interceptors/images/download.svg)](https://bintray.com/pinemz/maven/spring-cache-interceptors)

:leaves: Cache interceptors for SpringFramework

![](images/resized.jpg)<br>
<sup><sup>&copy; Pongnathee Kluaythong/123RF.COM</sup></sup>
<br>
<br>

## Requirements

- Java 8 or later

## Getting started
The library is published to jcenter.

```gradle
repositories {
    jcenter()
}

depepdencies {
    implementation 'moe.pine:spring-cache-interceptors:0.1.0'
}
```

## Usage
You can create a new interceptor with Spring Framework as following.

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
