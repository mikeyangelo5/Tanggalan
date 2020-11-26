package com.example.calendar;

public class Event {
    private String date;
    private String event;
    private String address;

    public Event(String date, String event, String address) {
        this.date = date;
        this.event = event;
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
