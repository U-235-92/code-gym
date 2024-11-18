package aq.gym.db.jdbc_sport_nutrition_shop;

import java.util.List;
import java.util.Optional;

public interface ClientDAO extends AbstractDAO<Client> {

	public int createClients(List<Client> clients);
	public int createClient(Client client);
	public List<Client> readClients(OrderDAO orderDAO);
	public Optional<Client> readClient(int clientID, OrderDAO orderDAO);
	public boolean updateClient(int clientID, Client updateClientData);
	public boolean deleteClient(int clientID, OrderDAO orderDAO);
}
