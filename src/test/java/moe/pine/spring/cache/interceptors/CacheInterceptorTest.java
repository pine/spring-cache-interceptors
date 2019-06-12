package moe.pine.spring.cache.interceptors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Clock;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CacheInterceptorTest {
    @Mock
    private CachePolicy cachePolicy;

    @Mock
    private CacheHeader cacheHeader1;

    @Mock
    private CacheHeader cacheHeader2;

    @Mock
    private Clock clock;

    private CacheInterceptor cacheInterceptor;

    @Before
    public void setUp() {
        cacheInterceptor =
                new CacheInterceptor(
                        cachePolicy,
                        Arrays.asList(cacheHeader1, cacheHeader2),
                        clock
                );
    }

    @Test
    public void constructorTest() {
        final CacheInterceptor cacheInterceptor = new CacheInterceptor(cachePolicy);
        assertSame(cachePolicy, cacheInterceptor.getCachePolicy());
        assertSame(CacheInterceptor.DEFAULT_HEADERS, cacheInterceptor.getCacheHeaders());
    }

    @Test
    public void preHandleTest() {
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        when(cacheHeader1.getName()).thenReturn("Header1");
        when(cacheHeader1.buildValue(cachePolicy, clock)).thenReturn(Optional.of("value1"));
        when(cacheHeader2.getName()).thenReturn("Header2");
        when(cacheHeader2.buildValue(cachePolicy, clock)).thenReturn(Optional.of("value2"));

        cacheInterceptor.preHandle(request, response, null);

        verify(cacheHeader1).getName();
        verify(cacheHeader1).buildValue(cachePolicy, clock);
        verify(cacheHeader2).getName();
        verify(cacheHeader2).buildValue(cachePolicy, clock);

        verify(response).addHeader("Header1", "value1");
        verify(response).addHeader("Header2", "value2");
        verify(response, times(2)).addHeader(anyString(), anyString());
    }

    @Test
    public void preHandleTest_empty() {
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        when(cacheHeader1.getName()).thenReturn("");
        when(cacheHeader2.getName()).thenReturn("Header2");
        when(cacheHeader2.buildValue(cachePolicy, clock)).thenReturn(Optional.empty());

        cacheInterceptor.preHandle(request, response, null);

        verify(cacheHeader1).getName();
        verify(cacheHeader1, never()).buildValue(cachePolicy, clock);
        verify(cacheHeader2).getName();
        verify(cacheHeader2).buildValue(cachePolicy, clock);

        verify(response, never()).addHeader(anyString(), anyString());
    }

    @Test
    public void getCachePolicyTest() {
        assertSame(cachePolicy, cacheInterceptor.getCachePolicy());
    }

    @Test
    public void getCacheHeadersTest() {
        assertEquals(2, cacheInterceptor.getCacheHeaders().size());
        assertSame(cacheHeader1, cacheInterceptor.getCacheHeaders().get(0));
        assertSame(cacheHeader2, cacheInterceptor.getCacheHeaders().get(1));
    }

    @Test
    public void getClockTest() {
        assertSame(clock, cacheInterceptor.getClock());
    }
}