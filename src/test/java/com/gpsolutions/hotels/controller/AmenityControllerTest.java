package com.gpsolutions.hotels.controller;

import com.gpsolutions.hotels.configuration.RestTemplateConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = RestTemplateConfiguration.class)
@AutoConfigureMockMvc
@Transactional
class AmenityControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void update_emptyAmenities() throws Exception {
        mockMvc.perform(post("/hotels/2/amenities")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("[]"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/hotels/2"))
                .andExpect(jsonPath("$.amenities", containsInAnyOrder(is("Free WiFi"))));
    }

    @Test
    void update_addExistingAmenity() throws Exception {
        mockMvc.perform(post("/hotels/2/amenities")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("[\"Free WiFi\"]"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/hotels/2"))
                .andExpect(jsonPath("$.amenities", containsInAnyOrder(is("Free WiFi"))));
    }

    @Test
    void update_addExistingAmenityWithDifferentCase() throws Exception {
        mockMvc.perform(post("/hotels/2/amenities")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("[\"Free Wifi\"]"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/hotels/2"))
                .andExpect(jsonPath("$.amenities", containsInAnyOrder(is("Free WiFi"))));
    }

    @Test
    void update_addAmenity() throws Exception {
        mockMvc.perform(post("/hotels/2/amenities")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("[\"Non-smoking rooms\"]"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/hotels/2"))
                .andExpect(jsonPath("$.amenities",
                        containsInAnyOrder(is("Free WiFi"), is("Non-smoking rooms"))));
    }

    @Test
    void update_addNewAmenity() throws Exception {
        mockMvc.perform(post("/hotels/2/amenities")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("[\"Free ice cream\"]"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/hotels/2"))
                .andExpect(jsonPath("$.amenities",
                        containsInAnyOrder(is("Free WiFi"), is("Free ice cream"))));
    }

    @Test
    void update_addDuplicateNewAmenities() throws Exception {
        mockMvc.perform(post("/hotels/2/amenities")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("[\"Free ice cream\",\"Free ice cream\"]"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/hotels/2"))
                .andExpect(jsonPath("$.amenities",
                        containsInAnyOrder(is("Free WiFi"), is("Free ice cream"))));
    }

    @Test
    void update_addAmenities() throws Exception {
        mockMvc.perform(post("/hotels/2/amenities")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("[\"Free ice cream\",\"Non-smoking rooms\"]"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/hotels/2"))
                .andExpect(jsonPath("$.amenities",
                        containsInAnyOrder(is("Free WiFi"), is("Free ice cream"), is("Non-smoking rooms"))));
    }
}