package aq.gym.db.jdbc_sport_nutrition_shop;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args)  {
		Main main = new Main();
		ClientDAO clientDAO = ClientDAOImpl.getInstance();
		OrderDAO orderDAO = OrderDAOImpl.getInstance();
		ItemDAO itemDAO = ItemDAOImpl.getInstance();
//		main.createClients(clientDAO);
//		main.createItems(itemDAO);
//		List<Client> clients = main.readAllClients(clientDAO, orderDAO);
//		System.out.println(clients);
//		main.createOrders(orderDAO, itemDAO);
		main.readOrders(orderDAO);
		main.deleteOrder(orderDAO, 13);
		main.readOrders(orderDAO);
	}
	
	@SuppressWarnings("unused")
	private void createClients(ClientDAO clientDAO) {
		Client bob = new Client("Bob");
		Client john = new Client("John");
		Client smith = new Client("Smith");
		Client mike = new Client("Mike");
		List<Client> clients = List.of(bob, john, smith);
		clientDAO.createClient(mike);
		clientDAO.createClients(clients);
	}
	
	@SuppressWarnings("unused")
	private List<Client> readAllClients(ClientDAO clientDAO, OrderDAO orderDAO) {
		return clientDAO.readClients(orderDAO);
	}
	
	@SuppressWarnings("unused")
	private void createItems(ItemDAO itemDAO) {
		Item dymatize = new Item("Dymatize Elite 100% Whey Protein 907");
		Item optimum = new Item("Optimum Nutrition 100% Whey Gold Standard 909");
		Item san = new Item("SAN 100% Pure Titanium Whey 909");
		Item scitec = new Item("Scitec Nutrition 100% Whey Protein Proffessional 920");
		itemDAO.createItem(dymatize);
		itemDAO.createItems(List.of(optimum, san, scitec));
	}
	
	@SuppressWarnings("unused")
	private void createOrders(OrderDAO orderDAO, ItemDAO itemDAO) {
		Order mikeOrder = new Order(LocalDateTime.now());
		mikeOrder.setComment("The first mike's order!");
		mikeOrder.addItem(itemDAO.readItemById(1).get());
		mikeOrder.addItem(itemDAO.readItemById(3).get());
		Order johnOrder = new Order(LocalDateTime.now());
		johnOrder.addItem(itemDAO.readItemById(2).get());
		johnOrder.addItem(itemDAO.readItemById(4).get());
		orderDAO.createOrderOfClient(1, mikeOrder);
		orderDAO.createOrderOfClient(3, johnOrder);
	}
	
	@SuppressWarnings("unused")
	private void readOrders(OrderDAO orderDAO) {
		List<Order> orders = orderDAO.readOrders();
		orders.forEach(System.out::println);
	}
	
	@SuppressWarnings("unused")
	private void deleteOrder(OrderDAO orderDAO, int orderID) {
		orderDAO.deleteOrder(orderID);
	}
}
