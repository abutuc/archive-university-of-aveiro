package ua.tqs.hw1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.tqs.hw1.data.Cache;
import static org.assertj.core.api.Assertions.assertThat;

class CacheStatsServiceIntegrationTest {
    private CacheStatsService service;
    @BeforeEach
    void setUp(){
        Cache cache = new Cache();
        cache.setEntry("hello", "bye");
        cache.isQueryInCache("hey");
        cache.isQueryInCache("hello");
        service = new CacheStatsService(cache);
    }

    @Test
    void whenGetCacheHits_thenReturnCacheHits(){
        assertThat(service.getCacheHits()).isEqualTo(new ResponseEntity<>(1, HttpStatus.OK));
    }

    @Test
    void whenGetCacheMisses_thenReturnCacheMisses(){
        assertThat(service.getCacheMisses()).isEqualTo(new ResponseEntity<>(1, HttpStatus.OK));
    }

    @Test
    void whenGetCacheRequests_thenReturnCacheRequests(){
        assertThat(service.getCacheRequests()).isEqualTo(new ResponseEntity<>(2, HttpStatus.OK));
    }
}
