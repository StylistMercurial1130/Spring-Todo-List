package com.personal.todoapp.Models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiToken {
    @JsonProperty("authToken") public final String token;

    public ApiToken(String token) {
        this.token = token;
    }
}
