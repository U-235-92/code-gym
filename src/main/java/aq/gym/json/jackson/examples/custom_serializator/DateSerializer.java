package aq.gym.json.jackson.examples.custom_serializator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class DateSerializer extends StdSerializer<LocalDate> {

	private static final long serialVersionUID = 1L;

	public DateSerializer() {
		super(LocalDate.class);
	}
	
	public DateSerializer(Class<LocalDate> clazz) {
		super(clazz);
	}

	@Override
	public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		gen.writeString(dtf.format(value));
	}
}
