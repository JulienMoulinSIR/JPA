package fr.istic.tpjpa.domain;

import javax.persistence.Entity;

@Entity
public class Heater extends Device{

	public Heater(){
		super();
	}
	
	public Heater(String name, int averageConso){
		super(name, averageConso);
	}
}
