package aq.gym.contests.realization;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ImplementRouter {
	
	public static void main(String[] args) {
////		Test case 1
		System.out.println("Test case #1:");
		Router router = new Router(3);
		System.out.println(router.addPacket(1, 4, 90)); // Return true
		System.out.println(router.addPacket(2, 5, 90)); // Return true
		System.out.println(router.addPacket(1, 4, 90)); // Return false
		System.out.println(router.addPacket(3, 5, 95)); // Return true
		System.out.println(router.addPacket(4, 5, 105)); // Return true
		System.out.println(Arrays.toString(router.forwardPacket())); // Return [2, 5, 90] and remove it from router.
		System.out.println(router.getCount(5, 100, 110)); // The only packet with destination 5 and timestamp in the inclusive range [100, 110] is [4, 5, 105]. Return 1.
		System.out.println();
		
//		Test case 2
		System.out.println("Test case #2:");
		Router router2 = new Router(2);
		System.out.println(router2.addPacket(7, 4, 90)); // Return true
		System.out.println(Arrays.toString(router2.forwardPacket())); // Return [7, 4, 90]
		System.out.println(Arrays.toString(router2.forwardPacket()));  // Return an empty array because router is empty
		System.out.println();
		
//		Test case 3
		System.out.println("Test case #3:");
		Router router3 = new Router(1);
		System.out.println(Arrays.toString(router3.forwardPacket())); // Return empty array
		System.out.println(Arrays.toString(router3.forwardPacket())); // Return empty array
		System.out.println(router3.getCount(1, 100, 150)); // Return 0 because router is empty
		System.out.println(router3.addPacket(1, 5, 50)); // Return true
		System.out.println(router3.getCount(1, 100, 150)); // Return 0 because there's no any packet with these characteristics
		System.out.println(router3.getCount(5, 50, 150)); // Return 1
		System.out.println(router3.addPacket(2, 8, 80)); // Return true
		System.out.println(Arrays.toString(router3.forwardPacket())); // Return [2, 8, 80]
		System.out.println(router3.addPacket(5, 8, 90)); // Return true
		System.out.println(Arrays.toString(router3.forwardPacket())); // Return [5, 8, 90]
		System.out.println();
		
//		Test case 4
		System.out.println("Test case #4:");
		Router router4 = new Router(2);
		System.out.println(router4.addPacket(5, 8, 50)); // Return true
		System.out.println(router4.addPacket(8, 5, 50)); // Return true
		System.out.println();
//		
//		Test case 5
		System.out.println("Test case #5:");
		Router router5 = new Router(2);
		System.out.println(router5.addPacket(76, 84, 8)); // Return true
		System.out.println(router5.addPacket(7, 68, 48)); // Return true
		System.out.println();
		
//		Test case 6
		System.out.println("Test case #6:");
		Router router6 = new Router(2);
		System.out.println(router6.addPacket(2, 1, 120)); // Return true
		System.out.println(router6.addPacket(1, 3, 120)); // Return true
		System.out.println(router6.getCount(1, 120, 120)); // Return 1
		System.out.println();
		
//		Test case 7
		Router router7 = new Router(15);
		System.out.println("Test case #7:");
		router7.addPacket(1, 4, 6);
		router7.addPacket(1, 5, 50);
		router7.addPacket(2, 5, 50);
		router7.addPacket(3, 5, 50);
		router7.addPacket(2, 8, 60);
		router7.addPacket(7, 5, 60);
		router7.addPacket(4, 5, 70);
		router7.addPacket(2, 9, 70);
		router7.addPacket(2, 5, 80);
		router7.addPacket(3, 5, 90);
		router7.addPacket(2, 8, 90);
		router7.addPacket(5, 9, 90);
		router7.addPacket(2, 8, 100);
		router7.addPacket(2, 5, 110);
		System.out.println(router7.getCount(4, 1, 4)); // Return 0
		System.out.println(router7.getCount(5, 60, 80)); // Return 3
		System.out.println();
//		Test case 8
		Router router8 = new Router(4);
		System.out.println("Test case #8:");
		router8.addPacket(4, 5, 1);
		System.out.println(router8.getCount(5, 1, 1)); // Return 1
	}
}

class Router {

	private final String DIGEST_FORMAT = "%d_%d_%d";
	
	private int memmoryLimit;
	
	private Set<String> packetDigests = new HashSet<>();
	private Deque<int[]> packetQueue = new ArrayDeque<>();
	private Map<Integer, List<int[]>> packetDestinations = new HashMap<>();
	
    public Router(int memoryLimit) {
        this.memmoryLimit = memoryLimit;
    }
    
	public boolean addPacket(int source, int destination, int timestamp) {
        int[] packet = new int[] {source, destination, timestamp};
        String digest = getDigest(source, destination, timestamp);
        if(packetDigests.contains(digest)) {
        	return false;
        } else {
        	if(packetQueue.size() < memmoryLimit) {
        		storeDataIntoCollections(digest, packet, destination);
        	} else {
        		forwardPacket();
        		storeDataIntoCollections(digest, packet, destination);
        	}
        	return true;
        }
    }
    
    private String getDigest(int source, int destination, int timestamp) {
    	return String.format(DIGEST_FORMAT, source, destination, timestamp);
    }
    
    private String getDigest(int[] packet) {
    	return String.format(DIGEST_FORMAT, packet[0], packet[1], packet[2]);
    }
    
    @SuppressWarnings("unused")
	private void storeDataIntoCollections(String digest, int[] packet, int destination) {
    	packetDigests.add(digest);
		packetQueue.add(packet);
		packetDestinations.compute(destination, (k, v) ->  {
			if(v == null) {
				List<int[]> list = new ArrayList<>();
				list.add(packet);
				return list;
			} else {
				v.add(packet);
				return v;
			}
		});
    }
    
    public int[] forwardPacket() {
    	if(!packetQueue.isEmpty()) {    
    		final int destinationIndex = 1;
    		int[] packet = packetQueue.remove();
    		String digest = getDigest(packet);
    		packetDigests.remove(digest);
    		int packetIndex = getPacketDestinationIndex(packetDestinations.get(packet[destinationIndex]), packet[2]);
    		packetDestinations.get(packet[destinationIndex]).remove(packetIndex);
    		return packet; 
    	} else {
    		return new int[] {};
    	}
    }
    
    private int getPacketDestinationIndex(List<int[]> destinations, int targetTimestamp) {
    	final int timestampIndex = 2;
    	int left = 0, right = destinations.size() - 1;
    	while(left <= right) {
    		int mid = left + (right - left) / 2;
    		int timestamp = destinations.get(mid)[timestampIndex];
    		if(timestamp == targetTimestamp) {
    			return mid;
    		} else if(timestamp > targetTimestamp) {
    			right = mid - 1;
    		} else {
    			left = left + 1;
    		}
    	}
    	return -1;
    }
    
    public int getCount(int destination, int startTime, int endTime) {
    	List<int[]> destinations = packetDestinations.get(destination);
    	if(destinations == null) {
    		return 0;
    	} else {    		
    		int leftBoundry = getLeftBoundry(destinations, startTime);
    		int rightBoundry = getRightBoundry(destinations, endTime);
    		int packetCount = rightBoundry - leftBoundry; 
    		return packetCount;
    	}
    }
    
    private int getLeftBoundry(List<int[]> destinations, int startTime) {
    	int left = 0;
        int right = destinations.size() - 1;
        int leftBoundry = destinations.size();
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int currentTimestamp = destinations.get(mid)[2];
            
            if (currentTimestamp >= startTime) {
                leftBoundry = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return leftBoundry;
    }
    
    private int getRightBoundry(List<int[]> destinations, int endTime) {
    	int left = 0;
        int right = destinations.size() - 1;
        int rightBoundry = destinations.size();
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int currentTimestamp = destinations.get(mid)[2];
            
            if (currentTimestamp > endTime) {
                rightBoundry = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return rightBoundry;
    }
}
