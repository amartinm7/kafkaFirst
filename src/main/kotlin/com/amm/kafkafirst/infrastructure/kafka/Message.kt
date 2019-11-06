package com.amm.kafkafirst.infrastructure.kafka

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class Message @JsonCreator
constructor(@param:JsonProperty("code") var code: Int, @param:JsonProperty("message") var message: String?) {

    override fun toString(): String {
        val sb = StringBuffer("Message{")
        sb.append("code=").append(code)
        sb.append(", message='").append(message).append('\'')
        sb.append('}')
        return sb.toString()
    }
}