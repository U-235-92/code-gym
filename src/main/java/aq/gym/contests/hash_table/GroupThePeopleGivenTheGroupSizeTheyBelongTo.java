package aq.gym.contests.hash_table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupThePeopleGivenTheGroupSizeTheyBelongTo {

//	https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/description/
	public static void main(String[] args) {
		int[] groupSizes = {3,3,3,3,3,1,3};
		List<List<Integer>> groupThePeople = new GroupThePeopleGivenTheGroupSizeTheyBelongTo().groupThePeople(groupSizes);
		groupThePeople.forEach(System.out::println);
	}

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
    	List<List<Integer>> groups = new ArrayList<>();
    	Map<Integer, List<Integer>> map = new HashMap<>();
    	for(int i = 0; i < groupSizes.length; i++) {
    		int groupSize = groupSizes[i];
    		if(map.get(groupSize) == null) {
    			List<Integer> persons = new ArrayList<>();
				persons.add(i);
				map.put(groupSize, persons);
    		} else {
    			List<Integer> persons = map.get(groupSize);
    			persons.add(i);
    			map.put(groupSize, persons);
    		}
    	}
    	for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
    		int groupSize = entry.getKey();
    		List<Integer> persons = entry.getValue();
    		if(groupSize == persons.size()) {
    			List<Integer> group = new ArrayList<>(persons);
    			groups.add(group);
    		} else {
    			while(!persons.isEmpty()) {    				
    				List<Integer> group = new ArrayList<>();
    				for(int i = 0; i < groupSize; i++) {
    					group.add(persons.removeLast());
    				}
    				groups.add(group);
    			}
    		}
    	}
        return groups;
    }
}
