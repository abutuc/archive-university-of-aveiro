package ua.tqs.hw1.boundary;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import ua.tqs.hw1.service.CacheStatsService;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CacheStatsController.class)
class CacheStatsControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CacheStatsService service;

    @Test
    void whenGetCacheHits_thenReturnCacheHits() throws Exception {
        Mockito.when(service.getCacheHits()).thenReturn(new ResponseEntity<>(2, HttpStatus.OK));
        mvc.perform(get("/cache/stats/hits").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(2)));
        Mockito.verify(service, Mockito.times(1)).getCacheHits();
    }

    @Test
    void whenGetCacheMisses_thenReturnCacheHits() throws Exception {
        Mockito.when(service.getCacheMisses()).thenReturn(new ResponseEntity<>(2, HttpStatus.OK));
        mvc.perform(get("/cache/stats/misses").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(2)));
        Mockito.verify(service, Mockito.times(1)).getCacheMisses();
    }

    @Test
    void whenGetCacheRequests_thenReturnCacheRequests() throws Exception {
        Mockito.when(service.getCacheRequests()).thenReturn(new ResponseEntity<>(2, HttpStatus.OK));
        mvc.perform(get("/cache/stats/requests").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(2)));
        Mockito.verify(service, Mockito.times(1)).getCacheRequests();
    }
}