package aq.gym.db.orphan;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Thing {

	@Id @GeneratedValue
	private int id;
	private String description;
}
