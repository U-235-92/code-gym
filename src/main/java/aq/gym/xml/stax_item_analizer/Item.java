package aq.gym.xml.stax_item_analizer;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {

	private String id;
	private String name;
	private String characteristic;
	private LocalDate expiration;
}
