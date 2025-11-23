package aq.gym.contests.heap;

import java.util.Objects;
import java.util.PriorityQueue;

public class TotalCostToHireKWorkers {

//	https://leetcode.com/problems/total-cost-to-hire-k-workers/
	public static void main(String[] args) {
//		int[] costs = {3,2,7,7,1,2};
//		int k = 2, candidates = 2;
//		int[] costs = {17,12,10,2,7,2,11,20,8};
//		int k = 3, candidates = 4;
//		int[] costs = {1,2,4,1};
//		int k = 3, candidates = 3;
//		int[] costs = {10,11,1,10};
//		int k = 2, candidates = 1;
		int[] costs = {31,25,72,79,74,65,84,91,18,59,27,9,81,33,17,58};
		int k = 11, candidates = 2;
		System.out.println(new TotalCostToHireKWorkers().totalCost(costs, k, candidates));
	}

    public long totalCost(int[] costs, int k, int candidates) {
    	long totalCost = 0;
    	if(candidates == costs.length) {    		
    		PriorityQueue<Cost> unionCostsPQ = new PriorityQueue<>();
        	for(int i = 0; i < costs.length; i++) {
        		Cost cost = new Cost(i, costs[i]);
        		unionCostsPQ.offer(cost);
        	}
    		for(int i = 0; i < k; i++) {
    			totalCost = totalCost + unionCostsPQ.poll().cost;
    		}
    	} else {
    		PriorityQueue<Cost> leftCostsPQ = new PriorityQueue<>();
    		PriorityQueue<Cost> rightCostsPQ = new PriorityQueue<>();
    		int[] indexesStorage = {0, costs.length - 1};
            for(int count = 0; count < candidates; count++) {
                if(indexesStorage[0] <= indexesStorage[1]) {
                    leftCostsPQ.offer(new Cost(indexesStorage[0], costs[indexesStorage[0]]));
                    indexesStorage[0]++;
                }
                if(indexesStorage[0] <= indexesStorage[1]) {
                    rightCostsPQ.offer(new Cost(indexesStorage[1], costs[indexesStorage[1]]));
                    indexesStorage[1]--;
                }
            }
    		for(int p = 0; p < k; p++) {
    			Cost leftCost = leftCostsPQ.peek();
    			Cost rightCost = rightCostsPQ.peek();
    			if(leftCost != null && rightCost != null) {
    				if(leftCost.compareTo(rightCost) < 0) {
        				totalCost = updateTotalCost(totalCost, leftCostsPQ);
        				offerNextLeftCost(costs, indexesStorage, leftCostsPQ);
        			} else {
        				totalCost = updateTotalCost(totalCost, rightCostsPQ);
        				offerNextRighCost(costs, indexesStorage, rightCostsPQ);
        			}
    			} else {
    				if(leftCost != null) {
    					totalCost = updateTotalCost(totalCost, leftCostsPQ);
    					offerNextLeftCost(costs, indexesStorage, leftCostsPQ);
    				} else {
    					totalCost = updateTotalCost(totalCost, rightCostsPQ);
    					offerNextRighCost(costs, indexesStorage, rightCostsPQ);
    				}
    			}
    		}
    	}
    	return totalCost;
    }
    
    private long updateTotalCost(long totalCost, PriorityQueue<Cost> costs) {
    	totalCost = totalCost + costs.poll().cost;
    	return totalCost;
    }
    
    private void offerNextRighCost(int[] costs, int[] indexesStorage, PriorityQueue<Cost> rightCostsPQ) {
    	if(indexesStorage[1] >= indexesStorage[0]) {    					
			rightCostsPQ.offer(new Cost(indexesStorage[1], costs[indexesStorage[1]]));
			indexesStorage[1]--;
		}
    }
    
    private void offerNextLeftCost(int[] costs, int[] indexesStorage, PriorityQueue<Cost> leftCostsPQ) {
    	if(indexesStorage[0] <= indexesStorage[1]) {    					
			leftCostsPQ.offer(new Cost(indexesStorage[0], costs[indexesStorage[0]]));
			indexesStorage[0]++;
		}
    }
    
    private static class Cost implements Comparable<Cost> {
    	
    	private int idx;
    	private int cost;
    	
    	Cost(int idx, int cost) {
    		this.idx = idx;
    		this.cost = cost;
    	}
    	
    	@Override
    	public boolean equals(Object obj) {
    		if(obj == null || obj.getClass() != this.getClass()) return false;
    		if(obj == this) return true;
    		Cost cost = (Cost) obj;
    		return cost.idx == this.idx && cost.cost == this.cost;
    	}
    	
    	@Override
    	public int hashCode() {
    		return Objects.hash(idx, cost);
    	}
    	
    	@Override
    	public String toString() {
    		return String.format("[%d, %d]", idx, cost);
    	}

		@Override
		public int compareTo(Cost cost) {
			int costCompare = Integer.compare(this.cost, cost.cost);
    		if(costCompare == 0) {
    			return Integer.compare(this.idx, cost.idx);
    		}
    		return costCompare;
		}
    }
}
