package fr.istic.tpjpa.jpa;

import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import fr.istic.tpjpa.domain.Device;
import fr.istic.tpjpa.domain.Electronic;
import fr.istic.tpjpa.domain.Heater;
import fr.istic.tpjpa.domain.Home;
import fr.istic.tpjpa.domain.Person;

public class JpaTest {

	private EntityManager manager;
	private EntityManagerFactory factory;
	private EntityTransaction tx;

	public JpaTest(String bdd) {
		factory = Persistence.createEntityManagerFactory(bdd);
		manager = factory.createEntityManager();
		tx = manager.getTransaction();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JpaTest jpa = new JpaTest("mysql"); //mysql or hsql
		jpa.tx.begin();

		// TODO create entity
		Person p1 = new Person("nom1","prenom1",'m',"personne1@mail.com","11-12-1980");
		Person p2 = new Person("nom2","prenom2",'m',"personne2@mail.com","05-02-1978");
		Person p3 = new Person("nom3","prenom3",'w',"personne3@mail.com","24-05-1972");
		
		Home h1 = new Home(63,4);
		Home h2 = new Home(18,1);
		
		Device heater1 = new Heater("heater1",10);
		Device heater2 = new Heater("heater2",12);
		Device elec1 = new Electronic("elec1",15);
		Device elec2 = new Electronic("elec2",9);
		Device elec3 = new Electronic("elec3",3);
		
		p1.addFriend(p2);
		p1.addFriend(p3);
		
		p1.addHome(h1);
		p1.addHome(h2);
		
		h1.addDevice(heater1);
		h1.addDevice(elec1);
		h2.addDevice(heater2);
		h2.addDevice(elec2);
		h2.addDevice(elec3);
		
		// TODO persist entity
		jpa.manager.persist(p1);
		jpa.manager.persist(p2);
		jpa.manager.persist(p3);
		
		jpa.tx.commit();

		// TODO run request
		
		// basic query
		System.out.println("\nBasic query :");
		List<Person> query = jpa.manager.createQuery("SELECT a FROM Person a ", Person.class).getResultList();
		for(Person person : query){
			System.out.println(person);
		}
		 // query named
		System.out.println("\nNamed query :");
	    TypedQuery<Person> queryNamed = jpa.manager.createQuery("SELECT c FROM Person c WHERE c.name = :name", Person.class);
		Person resultQueryNamed = queryNamed.setParameter("name", "nom1").getSingleResult();
		System.out.println(resultQueryNamed);
		
		// criteria query
		System.out.println("\nCriteria query :");
		CriteriaBuilder builder = jpa.manager.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = builder.createQuery();
		Root<Person> from = criteriaQuery.from(Person.class);
		CriteriaQuery<Object> select = criteriaQuery.select(from);
		TypedQuery<Object> typedQuery = jpa.manager.createQuery(select);
		for(Object o : typedQuery.getResultList()){
			System.out.println(o);
		}
		
		jpa.manager.close();
		System.out.println("\n.. done");
		
	}
	
	/*
	private void createPersons(int nb){
		Person p;
		RandomDateOfBirth randomDate;
		for(int i=0;i < nb;i++){
			randomDate = new RandomDateOfBirth();
			p = new Person("nom"+i,"prenom"+i,'m',"personne"+i+"@mail.com","");
			p.setBirthDay(randomDate.getRandomDate());
		}
	}
	*/
}
