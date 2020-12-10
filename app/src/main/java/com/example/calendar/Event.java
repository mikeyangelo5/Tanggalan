package com.example.calendar;

public class Event {
    private static String tanggalMulai;
    private String tanggalSelesai;
    private String event;
    private String address;
    private String note;
    private int id;

    public Event(){

    }

    public Event(){

    }
    public Event(String tanggalMulai, String tanggalSelesai, String event, String address, String note) {
        this.tanggalMulai = tanggalMulai;
        this.tanggalSelesai = tanggalSelesai;
        this.event = event;
        this.address = address;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public static String getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(String tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public String getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(String tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
