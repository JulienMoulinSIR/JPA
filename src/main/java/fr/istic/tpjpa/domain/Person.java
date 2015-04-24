package fr.istic.tpjpa.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Person")
public class Person {
	
	@Id
	@GeneratedValue
	private int numPerson;
	private String name;
	private String firstName;
	private char genre; // m -> man, w -> woman
	private String mail;
	private Date birthDay;
	@ManyToMany
	@JoinTable(name = "Friends",
		joinColumns = @JoinColumn(name = "PERSON1", referencedColumnName = "numPerson"), 
		inverseJoinColumns = @JoinColumn(name = "PERSON2", referencedColumnName = "numPerson")
	)
	private List<Person> friends;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="person")
	private List<Home> homes;
	
	public Person(){
		super();
		friends = new ArrayList<Person>();
		homes = new ArrayList<Home>();
	}
	
	public Person(String name, String firstName, char genre, String mail, String birthDay){ // Date format "dd-MM-yyyy"
		this.name = name;
		this.firstName = firstName;
		this.genre = genre;
		this.mail = mail;
		try {
			this.birthDay = new SimpleDateFormat("dd-MM-yyyy").parse(birthDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		friends = new ArrayList<Person>();
		homes = new ArrayList<Home>();
	}
	
	public int getNumPerson() {
		return numPerson;
	}

	public void setNumPerson(int numPerson) {
		this.numPerson = numPerson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public char isGenre() {
		return genre;
	}

	public void setGenre(char genre) {
		this.genre = genre;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Temporal(TemporalType.DATE)
	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
	public void setBirthDay(String birthDay) {
		try {
			this.birthDay = new SimpleDateFormat("dd-MM-yyyy").parse(birthDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public List<Person> getFriends() {
		return friends;
	}

	public void setFriends(List<Person> friends) {
		this.friends = friends;
	}
	
	public boolean removeFriend(Person friend) {
		return friends.remove(friend);
	}

	//If we p1 is friendly with p2 then p2 
	public void addFriend(Person friend) {
		friends.add(friend);
		if(!friend.alreadyFriend(this)){
			friend.addFriend(this);
		}
		
	}

	public boolean alreadyFriend(Person friend) {
		return friends.contains(friend);
	}
	
	public Person getFriend(int index) {
		return friends.get(index);
	}

	public List<Home> getHomes() {
		return homes;
	}

	public void setHomes(List<Home> homes) {
		this.homes = homes;
	}
	
	public boolean removeHome(Home home) {
		return homes.remove(home);
	}

	public void addHome(Home home) {
		homes.add(home);
		home.setPerson(this);
	}

	@Override
	public String toString() {
		return	"Person [num="+numPerson
						+", name="+name
						+", firstName="+firstName
						+", genre="+genre
						+", mail="+mail
						+", friends="+friends.size()
						+", homes="+homes.size()
						+", birthDay="+birthDay.toString()
						+"]";
	}

}
