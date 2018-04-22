package com.github.anurag145.slick.estimote;

import com.estimote.sdk.MacAddress;
import com.estimote.sdk.cloud.model.Color;

public class EstimoteCloudBeaconDetails {

    private String beaconName;
    private Color beaconColor;
    private double batteryLife;
    private MacAddress MAC;

    public EstimoteCloudBeaconDetails(String beaconName, Color beaconColor, double batteryLife,MacAddress MAC) {
        this.beaconName = beaconName;
        this.beaconColor = beaconColor;
        this.batteryLife=batteryLife;
        this.MAC=MAC;
    }

    public String getBeaconName() {
        return beaconName;
    }

    public MacAddress getBeaconMacAddress(){
        return MAC;
    }

    public double getBatteryLife(){
        return batteryLife;
    }

    public Color getBeaconColor() {
        return beaconColor;
    }

    @Override
    public String toString() {
        return "BeaconName: " + getBeaconName() + "\nBeaconColor: " + getBeaconColor()+"\nBeaconBatteryLife:"+getBatteryLife()+"\nBeaconMacAddress: "+getBeaconMacAddress();
    }
}
