package ru.itis.dis403.lab7.docker.controller;

public class MachineStatus {
    private double temp1;
    private double temp2;
    private double temp3;
    private double pressure;
    private double resource;

    public double getTemp1() {
        return temp1;
    }

    public void setTemp1(double temp1) {
        this.temp1 = temp1;
    }

    public double getTemp2() {
        return temp2;
    }

    public void setTemp2(double temp2) {
        this.temp2 = temp2;
    }

    public double getTemp3() {
        return temp3;
    }

    public void setTemp3(double temp3) {
        this.temp3 = temp3;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getResource() {
        return resource;
    }

    public void setResource(double resource) {
        this.resource = resource;
    }
}