package ua.tqs.hw1.data;

import ua.tqs.hw1.utils.ConfigUtils;

public class CacheEntry {
    private static final Long EXPIRATION = Long.parseLong(ConfigUtils.getPropertyFromConfig("cache_ttl"));
    private final String value;
    private final Long creationTime;
    private Long expirationTime;


    public CacheEntry(String value){
        this.value = value;
        this.creationTime = System.currentTimeMillis();
        this.expirationTime = creationTime + EXPIRATION;
    }

    public String getValue(){
        return value;
    }

    public void setExpirationTime(Long expirationTime) {
        this.expirationTime = creationTime + expirationTime;
    }

    public boolean isExpired(){
        return System.currentTimeMillis() > expirationTime;
    }

}
