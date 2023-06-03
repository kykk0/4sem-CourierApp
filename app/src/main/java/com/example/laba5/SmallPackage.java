package com.example.laba5;

public class SmallPackage extends Package{
    public SmallPackage(String size, boolean fragility, String SourceAddress, String DestinationAddress) {
        super(SourceAddress, DestinationAddress);
        this.fragility = fragility;
        this.size = size;
    }

    public String getType() {
        return "М";
    }
}
