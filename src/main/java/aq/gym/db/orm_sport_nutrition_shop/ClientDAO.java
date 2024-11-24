package aq.gym.db.orm_sport_nutrition_shop;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ClientDAO {

	@NonNull
	private EntityManager entityManager;
	
	public void addClients(List<Client> clients) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		clients.forEach(entityManager::persist);
		transaction.commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Client> getClients() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Query query = entityManager.createQuery("SELECT c FROM Client c", Client.class);
		List<Client> clients = query.getResultList();
		transaction.commit();
		return clients;
	}
}
