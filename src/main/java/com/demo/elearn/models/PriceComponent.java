package com.demo.elearn.models;

import com.demo.elearn.models.enums.PriceComponentType;
import com.demo.elearn.models.enums.ValueType;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.HashMap;

@Entity(name =  "PriceComponent")
@Table(name = "price_component")
@JsonIgnoreProperties({"created","value","valueType"})
@JsonPropertyOrder({ "country_code", "component_name","value_dict" })
public class PriceComponent extends BaseModel {

        @Column(name = "country_code", nullable = false)
        @JsonProperty("country_code")
        private String countryCode;

        @Column(name = "component_name", nullable = false)
        @JsonProperty("component_name")
        private PriceComponentType componentName;

        @Enumerated(EnumType.STRING)
        private ValueType valueType;

        @Column(name = "value", nullable = false)
        private double value;

        @JsonBackReference
        @ManyToOne
        @JoinColumn(name = "price_id", nullable = false)
        private Price price;

        PriceComponent(){}

        public PriceComponent(String countryCode, PriceComponentType componentName, ValueType valueType, double value) {
            this.countryCode = countryCode;
            this.componentName = componentName;
            this.valueType = valueType;
            this.value = value;
        }

        @JsonGetter
        @JsonProperty("value_dict")
        public HashMap<String,Object> getValueObject(){
            HashMap<String,Object> valueObj = new HashMap<>();
            valueObj.put("type",valueType);
            valueObj.put("value",value);
            return valueObj;
        }
        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public PriceComponentType getComponentName() {
            return componentName;
        }

        public void setComponentName(PriceComponentType componentName) {
            this.componentName = componentName;
        }

        public ValueType getValueType() {
            return valueType;
        }

        public void setValueType(ValueType valueType) {
            this.valueType = valueType;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public Price getPrice() {
            return price;
        }

        public void setPrice(Price price) {
            this.price = price;
        }


        
}