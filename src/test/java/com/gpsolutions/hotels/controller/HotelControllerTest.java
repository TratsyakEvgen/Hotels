package com.gpsolutions.hotels.controller;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class HotelControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll_shouldReturn20Values() throws Exception {
        mockMvc.perform(get("/hotels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(20));
    }

    @Test
    void getAll_shouldReturnCorrectJSON() throws Exception {
        mockMvc.perform(get("/hotels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].name").value("Sunset Paradise"))
                .andExpect(jsonPath("$.[0].description")
                        .value("A luxurious beachfront resort with stunning ocean views."))
                .andExpect(jsonPath("$.[0].address").value("123 Ocean Drive, Miami, 33139, USA"))
                .andExpect(jsonPath("$.[0].phone").value("+1 305 123 4567"));
    }

    @Test
    void getAll_shouldReturnNullForOptionalDescription() throws Exception {
        mockMvc.perform(get("/hotels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[20].description").doesNotExist());
    }

    @Test
    void find_shouldReturnCorrectJSON() throws Exception {
        mockMvc.perform(get("/hotels/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Sunset Paradise"))
                .andExpect(jsonPath("$.brand").value("Paradise Resorts"))
                .andExpect(jsonPath("$.address.houseNumber").value(123))
                .andExpect(jsonPath("$.address.street").value("Ocean Drive"))
                .andExpect(jsonPath("$.address.city").value("Miami"))
                .andExpect(jsonPath("$.address.county").value("USA"))
                .andExpect(jsonPath("$.address.postCode").value("33139"))
                .andExpect(jsonPath("$.contacts.phone").value("+1 305 123 4567"))
                .andExpect(jsonPath("$.contacts.email").value("info@sunsetparadise.com"))
                .andExpect(jsonPath("$.arrivalTime.checkIn").value("15:00"))
                .andExpect(jsonPath("$.arrivalTime.checkOut").value("11:00"))
                .andExpect(jsonPath("$.amenities[0]").value("On-site restaurant"))
                .andExpect(jsonPath("$.amenities[1]").value("Free parking"))
                .andExpect(jsonPath("$.amenities[2]").value("Fitness center"))
                .andExpect(jsonPath("$.amenities[3]").value("Non-smoking rooms"))
                .andExpect(jsonPath("$.amenities[4]").value("Business center"))
                .andExpect(jsonPath("$.amenities[5]").value("Room service"))
                .andExpect(jsonPath("$.amenities[6]").value("Free WiFi"));
    }

    @Test
    void find_shouldReturnNullForOptionalCheckOut() throws Exception {
        mockMvc.perform(get("/hotels/20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.arrivalTime.checkOut").doesNotExist());
    }

    @Test
    void find_shouldReturnNotFoundForNotExistValue() throws Exception {
        mockMvc.perform(get("/hotels/21"))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_shouldReturnCorrectJSON() throws Exception {
        mockMvc.perform(post("/hotels")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"name\":\"DoubleTree by Hilton Minsk\",\"description\":" +
                                "\"The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian" +
                                " capital and stunning views of Minsk city from the hotel's 20th floor ...\"," +
                                "\"brand\":\"Hilton\",\"address\":{\"houseNumber\": 9,\"street\":\"Pobediteley Avenue\"," +
                                "\"city\": \"Minsk\",\"county\":\"Belarus\",\"postCode\":\"220004\"},\"contacts\":" +
                                "{\"phone\":\"+375 17 309-80-00\",\"email\":\"doubletreeminsk.info@hilton.com\"}," +
                                "\"arrivalTime\": {\"checkIn\": \"14:00\",\"checkOut\":\"12:00\"}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("DoubleTree by Hilton Minsk"))
                .andExpect(jsonPath("$.description")
                        .value("The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the " +
                                "Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ..."))
                .andExpect(jsonPath("$.address").value("9 Pobediteley Avenue, Minsk, 220004, Belarus"))
                .andExpect(jsonPath("$.phone").value("+375 17 309-80-00"));
    }

    @Test
    void create_shouldSaveWithoutCheckOut() throws Exception {
        mockMvc.perform(post("/hotels")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"name\":\"DoubleTree by Hilton Minsk\",\"description\":" +
                                "\"The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian" +
                                " capital and stunning views of Minsk city from the hotel's 20th floor ...\"," +
                                "\"brand\":\"Hilton\",\"address\":{\"houseNumber\": 9,\"street\":\"Pobediteley Avenue\"," +
                                "\"city\": \"Minsk\",\"county\":\"Belarus\",\"postCode\":\"220004\"},\"contacts\":" +
                                "{\"phone\":\"+375 17 309-80-00\",\"email\":\"doubletreeminsk.info@hilton.com\"}," +
                                "\"arrivalTime\": {\"checkIn\": \"14:00\"}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("DoubleTree by Hilton Minsk"))
                .andExpect(jsonPath("$.description")
                        .value("The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the " +
                                "Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ..."))
                .andExpect(jsonPath("$.address").value("9 Pobediteley Avenue, Minsk, 220004, Belarus"))
                .andExpect(jsonPath("$.phone").value("+375 17 309-80-00"));
    }

    @Test
    void create_shouldReturnNullForOptionalDescription() throws Exception {
        mockMvc.perform(post("/hotels")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"name\":\"DoubleTree by Hilton Minsk\"," +
                                "\"brand\":\"Hilton\",\"address\":{\"houseNumber\": 9,\"street\":\"Pobediteley Avenue\"," +
                                "\"city\": \"Minsk\",\"county\":\"Belarus\",\"postCode\":\"220004\"},\"contacts\":" +
                                "{\"phone\":\"+375 17 309-80-00\",\"email\":\"doubletreeminsk.info@hilton.com\"}," +
                                "\"arrivalTime\": {\"checkIn\": \"14:00\",\"checkOut\":\"12:00\"}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("DoubleTree by Hilton Minsk"))
                .andExpect(jsonPath("$.description").doesNotExist())
                .andExpect(jsonPath("$.address").value("9 Pobediteley Avenue, Minsk, 220004, Belarus"))
                .andExpect(jsonPath("$.phone").value("+375 17 309-80-00"));
    }

    @Test
    void create_shouldReturnBadRequestIfNestedObjectsAreNotExist() throws Exception {
        mockMvc.perform(post("/hotels")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"name\":\"DoubleTree by Hilton Minsk\"," +
                                "\"brand\":\"Hilton\"}}}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.error").value(allOf(
                        containsString("Contacts time must not be null"),
                        containsString("Arrival time must not be null"),
                        containsString("Address must not be null"))))
                .andExpect(jsonPath("$.path").value("/hotels"));
    }

    @Test
    void create_shouldReturnBadRequestIfDataIsNotValid() throws Exception {
        mockMvc.perform(post("/hotels")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"name\":\"\"," +
                                "\"brand\":\"\",\"address\":{\"houseNumber\": -1,\"street\":\"\"," +
                                "\"city\": \"\",\"county\":\"\",\"postCode\":\"\"},\"contacts\":" +
                                "{\"phone\":\"\",\"email\":\"\"}," +
                                "\"arrivalTime\": {\"checkIn\": \"\"}}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.error").value(allOf(
                        containsString("Name must not be blank"),
                        containsString("Brand must not be blank"),
                        containsString("House number must be equals or greater than 0"),
                        containsString("Street must not be blank"),
                        containsString("City must not be blank"),
                        containsString("Post code must not be blank"),
                        containsString("Phone number must not be blank"),
                        containsString("Email must not be blank"),
                        containsString("Check-in time must not be null"))))
                .andExpect(jsonPath("$.path").value("/hotels"));
    }

    @ParameterizedTest
    @MethodSource("getSearchSource")
    public <T> void testListContainsObjectWithIdAndName(String parameters, Matcher<? super T> matcherId) throws Exception {
        mockMvc.perform(get("/search" + parameters))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", matcherId));
    }

    static Stream<Arguments> getSearchSource() {
        return Stream.of(
                Arguments.of("", iterableWithSize(20)),
                Arguments.of("?city=", iterableWithSize(20)),
                Arguments.of("?city=Miami", containsInAnyOrder(is(1))),
                Arguments.of("?city=miami", containsInAnyOrder(is(1))),
                Arguments.of("?city=Miami&city=Miami", containsInAnyOrder(is(1))),
                Arguments.of("?city=Miami&city=Kyoto", containsInAnyOrder(is(1), is(20))),
                Arguments.of("?city=Miami,Kyoto", containsInAnyOrder(is(1), is(20))),
                Arguments.of("?brand=Ocean Resorts",
                        containsInAnyOrder(is(15), is(16), is(18), is(19))),
                Arguments.of("?brand=ocean resorts",
                        containsInAnyOrder(is(15), is(16), is(18), is(19))),
                Arguments.of("?brand=Ocean Resorts&city=Miami", iterableWithSize(0)),
                Arguments.of("?county=USA",
                        containsInAnyOrder(is(1), is(2), is(3), is(8), is(12))),
                Arguments.of("?county=usa",
                        containsInAnyOrder(is(1), is(2), is(3), is(8), is(12))),
                Arguments.of("?county=usa&city=Miami", containsInAnyOrder(is(1))),
                Arguments.of("?amenities=Free WiFi",
                        containsInAnyOrder(is(1), is(2), is(10), is(12))),
                Arguments.of("?amenities=free wifi",
                        containsInAnyOrder(is(1), is(2), is(10), is(12))),
                Arguments.of("?amenities=free wifi&city=Miami&county=usa&brand=paradise resorts",
                        containsInAnyOrder(is(1)))

        );
    }
}