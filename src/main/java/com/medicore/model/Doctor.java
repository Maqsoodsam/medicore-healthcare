package com.medicore.model;

public class Doctor {

    private Long id;
    private String name;
    private String specialisation;

    public Doctor() {
    }

    public Doctor(Long id, String name, String specialisation) {
        this.id = id;
        this.name = name;
        this.specialisation = specialisation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }
}
