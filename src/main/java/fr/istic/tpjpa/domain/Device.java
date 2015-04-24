package fr.istic.tpjpa.domain;

import javax.persistence.*;

@Entity
@Table(name = "Device")
public abstract class Device {

	@Id
	@GeneratedValue
	private int numDevice;
	private static String name;
	private static int averageConso;
	@ManyToOne
	private Home home;
	
	public Device(){
		super();
	}
	
	public Device(String name, int averageConso){
		this.name = name;
		this.averageConso = averageConso;
	}
	
	public int getNumDevice() {
		return numDevice;
	}

	public void setNumDevice(int numDevice) {
		this.numDevice = numDevice;
	}

	public static String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static int getAverageConso() {
		return averageConso;
	}

	public void setAverageConso(int averageConso) {
		this.averageConso = averageConso;
	}
	
	
	public Home getHome() {
		return home;
	}

	public void setHome(Home home) {
		this.home = home;
	}

	@Override
	public String toString() {
		return "Device "+this.getClass()
				+" [num="+numDevice
				+", name="+name
				+", average consomation="+averageConso
				+"]";
	}
	
	
}
