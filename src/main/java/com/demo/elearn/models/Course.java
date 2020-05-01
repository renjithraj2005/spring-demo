package com.demo.elearn.models;

import javax.persistence.*;

import com.demo.elearn.models.enums.PriceStrategy;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import java.util.*;

@NaturalIdCache
@Entity(name =  "Course")
@Table(name = "course")
@JsonPropertyOrder({ "name", "description","strategy_type","created" })
public class Course extends BaseModel {

    @NaturalId
    @Column(name="uuid", unique = true)
    private String uuid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @JsonProperty("strategy_type")
    private PriceStrategy strategyType;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="price", unique = true)
    private Price price;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Subscription> subscriptions;

    Course(){

    }
    public Course(String name, String description, PriceStrategy strategyType){
        this.name = name;
        this.description = description;
        this.strategyType = strategyType;
        this.subscriptions = new HashSet<Subscription>();
        this.price = null;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PriceStrategy getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(PriceStrategy strategyType) {
        this.strategyType = strategyType;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Set<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

}