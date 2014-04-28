package com.smartgrid.model.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MeteringVo implements Serializable{
	
	private Integer idDevice;
	private String meteringDate;
	private Float consumption;
	private Float consumptionPrice;
	private Float generation;
	private Float generationPrice;
	private Float voltage;
	private Float current;
	private Float power;
	private Float battery;
	
	public Integer getIdDevice() {
		return idDevice;
	}
	public void setIdDevice(Integer idDevice) {
		this.idDevice = idDevice;
	}
	public String getMeteringDate() {
		return meteringDate;
	}
	public void setMeteringDate(String meteringDate) {
		this.meteringDate = meteringDate;
	}
	public Float getConsumption() {
		return consumption;
	}
	public void setConsumption(Float consumption) {
		this.consumption = consumption;
	}
	public Float getGeneration() {
		return generation;
	}
	public void setGeneration(Float generation) {
		this.generation = generation;
	}
	public Float getVoltage() {
		return voltage;
	}
	public void setVoltage(Float voltage) {
		this.voltage = voltage;
	}
	public Float getCurrent() {
		return current;
	}
	public void setCurrent(Float current) {
		this.current = current;
	}
	public Float getPower() {
		return power;
	}
	public void setPower(Float power) {
		this.power = power;
	}
	public Float getBattery() {
		return battery;
	}
	public void setBattery(Float battery) {
		this.battery = battery;
	}
	public Float getConsumptionPrice() {
		return consumptionPrice;
	}
	public void setConsumptionPrice(Float consumptionPrice) {
		this.consumptionPrice = consumptionPrice;
	}
	public Float getGenerationPrice() {
		return generationPrice;
	}
	public void setGenerationPrice(Float generationPrice) {
		this.generationPrice = generationPrice;
	}	
	
}
