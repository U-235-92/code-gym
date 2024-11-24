package aq.gym.db.orm_sport_nutrition_shop;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ClientDAO {

	@NonNull
	private EntityManagerFactory entityManagerFactory;
	
	public void addClients(List<Client> clients) {
		try(EntityManager entityManager = entityManagerFactory.createEntityManager()) {			
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			clients.forEach(entityManager::persist);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
