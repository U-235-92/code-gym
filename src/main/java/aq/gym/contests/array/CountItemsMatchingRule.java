package aq.gym.contests.array;

import java.util.ArrayList;
import java.util.List;

public class CountItemsMatchingRule {

	public static void main(String[] args) {
		List<List<String>> items = new ArrayList<>();
		items.add(List.of("phone", "blue", "pixel"));
		items.add(List.of("computer", "silver", "lenovo"));
		items.add(List.of("phone", "gold", "iphone"));
		System.out.println(new CountItemsMatchingRule().countMatches(items, "type", "computer"));
	}

    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
    	int ruleIdx = 0, count = 0;
    	switch (ruleKey) {
			case "type": 
				ruleIdx = 0; 
				break;
			case "color": 
				ruleIdx = 1;
				break;
			case "name": 
				ruleIdx = 2;
				break;
    	}
    	for(int i = 0; i < items.size(); i++) {
    		String currentValue = items.get(i).get(ruleIdx);
    		if(currentValue.hashCode() == ruleValue.hashCode() && currentValue.equals(ruleValue)) {
    			count++;
    		}
    	}
    	return count;
    }
}
