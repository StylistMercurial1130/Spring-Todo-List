package com.personal.todoapp.Models.shared;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskStatus {
    NOT_STARTED("not started"),
    IN_PROGRESS("in progress"),
    DONE("done");

    private final String statusString;

    TaskStatus(String taskStatus) {
        this.statusString = taskStatus;
    }

    public static TaskStatus fromString(String status) {
        return Arrays
        .stream(values())
        .filter(s -> s.getStatusString().equalsIgnoreCase(status))
        .findAny()
        .orElseThrow();
    }

    @JsonValue
    public  String getStatusString() {
        return statusString;
    }
}
