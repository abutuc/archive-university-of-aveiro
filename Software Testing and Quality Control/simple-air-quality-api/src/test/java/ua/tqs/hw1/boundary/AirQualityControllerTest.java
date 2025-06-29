package ua.tqs.hw1.boundary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import ua.tqs.hw1.data.AirQuality;
import ua.tqs.hw1.data.Coordinates;
import ua.tqs.hw1.service.AirQualityService;

import java.util.Date;
import java.util.HashMap;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AirQualityController.class)
class AirQualityControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private AirQualityService service;

    @Test
    void givenLocationAveiro_thenReturnAveiroAirQuality() throws Exception {
        Coordinates aveiroCoords = new Coordinates(40.640496, -8.6537841);
        HashMap<String, Double> components = new HashMap<>();
        components.put("co", 260.35);
        components.put("no", 0.0);
        components.put("no2", 2.1);
        components.put("o3", 101.57);
        components.put("so2", 1.31);
        components.put("pm2_5", 13.18);
        components.put("pm10", 22.69);
        components.put("nh3", 1.35);
        Date date = new Date(1680542963L*1000L);
        AirQuality aveiroAirQuality = new AirQuality("Aveiro", aveiroCoords, 3, components, date.toString());
        when(service.getLocationAirQualityNow(any())).thenReturn(new ResponseEntity<>(aveiroAirQuality, HttpStatus.OK));
        mvc.perform(get("/air_quality/now?location=Aveiro").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location", is(aveiroAirQuality.getLocation())))
                .andExpect(jsonPath("$.coordinates.lon", is(aveiroAirQuality.getCoordinates().getLon())))
                .andExpect(jsonPath("$.coordinates.lat", is(aveiroAirQuality.getCoordinates().getLat())))
                .andExpect(jsonPath("$.qualityIndex", is(aveiroAirQuality.getQualityIndex())))
                .andExpect(jsonPath("$.componentsConcentration", is(aveiroAirQuality.getComponentsConcentration())))
                .andExpect(jsonPath("$.date", is(aveiroAirQuality.getDate())));
        verify(service, times(1)).getLocationAirQualityNow("Aveiro");
    }

    @Test
    void givenInvalidLocation_thenReturnEmptyResponseAndStatusNotFound() throws Exception {
        when(service.getLocationAirQualityNow(any())).thenReturn(new ResponseEntity<>(new AirQuality(), HttpStatus.NO_CONTENT));
        mvc.perform(get("/air_quality/now?location=Rua Domingues").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.location", is(nullValue())))
                .andExpect(jsonPath("$.coordinates", is(nullValue())))
                .andExpect(jsonPath("$.qualityIndex", is(nullValue())))
                .andExpect(jsonPath("$.componentsConcentration", is(nullValue())))
                .andExpect(jsonPath("$.date", is(nullValue())));
    }
}