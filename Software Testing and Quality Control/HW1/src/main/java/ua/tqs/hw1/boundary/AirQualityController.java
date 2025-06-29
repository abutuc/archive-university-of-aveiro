package ua.tqs.hw1.boundary;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.tqs.hw1.data.AirQuality;
import ua.tqs.hw1.service.AirQualityService;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/air_quality")
public class AirQualityController {
    private final AirQualityService service;
    public AirQualityController(AirQualityService service){this.service = service;}
    @GetMapping("/now")
    public ResponseEntity<AirQuality> getLocationAirQualityNow(@RequestParam String location) throws URISyntaxException, IOException, ParseException {
        return service.getLocationAirQualityNow(location);
    }

    @GetMapping("/forecast")
    public ResponseEntity<List<AirQuality>> getLocationAirQualityForecast(@RequestParam String location) throws URISyntaxException, IOException, ParseException {
        return service.getLocationAirQualityForecast(location);
    }


}
