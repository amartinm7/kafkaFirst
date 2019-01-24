package com.amm.kafkafirst.interfaces;

import com.amm.kafkafirst.application.ConsumerServiceTest;
import com.amm.kafkafirst.infrastructure.HttpParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@EmbeddedKafka(partitions = 1,
        topics = ProviderControllerTest.MY_TOPIC)
public class ProviderControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(ProviderControllerTest.class);
    static final String MY_TOPIC = "myTopic.t";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void publishMessage() throws Exception {
        // given
        final String username = "";
        final String password = "password";

        // when
        final MultiValueMap<String,String> user = new LinkedMultiValueMap<>();
        user.put(HttpParams.USER_NAME, Collections.singletonList(username));

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpParams.X_PASSWORD, password);

        final String expectedMessage = "";

        // Then
        this.mockMvc.perform(get(HttpParams.URL_PROVIDER)
                .contentType(MediaType.APPLICATION_JSON)
                .params(user)
                .headers(httpHeaders)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedMessage)));
    }

    @Test
    public void subscribeMessage() throws Exception {
        // given
        final String username = "";
        final String password = "password";

        // when
        final MultiValueMap<String,String> user = new LinkedMultiValueMap<>();
        user.put(HttpParams.USER_NAME, Collections.singletonList(username));

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpParams.X_PASSWORD, password);

        final String expectedMessage = "";

        // Then
        this.mockMvc.perform(get(HttpParams.URL_CONSUMER)
                .contentType(MediaType.APPLICATION_JSON)
                .params(user)
                .headers(httpHeaders)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedMessage)));
    }

}
