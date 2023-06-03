package com.example.laba5;

import com.example.laba5.constans.CourierAbility;

public class DocPackage extends Package{
    public DocPackage(String SourceAddress, String DestinationAddress) {
        super(SourceAddress, DestinationAddress);
        this.fragility = false;
        this.SourceAddress = SourceAddress;
        this.DestinationAddress = DestinationAddress;
        this.requirements = CourierAbility.DOCUMENT_DELIVERY;
    }

    public String getSourceAddress() {
        return SourceAddress;
    }

    public String getDestinationAddress() {
        return DestinationAddress;
    }

    public String getType() {
        return "Д";
    }
}
