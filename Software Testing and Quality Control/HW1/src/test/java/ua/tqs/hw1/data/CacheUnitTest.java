package ua.tqs.hw1.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.tqs.hw1.utils.ConfigUtils;

import static org.assertj.core.api.Assertions.assertThat;

class CacheUnitTest {
    private final long cacheTTL = Long.parseLong(ConfigUtils.getPropertyFromConfig("cache_ttl_testing"));
    private Cache cache;


    @BeforeEach
    void setUp(){
        cache = new Cache();
    }
    @Test
    void whenInit_thenAttributesShouldBeZeroOrEmpty(){
        assertThat(cache.getMap()).isEmpty();
        assertThat(cache.getHits()).isZero();
        assertThat(cache.getMisses()).isZero();
        assertThat(cache.getRequests()).isZero();
    }


    @Test
    void whenQueryInCache_thenIsQueryInCacheShouldReturnTrue(){
        cache.setEntry("api_call", "api_call_value", cacheTTL);

        assertThat(cache.isQueryInCache("api_call")).isTrue();
        assertThat(cache.getHits()).isOne();
        assertThat(cache.getMisses()).isZero();
        assertThat(cache.getRequests()).isOne();
    }

    @Test
    void whenQueryNotInCache_thenIsQueryInCacheShouldReturnFalse(){
        cache.setEntry("api_call", "api_call_value", cacheTTL);
        assertThat(cache.isQueryInCache("api_another_call")).isFalse();
        assertThat(cache.getHits()).isZero();
        assertThat(cache.getMisses()).isOne();
        assertThat(cache.getRequests()).isOne();
    }

    @Test
    void whenCleanExpiredEntries_thenExpiredEntriesShouldBeEmpty() throws InterruptedException {
        cache.setEntry("api_call", "api_call_value", cacheTTL);
        assertThat(cache.getExpiredEntries()).isEmpty();
        Thread.sleep(cacheTTL + 10);
        assertThat(cache.getExpiredEntries()).isNotEmpty();
        cache.cleanExpiredEntries();
        assertThat(cache.getExpiredEntries()).isEmpty();
    }








}