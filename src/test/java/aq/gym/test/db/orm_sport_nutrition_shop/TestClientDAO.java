package aq.gym.test.db.orm_sport_nutrition_shop;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import aq.gym.db.orm_sport_nutrition_shop.Client;
import aq.gym.db.orm_sport_nutrition_shop.ClientDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class TestClientDAO {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("aq.db.orm_sport_nutrition_shop");
	private static EntityManager entityManager = entityManagerFactory.createEntityManager();
	private static ClientDAO clientDAO = new ClientDAO(entityManager);;
	
	@Test
	public void shouldAddClients() {
		List<Client> insertedClients = List.of(new Client("Alice"), new Client("Bob"), new Client("Sarah"));
		clientDAO.addClients(insertedClients);
		List<Client> readedClients = clientDAO.getClients();
		System.out.println(readedClients);
		assertNotNull(readedClients);
	}
	
	@AfterAll
	public static void closeEntityManager() {
		if(entityManager != null) 
			entityManager.close();
	}

}
