package ua.tqs.hw1.data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Cache {
    private static final Logger logger = LoggerFactory.getLogger(Cache.class);
    private int hits;
    private int misses;
    private Map<String, CacheEntry> map;

    public Cache(){
        this.hits = 0;
        this.misses = 0;
        this.map = new HashMap<>();
    }

    public String getValue(String query){
        logger.info("Fetching value from cache...");
        return map.get(query).getValue();
    }


    public boolean isQueryInCache(String query){
        boolean contains = map.containsKey(query);
        if (contains) {
            hits += 1;
            logger.info("Cache Hit!");
        }
        else {
            logger.info("Cache Miss!");
            misses += 1;
        }
        return contains;
    }
    public Map<String, CacheEntry> getMap() {
        return map;
    }
    public void setEntry(String query, String value){
        logger.info("Storing new entry in cache...");
        map.put(query, new CacheEntry(value));
    }

    public void setEntry(String query, String value, Long expirationTime){
        logger.info("Storing new entry in cache...");
        CacheEntry entry = new CacheEntry(value);
        entry.setExpirationTime(expirationTime);
        map.put(query, entry);
    }

    public void cleanExpiredEntries(){
        logger.info("Cleaning cache...");
        map.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }

    public List<String> getExpiredEntries(){
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, CacheEntry> entry : map.entrySet()){
            CacheEntry cacheEntry = entry.getValue();
            if (cacheEntry.isExpired())
                list.add(entry.getKey());
        }
        return list;
    }

    public int getHits(){return hits;}
    public int getMisses(){return misses;}
    public int getRequests(){return hits + misses;}

    public void setHits(int hits) {
        this.hits = hits;
    }

    public void setMisses(int misses) {
        this.misses = misses;
    }

    public void setMap(Map<String, CacheEntry> map) {
        this.map = map;
    }
}
