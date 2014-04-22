package com.smartgrid.model.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MeteringVo implements Serializable {

	private String deviceName;
	private String tipoMedicao; // diario, semanal, mensal
	private Date date_start;
	private Date date_stop;
	private List<Float> consumptionValue;	
	private List<Float> generationValue;
	
	// atributos sao calculados internamente na classe, nao tem set, so get
	private List<Float> consumptionPrice; 
	private List<Float> generationPrice;
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getTipoMedicao() {
		return tipoMedicao;
	}
	public void setTipoMedicao(String tipoMedicao) {
		this.tipoMedicao = tipoMedicao;
	}
	public Date getDate_start() {
		return date_start;
	}
	public void setDate_start(Date date_start) {
		this.date_start = date_start;
	}
	public Date getDate_stop() {
		return date_stop;
	}
	public void setDate_stop(Date date_stop) {
		this.date_stop = date_stop;
	}
	public List<Float> getConsumptionValue() {
		return consumptionValue;
	}
	public void setConsumptionValue(List<Float> consumptionValue) {
		this.consumptionValue = consumptionValue;
	}
	public List<Float> getGenerationValue() {
		return generationValue;
	}
	public void setGenerationValue(List<Float> generationValue) {
		this.generationValue = generationValue;
	}
	public List<Float> getConsumptionPrice() {
		return consumptionPrice;
	}
	public List<Float> getGenerationPrice() {
		return generationPrice;
	}
}
