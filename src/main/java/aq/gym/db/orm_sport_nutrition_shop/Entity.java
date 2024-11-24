package aq.gym.db.orm_sport_nutrition_shop;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Entity {

	@Getter @Setter
	@Id @GeneratedValue
	private int id;
}
