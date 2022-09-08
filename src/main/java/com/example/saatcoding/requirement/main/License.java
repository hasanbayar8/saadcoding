package com.example.saatcoding.requirement.main;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @SequenceGenerator(
            name = "license_sequence",
            sequenceName = "license_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "licenseSequence"
    )
    private String name;
    private LocalDate startTime;
    private LocalDate endTime;
    @JsonIgnore
    @ManyToMany(mappedBy = "licenses")
    private List<Content> contents = new ArrayList<>() ;

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }


    public License() {

    }public License(Long id,
                   List<Content> contents,
                   String name,
                   LocalDate startTime,
                   LocalDate endTime) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.contents = contents;

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

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "License{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof License license)) return false;

        if (getId() != null ? !getId().equals(license.getId()) : license.getId() != null) return false;
        if (getContents() != null ? !getContents().equals(license.getContents()) : license.getContents() != null)
            return false;
        if (getName() != null ? !getName().equals(license.getName()) : license.getName() != null) return false;
        if (getStartTime() != null ? !getStartTime().equals(license.getStartTime()) : license.getStartTime() != null)
            return false;
        return getEndTime() != null ? getEndTime().equals(license.getEndTime()) : license.getEndTime() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getContents() != null ? getContents().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getStartTime() != null ? getStartTime().hashCode() : 0);
        result = 31 * result + (getEndTime() != null ? getEndTime().hashCode() : 0);
        return result;
    }
}
