package aq.gym.db.jdbc_sport_nutrition_shop;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args)  {
		Main main = new Main();
		try(Connection connection = ConnectionManager.getConnection()) {
			Transaction transaction = new Transaction(connection);
			ClientDAO clientDAO = new ClientDAOImpl(connection, transaction);
			OrderDAO orderDAO = new OrderDAOImpl(connection, transaction);
			ItemDAO itemDAO = new ItemDAOImpl(connection, transaction);
//			main.addClients();
//			main.createItems(itemDAO);
			List<Client> clients = main.readAllClients(clientDAO, orderDAO);
			System.out.println(clients);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@SuppressWarnings("unused")
	private void createClients(ClientDAO clientDAO) throws SQLException {
		Client bob = new Client("Bob");
		Client john = new Client("John");
		Client smith = new Client("Smith");
		Client mike = new Client("Mike");
		List<Client> clients = List.of(bob, john, smith);
		clientDAO.createClient(mike);
		clientDAO.createClients(clients);
		System.out.println("All the clients added!");
	}
	
	@SuppressWarnings("unused")
	private List<Client> readAllClients(ClientDAO clientDAO, OrderDAO orderDAO) {
		return clientDAO.readClients(orderDAO);
	}
	
	@SuppressWarnings("unused")
	private void createItems(ItemDAO itemDAO) throws SQLException {
		Item dymatize = new Item("Dymatize Elite 100% Whey Protein 907", 10);
		Item optimum = new Item("Optimum Nutrition 100% Whey Gold Standard 909", 10);
		Item san = new Item("SAN 100% Pure Titanium Whey 909", 10);
		Item scitec = new Item("Scitec Nutrition 100% Whey Protein Proffessional 920", 10);
		itemDAO.createItem(dymatize);
		itemDAO.createItems(List.of(optimum, san, scitec));
		System.out.println("All the items added!");
	}
}
