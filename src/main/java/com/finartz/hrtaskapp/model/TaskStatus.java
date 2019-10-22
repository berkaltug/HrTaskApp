package com.finartz.hrtaskapp.model;

public enum TaskStatus {
    INPROGRESS("üzerinde çalışıyor"),
    TODO("yapılacak"),
    DONE("görev yerine getirildi");

    private String message;

    TaskStatus(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }
}
