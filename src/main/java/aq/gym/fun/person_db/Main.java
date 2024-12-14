package aq.gym.fun.person_db;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String persistanceUnitName = "aq.gym.fun.person_db";
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistanceUnitName);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Hobby sport = new Hobby();
		sport.setName("sport");
		Hobby dancing = new Hobby();
		dancing.setName("dancing");
		List<Hobby> aliceHobbies = List.of(sport, dancing);
		Person alice = new Person();
		alice.setAge(22);
		alice.setHobbies(aliceHobbies);
		alice.setName("Alice");
		Hobby painting = new Hobby();
		painting.setName("painting");
		Hobby singing = new Hobby();
		singing.setName("singing");
		List<Hobby> sarahHobbies = List.of(painting, singing);
		Person sarah = new Person();
		sarah.setAge(20);
		sarah.setHobbies(sarahHobbies);
		sarah.setName("Sarah");
		entityManager.persist(alice);
		entityManager.persist(sarah);
		entityManager.createQuery("SELECT p FROM Person p").getResultList().forEach(System.out::println);
		transaction.commit();
	}
}
