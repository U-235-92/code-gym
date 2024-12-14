package aq.gym.fun.person_db;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Access(AccessType.FIELD)
@Table(name = "hobbies")
public class Hobby {

	@Id @GeneratedValue
	@Column(name = "hobby_id")
	private int id;
	@Column(name = "hobby_name", unique = true)
	private String name;
}
