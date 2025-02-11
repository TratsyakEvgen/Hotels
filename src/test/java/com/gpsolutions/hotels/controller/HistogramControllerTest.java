package com.gpsolutions.hotels.controller;

import com.gpsolutions.hotels.configuration.PostgresTestContainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ImportTestcontainers(PostgresTestContainer.class)
@ActiveProfiles("production")
@Transactional
class HistogramControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void groupBy_Brand() throws Exception {
        mockMvc.perform(get("/histogram/brand"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.['Alpine Escapes']").value(5))
                .andExpect(jsonPath("$.['Metropolitan Hotels']").value(1))
                .andExpect(jsonPath("$.['Paradise Resorts']").value(6))
                .andExpect(jsonPath("$.['City Hotels']").value(1))
                .andExpect(jsonPath("$.['Nature Stays']").value(1))
                .andExpect(jsonPath("$.['Garden Stays']").value(1))
                .andExpect(jsonPath("$.['Lake Resorts']").value(1))
                .andExpect(jsonPath("$.['Ocean Resorts']").value(4));
    }

    @Test
    void groupBy_City() throws Exception {
        mockMvc.perform(get("/histogram/city"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.['Banff']").value(1))
                .andExpect(jsonPath("$.['New York']").value(4))
                .andExpect(jsonPath("$.['Bali']").value(1))
                .andExpect(jsonPath("$.['Cotswolds']").value(1))
                .andExpect(jsonPath("$.['Kyoto']").value(1))
                .andExpect(jsonPath("$.['Whistler']").value(1))
                .andExpect(jsonPath("$.['Phuket']").value(1))
                .andExpect(jsonPath("$.['Dubai']").value(1))
                .andExpect(jsonPath("$.['Miami']").value(1))
                .andExpect(jsonPath("$.['Maldives']").value(1))
                .andExpect(jsonPath("$.['Cancun']").value(1))
                .andExpect(jsonPath("$.['Tokyo']").value(1))
                .andExpect(jsonPath("$.['London']").value(1))
                .andExpect(jsonPath("$.['Zermatt']").value(1))
                .andExpect(jsonPath("$.['Paris']").value(1))
                .andExpect(jsonPath("$.['Sydney']").value(1))
                .andExpect(jsonPath("$.['Geneva']").value(1));
    }

    @Test
    void groupBy_County() throws Exception {
        mockMvc.perform(get("/histogram/county"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.['Canada']").value(2))
                .andExpect(jsonPath("$.['Maldives']").value(1))
                .andExpect(jsonPath("$.['USA']").value(5))
                .andExpect(jsonPath("$.['Japan']").value(2))
                .andExpect(jsonPath("$.['UK']").value(2))
                .andExpect(jsonPath("$.['UAE']").value(1))
                .andExpect(jsonPath("$.['Mexico']").value(1))
                .andExpect(jsonPath("$.['France']").value(1))
                .andExpect(jsonPath("$.['Thailand']").value(1))
                .andExpect(jsonPath("$.['Switzerland']").value(2))
                .andExpect(jsonPath("$.['Indonesia']").value(1))
                .andExpect(jsonPath("$.['Australia']").value(1));
    }

    @Test
    void groupBy_Amenities() throws Exception {
        mockMvc.perform(get("/histogram/amenities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.['Business center']").value(3))
                .andExpect(jsonPath("$.['Fitness center']").value(3))
                .andExpect(jsonPath("$.['Concierge']").value(3))
                .andExpect(jsonPath("$.['Pet-friendly rooms']").value(2))
                .andExpect(jsonPath("$.['Room service']").value(3))
                .andExpect(jsonPath("$.['Free parking']").value(5))
                .andExpect(jsonPath("$.['Meeting rooms']").value(2))
                .andExpect(jsonPath("$.['Free WiFi']").value(4))
                .andExpect(jsonPath("$.['Non-smoking rooms']").value(4))
                .andExpect(jsonPath("$.['On-site restaurant']").value(3));
    }

    @Test
    void groupBy_shouldReturnBadRequestIfUnknownField() throws Exception {
        mockMvc.perform(get("/histogram/name"))
                .andExpect(status().isBadRequest());
    }

}