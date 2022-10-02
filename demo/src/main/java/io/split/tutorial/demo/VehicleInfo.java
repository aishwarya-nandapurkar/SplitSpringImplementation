package io.split.tutorial.demo;

/**
 * 
 * @author Aishwarya
 *
 */
public class VehicleInfo {
	private String vin;
	private String make;
	private String model;
	private String year;
	private String productionDate;
	private String mno;
		
	public VehicleInfo(String vin, String make, String model, String year, String productionDate, String mno) {
		super();
		this.vin = vin;
		this.make = make;
		this.model = model;
		this.year = year;
		this.productionDate = productionDate;
		this.mno = mno;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}
	public String getMno() {
		return mno;
	}
	public void setMno(String mno) {
		this.mno = mno;
	}
	
}
