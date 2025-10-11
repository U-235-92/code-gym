package aq.gym.db.orphan;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class OrphanTestDrive {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aq.gym.db.orphan");
		EntityManager entityManager = emf.createEntityManager();
		Thing t1 = new Thing();
		t1.setDescription("Thing#1");
		Thing t2 = new Thing();
		t2.setDescription("Thing#2");
		Thing t3 = new Thing();
		t3.setDescription("Thing#3");
		Customer alice = new Customer();
		alice.addThings(t1, t2, t3);
//		Save customer in DB
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(alice);
		transaction.commit();
//		Try to remove a thing in DB (Try to change [orphanRemoval] property in Customer class)
//		After removing a thing with Customer's [orphanRemoval=true] property the thing is removed from DB
//		After removing a thing with Customer's [orphanRemoval=false] property the thing is NOT removed from DB
//		To check it try to [select * from thing] in DB's console after program ends
		transaction.begin();
		alice.deleteThing(t3);
		transaction.commit();
		System.out.println(alice.getThings());
	}

}
