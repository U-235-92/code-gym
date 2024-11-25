package aq.gym.db.orm_sport_nutrition_shop;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemDAO {

	@NonNull
	private EntityManager entityManager;
	
	public void deleteItem(int id) {
//		Doesn't work 
//		EntityTransaction transaction = entityManager.getTransaction();
//		transaction.begin();
//		Query query = entityManager.createNamedQuery(Item.DELETE_ITEM);
//		query.setParameter("id", id);
//		int i = query.executeUpdate();
//		System.out.println(i);
//		transaction.commit();
		Item delete = readItem(id);
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(delete);
		transaction.commit();
	}
	
	public void updateItemName(int id, String name) {
		EntityTransaction transaction = entityManager.getTransaction();
		Item item = readItem(id);
		transaction.begin();
		item.setName(name);
		transaction.commit();
	}
	
	public Item readItem(int id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Item item = entityManager.find(Item.class, id);
		transaction.commit();
		return item;
	}
	
	public Item readItem(String name) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Query query = entityManager.createNativeQuery("SELECT * FROM items WHERE name = :name", Item.class);
		query.setParameter("name", name);
		Item item = (Item) query.getSingleResult();
		transaction.commit();
		return item;
	}
	
	@SuppressWarnings("unchecked")
	public List<Item> readItems() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Query query = entityManager.createNamedQuery(Item.READ_ITEMS, Item.class);
		List<Item> items = query.getResultList();
		transaction.commit();
		return items;
	}
	
	public void createItem(Item item) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(item);
		transaction.commit();
	}
	
	public void createItems(List<Item> items) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		items.forEach(entityManager::persist);
		transaction.commit();
	}
}
