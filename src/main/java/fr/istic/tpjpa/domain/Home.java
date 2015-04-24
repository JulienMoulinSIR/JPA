package fr.istic.tpjpa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Home")
public class Home {

	@Id
	@GeneratedValue
	private int numHome;
	private int size;
	private int nbParts;
	@ManyToOne
	Person person;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="home")
	private List<Device> devices;
	
	public Home(){
		super();
		devices = new ArrayList<Device>();
	}
	
	public Home(int size, int nbParts){
		this.size = size;
		this.nbParts = nbParts;
		devices = new ArrayList<Device>();
	}
	
	public int getNumHome() {
		return numHome;
	}

	public void setNumHome(int numHome) {
		this.numHome = numHome;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNbParts() {
		return nbParts;
	}

	public void setNbParts(int nbParts) {
		this.nbParts = nbParts;
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
	
	public boolean removeDevice(Device device) {
		return devices.remove(device);
	}

	public void addDevice(Device device) {
		devices.add(device);
		device.setHome(this);
	}

	@Override
	public String toString() {
		return	"Home [num="+numHome
					+", size="+size
					+", nbParts="+nbParts
					+"]";
	}

}
