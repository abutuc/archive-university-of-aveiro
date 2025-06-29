package ua.tqs.hw1.data;

import org.junit.jupiter.api.Test;
import ua.tqs.hw1.utils.ConfigUtils;

import static org.assertj.core.api.Assertions.assertThat;
class CacheEntryUnitTest {
    private final long cacheTTL = Long.parseLong(ConfigUtils.getPropertyFromConfig("cache_ttl_testing"));

    @Test
    void isExpired() throws InterruptedException {
        CacheEntry entry = new CacheEntry("[{\"name\":\"Aveiro\",\"local_names\":{\"lt\":\"Aveiras\",\"ru\":\"Авейру\",\"pt\":\"Aveiro\",\"el\":\"Αβέιρο\",\"hu\":\"Aveiro\",\"ar\":\"آويرو\"},\"lat\":40.640496,\"lon\":-8.6537841,\"country\":\"PT\"}]");
        entry.setExpirationTime(cacheTTL);
        Thread.sleep(cacheTTL + 100);
        assertThat(entry.isExpired()).isTrue();
    }

    @Test
    void isNotExpired() throws InterruptedException {
        CacheEntry entry = new CacheEntry("[{\"name\":\"Aveiro\",\"local_names\":{\"lt\":\"Aveiras\",\"ru\":\"Авейру\",\"pt\":\"Aveiro\",\"el\":\"Αβέιρο\",\"hu\":\"Aveiro\",\"ar\":\"آويرو\"},\"lat\":40.640496,\"lon\":-8.6537841,\"country\":\"PT\"}]");
        entry.setExpirationTime(cacheTTL);
        Thread.sleep(cacheTTL - 50);
        assertThat(entry.isExpired()).isFalse();
    }
}