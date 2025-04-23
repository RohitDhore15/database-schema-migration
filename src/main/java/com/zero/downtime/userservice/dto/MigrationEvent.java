package com.zero.downtime.userservice.dto;

public class MigrationEvent {
    private String action;

    public MigrationEvent() {}

    public MigrationEvent(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
