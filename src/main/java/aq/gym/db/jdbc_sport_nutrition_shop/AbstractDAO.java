package aq.gym.db.jdbc_sport_nutrition_shop;

import java.sql.Connection;
import java.util.Optional;

public interface AbstractDAO<T extends Entity> {
	
	public void setConnection(Connection connection);
	public void setTransaction(Transaction transaction);
}
