package aq.gym.db.orm_sport_nutrition_shop;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
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
	}

}
