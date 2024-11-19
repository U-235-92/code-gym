package aq.gym.db.jdbc_sport_nutrition_shop;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class Entity {

	@Getter
	@Setter
	private int id;
}
