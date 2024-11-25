package aq.gym.db.orm_sport_nutrition_shop;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ClientDAO {

	@NonNull
	private EntityManager entityManager;
	
	public void createClients(List<Client> clients) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		clients.forEach(entityManager::persist);
		transaction.commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Client> readClients() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Query query = entityManager.createQuery("SELECT c FROM Client c", Client.class);
		List<Client> clients = query.getResultList();
		transaction.commit();
		return clients;
	}
	
	public Client readClient(int id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Client client = entityManager.find(Client.class, id);
		transaction.commit();
		return client;
	}
	
	public void updateClientName(int id, String name) {
		EntityTransaction transaction = entityManager.getTransaction();
		Client client = readClient(id);
		transaction.begin();
		client.setName(name);
		transaction.commit();
	}
	
	public void deleteClient(int id) {
//		Doesn't work
//		EntityTransaction transaction = entityManager.getTransaction();
//		transaction.begin();
//		Query query = entityManager.createQuery("DELETE FROM Client c WHERE c.id = :id");
//		query.setParameter("id", id);
//		query.executeUpdate();
//		transaction.commit();
		
//		Works
		entityManager.remove(readClient(id));
	}
}
