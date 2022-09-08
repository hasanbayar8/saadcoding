package com.example.saatcoding.requirement.main;

public enum Status {
    Inprogress("Inprogress"),
    Published("Published");

    public final String label;

    private Status(String label) {
        this.label = label;
    }
    public static Status valueOfLabel(String label) {
        for (Status s : values()) {
            if (s.label.equals(label)) {
                return s;
            }
        }
        return Inprogress;

    }
}
