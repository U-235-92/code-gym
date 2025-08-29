package aq.gym.brokers.kafka.serialize_deserialize;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Alert implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String stageId;
	private final int alertId;
	private final String alertLevel;
	private final String alertMessage;
	
	public String getStageId() {
		return stageId;
	}
	
	public void setStageId(String stageId) {
		this.stageId = stageId;
	}
	
	@Override
	public String toString() {
		return String.format("Alert[ StageId: %s, AlertId: %d, AlertLvL: %s, Message: %s ]", stageId, alertId, alertLevel, alertMessage);
	}
}
