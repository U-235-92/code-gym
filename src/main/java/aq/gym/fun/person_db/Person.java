package aq.gym.fun.person_db;

import java.util.List;

import javax.annotation.Nonnegative;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "persons")
@Access(AccessType.FIELD)
public class Person {

	@Id	@GeneratedValue
	@Column(name = "person_id")
	private int id;
	@Column(name = "person_name")
	private String name;
	@Column(name = "person_age")
	@Nonnegative
	private int age;
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "person_hobby", 
				joinColumns = @JoinColumn(columnDefinition = "person_id"),
				inverseJoinColumns = @JoinColumn(columnDefinition = "hobby_id"))
	private List<Hobby> hobbies;
}
