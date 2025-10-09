package aq.gym.db.orm_sport_nutrition_shop;

import java.io.IOException;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("aq.db.orm_sport_nutrition_shop");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		ClientDAO clientDAO = new ClientDAO(entityManager);
		List<Client> insertedClients = List.of(new Client("Alice"), new Client("Bob"), new Client("Sarah"));
		clientDAO.createClients(insertedClients);
		
//		Will persist to db after commit transaction
//		EntityTransaction transaction = entityManager.getTransaction();
//		transaction.begin();
//		Client john = new Client("John");
//		entityManager.persist(john);
//		try {
//			System.out.println("Press any key...");
//			System.in.read();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		transaction.commit();
		
//		Will save all changes after commit transaction :-/
//		Client bob = clientDAO.readClient(2);
//		EntityTransaction transaction = entityManager.getTransaction();
//		transaction.begin();
//		bob.setName("New Bob");
//		try {
//			System.out.println("Press any key...");
//			System.in.read();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		transaction.commit();
		
//		EntityTransaction transaction = entityManager.getTransaction();
//		transaction.begin();
//		Client john = new Client("John");
//		entityManager.persist(john);
//		transaction.commit();
//		
////		entityManager.detach(john);
//		System.out.println("Does context contain " + john + " ??? " + entityManager.contains(john));
//		john.setName("Middle John");
//		transaction.begin();
//		try {
//			System.out.println("Press any key...");
//			System.in.read();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		entityManager.refresh(john);
////		entityManager.detach(john);
//		transaction.commit();
		
		Client a = new Client("A_FLUSH_22");
		Client b = new Client("B_FLUSH_22");
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(a);
		entityManager.persist(b);
		entityManager.flush();
		b.setName("B_321");
		try {
			System.out.println("Press any key...");
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		transaction.rollback();
		transaction.commit();
		System.out.println(entityManager.contains(a));
		entityManager.clear();
		System.out.println(entityManager.contains(a));
	}

}
