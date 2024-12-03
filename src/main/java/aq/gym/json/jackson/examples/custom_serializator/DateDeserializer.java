package aq.gym.json.jackson.examples.custom_serializator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class DateDeserializer extends StdDeserializer<LocalDate> {

	private static final long serialVersionUID = -1480693828723330114L;

	public DateDeserializer() {
		super(LocalDate.class);
	}
	
	public DateDeserializer(Class<LocalDate> clazz) {
		super(clazz);
	}

	@Override
	public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ObjectCodec codec = p.getCodec();
		JsonNode node = codec.readTree(p);
		String date = node.asText();
		return LocalDate.from(dtf.parse(date));
	}
}
