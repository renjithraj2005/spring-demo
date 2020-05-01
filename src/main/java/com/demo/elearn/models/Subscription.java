package com.demo.elearn.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name =  "Subscription")
@Table(name = "subscription")
@JsonIgnoreProperties({"created"})
public class Subscription extends BaseModel{

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "months", nullable = false)
    private int months;

    @Column(name = "discount", nullable = false)
    private double discount;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    Subscription(){}

    public Subscription(String name, int months, double discount) {
        this.name = name;
        this.months = months;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    
}