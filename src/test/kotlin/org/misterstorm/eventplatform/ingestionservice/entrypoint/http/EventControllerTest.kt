package org.misterstorm.eventplatform.ingestionservice.entrypoint.http

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
class EventControllerTest(
    @Autowired val mockMvc: MockMvc
) {

    @Test
    fun `should return 400 when eventId is blank`() {

        val json = """
        {
          "eventId": "",
          "eventType": "PAYMENT_CREATED",
          "timestamp": "2026-03-29T12:00:00Z",
          "source": "test",
          "data": {}
        }
        """

        mockMvc.perform(
            post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.errorCode").value("MALFORMED_REQUEST"))
            .andExpect(jsonPath("$.description").exists())
    }
}