# spring-cache-interceptors
[![Build Status](https://travis-ci.com/pine/spring-cache-interceptors.svg?branch=master)](https://travis-ci.com/pine/spring-cache-interceptors)
[![codecov](https://codecov.io/gh/pine/spring-cache-interceptors/branch/master/graph/badge.svg)](https://codecov.io/gh/pine/spring-cache-interceptors)
[![Maven Central](https://img.shields.io/maven-central/v/moe.pine/spring-cache-interceptors)](https://search.maven.org/artifact/moe.pine/spring-cache-interceptors)

:herb: Cache interceptors for SpringFramework

![](images/resized.jpg)<br>
<sup><sup>&copy; Pongnathee Kluaythong/123RF.COM</sup></sup>
<br>
<br>

## Requirements

- Java 8 or later
- Spring Framework

### Supported Spring Framework versions

|Spring Framework versions|Spring Boot versions|This library version|
|-------------------------|--------------------|--------------------|
|5.3                      |2.4, 2.5, 2.6       |0.2.0               |
|5.2                      |2.2, 2.3            |0.1.3               |

## Getting started
The library is published to Maven Central.

```gradle
repositories {
    mavenCentral()
}

depepdencies {
    implementation "moe.pine:spring-cache-interceptors:$latestVersion"
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

### Upload Maven Central

```
$ ./gradlew clean publish
```

## License

MIT &copy; [Pine Mizune](https://profile.pine.moe)
