package ua.tqs.hw1.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.tqs.hw1.data.Cache;

import java.util.HashMap;

@Service
public class CacheStatsService {
    private final Cache cache;
    @Autowired
    public CacheStatsService(Cache cache){
        this.cache = cache;
    }
    public ResponseEntity<Integer> getCacheHits(){return new ResponseEntity<>(cache.getHits(), HttpStatus.OK);}
    public ResponseEntity<Integer> getCacheMisses(){return new ResponseEntity<>(cache.getMisses(), HttpStatus.OK);}
    public ResponseEntity<Integer> getCacheRequests(){return new ResponseEntity<>(cache.getRequests(), HttpStatus.OK);}

    public ResponseEntity<String> deleteStats(){
        cache.setHits(0);
        cache.setMisses(0);
        cache.setMap(new HashMap<>());
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
