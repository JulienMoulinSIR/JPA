package fr.istic.tpjpa.domain;

import javax.persistence.Entity;

@Entity
public class Electronic extends Device{

	public Electronic(){
		super();
	}
	
	public Electronic(String name, int averageConso){
		super(name, averageConso);
	}
}
