package com.example.laba5;

import com.example.laba5.constans.CourierAbility;

public class BigPackage extends Package {

    public BigPackage(String size, boolean fragility, double weight, String SourceAddress, String DestinationAddress) {
        super(SourceAddress, DestinationAddress);
        this.fragility = fragility;
        this.size = size;
        this.weight = weight;
        this.setRequirements(CourierAbility.CAR_DELIVERY);
    }

    private double weight;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight){
        this.weight = weight;
    }

    public String getType() {
        return "Б";
    }
}