package com.demo.elearn.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.demo.elearn.models.enums.Currency;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name =  "Price")
@Table(name = "price")
@JsonIgnoreProperties({"created"})
public class Price extends BaseModel{

        @Column(name = "base_price", nullable = false)
        @JsonProperty("base_price")
        private double basePrice;

        @Enumerated(EnumType.STRING)
        private Currency currency;

        @JsonManagedReference
        @OneToMany(fetch = FetchType.EAGER, mappedBy = "price", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonProperty("components")
        private Set<PriceComponent> priceComponent;

        @JsonBackReference
        @OneToOne(mappedBy = "price")
        private Course course;

        Price(){}

        public Price(double basePrice, Currency currency) {
            this.basePrice = basePrice;
            this.currency = currency;
            this.priceComponent = new HashSet<PriceComponent>();
        }


        public double getBasePrice() {
            return basePrice;
        }

        public void setBasePrice(double basePrice) {
            this.basePrice = basePrice;
        }

        public Currency getCurrency() {
            return currency;
        }

        public void setCurrency(Currency currency) {
            this.currency = currency;
        }

        public Set<PriceComponent> getPriceComponent() {
            return priceComponent;
        }

        public void setPriceComponent(Set<PriceComponent> priceComponent) {
            this.priceComponent = priceComponent;
        }

        public Course getCourse() {
            return course;
        }

        public void setCourse(Course course) {
            this.course = course;
        }


        

        
}