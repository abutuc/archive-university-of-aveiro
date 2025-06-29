package ua.tqs.hw1.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.tqs.hw1.service.CacheStatsService;

@RestController
@RequestMapping("/cache")
public class CacheStatsController {
    private final CacheStatsService service;
    @Autowired
    public CacheStatsController(CacheStatsService service){this.service=service;}
    @GetMapping("/stats/hits")
    public ResponseEntity<Integer> getCacheHits(){return service.getCacheHits();}

    @GetMapping("/stats/misses")
    public ResponseEntity<Integer> getCacheMisses(){return service.getCacheMisses();}

    @GetMapping("/stats/requests")
    public ResponseEntity<Integer> getCacheRequests(){
        return service.getCacheRequests();
    }

    @DeleteMapping("/stats/clear")
    public ResponseEntity<String> deleteStats(){return  service.deleteStats();}

}
