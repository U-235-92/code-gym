package aq.gym.thread.flow.native_temp_info;

import java.util.Random;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class TempInfo {

	private static final Random RANDOM = new Random();
	
	@Getter
	private final String town;
	@Getter
	private final int temp;
	
	public static TempInfo fetch(String town) {
		if(RANDOM.nextInt(10) == 0)
			throw new RuntimeException("Error!");
		return new TempInfo(town, RANDOM.nextInt(100));
	}
}
