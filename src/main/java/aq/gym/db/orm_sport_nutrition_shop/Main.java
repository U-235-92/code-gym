package aq.gym.db.orm_sport_nutrition_shop;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("aq.db.orm_sport_nutrition_shop");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		ClientDAO clientDAO = new ClientDAO(entityManager);
		entityTransaction.begin();
		List<Client> insertedClients = List.of(new Client("Alice"), new Client("Bob"), new Client("Sarah"));
		clientDAO.addClients(insertedClients);
		entityTransaction.commit();
	}

}
