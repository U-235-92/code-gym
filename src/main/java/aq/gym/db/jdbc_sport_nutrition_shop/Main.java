package aq.gym.db.jdbc_sport_nutrition_shop;

import java.sql.SQLException;
import java.util.List;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args)  {
		Main main = new Main();
		ClientDAO clientDAO = ClientDAOImpl.getInstance();
		OrderDAO orderDAO = OrderDAOImpl.getInstance();
		ItemDAO itemDAO = ItemDAOImpl.getInstance();
//		main.createClients(clientDAO);
		main.createItems(itemDAO);
//		List<Client> clients = main.readAllClients(clientDAO, orderDAO);
//		System.out.println(clients);
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
		Item dymatize = new Item("Dymatize Elite 100% Whey Protein 907", 10);
		Item optimum = new Item("Optimum Nutrition 100% Whey Gold Standard 909", 10);
		Item san = new Item("SAN 100% Pure Titanium Whey 909", 10);
		Item scitec = new Item("Scitec Nutrition 100% Whey Protein Proffessional 920", 10);
		itemDAO.createItem(dymatize);
		itemDAO.createItems(List.of(optimum, san, scitec));
	}
}
