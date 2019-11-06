package com.amm.kafkafirst.infrastructure.interfaces

import com.amm.kafkafirst.infrastructure.configuration.HttpParams
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

import java.util.Collections

import org.hamcrest.Matchers.containsString
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@EmbeddedKafka(partitions = 1, topics = ["myTopic.t"])
class ProviderControllerTest {

    private val logger = LoggerFactory.getLogger(ProviderControllerTest::class.java)

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    @Throws(Exception::class)
    fun publishMessage() {
        // given
        val username = ""
        val password = "password"

        // when
        val user = LinkedMultiValueMap<String, String>()
        user[HttpParams.USER_NAME] = listOf(username)

        val httpHeaders = HttpHeaders()
        httpHeaders.add(HttpParams.X_PASSWORD, password)

        val expectedMessage = ""

        // Then
        this.mockMvc.perform(
            get(HttpParams.URL_PROVIDER)
                .contentType(MediaType.APPLICATION_JSON)
                .params(user)
                .headers(httpHeaders)
        ).andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().string(containsString(expectedMessage)))
    }

    @Test
    @Throws(Exception::class)
    fun subscribeMessage() {
        // given
        val username = ""
        val password = "password"

        // when
        val user = LinkedMultiValueMap<String, String>()
        user[HttpParams.USER_NAME] = listOf(username)

        val httpHeaders = HttpHeaders()
        httpHeaders.add(HttpParams.X_PASSWORD, password)

        val expectedMessage = ""

        // Then
        this.mockMvc.perform(
            get(HttpParams.URL_CONSUMER)
                .contentType(MediaType.APPLICATION_JSON)
                .params(user)
                .headers(httpHeaders)
        ).andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().string(containsString(expectedMessage)))
    }
}
