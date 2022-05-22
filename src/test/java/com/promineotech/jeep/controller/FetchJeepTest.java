package com.promineotech.jeep.controller;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.*;
import org.springframework.test.context.jdbc.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
        "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
        "classpath:flyway/migrations/V1.1__Jeep_Data.sql"},
        config = @SqlConfig(encoding = "utf-8"))
class FetchJeepTest {
    @Autowired
    @Getter
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int serverPort;

    @Test
    void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {
        JeepModel model = JeepModel.WRANGLER;
        String trim = "Sport";
        String uri = String.format("http://localhost:%d/jeeps?model=%s&trim=%s", serverPort, model, trim);
        ResponseEntity<List<Jeep>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}