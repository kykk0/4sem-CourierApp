package com.example.laba5;

import com.example.laba5.constans.CourierAbility;

public  abstract class Package {

    public Package(String SourceAddress, String DestinationAddress) {
        this.SourceAddress = SourceAddress;
        this.DestinationAddress = DestinationAddress;
    }

    protected String SourceAddress;
    protected String DestinationAddress;
    protected String size;
    protected boolean fragility;
    protected CourierAbility requirements;

    public void setRequirements(CourierAbility requirements) {
        this.requirements = requirements;
    }

    public String getSize() {
        return size;
    }

    public boolean isFragility() {
        return fragility;
    }

    public CourierAbility getRequirements() {
        return requirements;
    }

    public String getSourceAddress() {
        return SourceAddress;
    }

    public String getDestinationAddress() {
        return DestinationAddress;
    }

    public abstract String getType();
}
