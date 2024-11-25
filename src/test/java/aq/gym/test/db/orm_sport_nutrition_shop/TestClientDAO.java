package aq.gym.test.db.orm_sport_nutrition_shop;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import aq.gym.db.orm_sport_nutrition_shop.Client;
import aq.gym.db.orm_sport_nutrition_shop.ClientDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class TestClientDAO {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("aq.db.orm_sport_nutrition_shop");
	private static EntityManager entityManager = entityManagerFactory.createEntityManager();
	private static ClientDAO clientDAO = new ClientDAO(entityManager);;
	private static List<Client> insertedClients = List.of(new Client("Alice"), new Client("Bob"), new Client("Sarah"));
	
	@BeforeAll
	public static void fillDatabase() {
		clientDAO.createClients(insertedClients);
	}
	
	@Test
	@Disabled
	public void shouldAddClients() {
		clientDAO.createClients(insertedClients);
		List<Client> readedClients = clientDAO.readClients();
		assertNotNull(readedClients);
	}
	
	@Test
	@Disabled
	public void shouldGetClientById() {
		int id = 1;
		clientDAO.createClients(insertedClients);
		Client client = clientDAO.readClient(id);
		assertNotNull(client);
	}
	
	@Test
	@Disabled
	public void shouldRenameClient() {
		int id = 1;
		String ada = "Ada";
		Client client = clientDAO.readClient(id);
		clientDAO.updateClientName(id, ada);
		client = clientDAO.readClient(id);
		assertEquals(ada, client.getName());
	}
	
	@Test
	public void shouldDeleteClient() {
		int id = 1;
		clientDAO.deleteClient(id);
		Client client = clientDAO.readClient(id);
		assertNull(client);
	}
	
	@AfterAll
	public static void closeEntityManager() {
		if(entityManager != null) 
			entityManager.close();
	}

}
