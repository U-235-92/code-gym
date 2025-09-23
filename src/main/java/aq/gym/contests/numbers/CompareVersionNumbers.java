package aq.gym.contests.numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompareVersionNumbers {

//	https://leetcode.com/problems/compare-version-numbers/description/
	public static void main(String[] args) {
		String version1 = "1.0", version2 = "1.0.0.0";
		System.out.println(new CompareVersionNumbers().compareVersion(version1, version2));
	}

	public int compareVersion(String version1, String version2) {
    	return way1(version1, version2);
    }
	
	@SuppressWarnings("unused")
	private int way1(String version1, String version2) {
		int v1Pointer = 0, v2Pointer = 0;
    	int compareVersion = 0;
        boolean isComapare = true;
        while(isComapare) {
        	if(v1Pointer >= version1.length() && v2Pointer >= version2.length()) {
        		break;
        	}
        	int v1Revision = 0, v2Revision = 0;
        	StringBuilder v1Builder = new StringBuilder();
        	StringBuilder v2Builder = new StringBuilder();
        	for(int i = v1Pointer; i < version1.length(); i++) {
        		char ch = version1.charAt(i);
        		if(ch == '.') {
        			v1Revision = Integer.valueOf(v1Builder.toString());
        			v1Pointer = i + 1;
        			break;
        		} else {        			
        			v1Builder.append(ch);
        			if(i == version1.length() - 1) {
        				v1Revision = Integer.valueOf(v1Builder.toString());
        				v1Pointer = i + 1;
        			}
        		}
        	}
        	for(int i = v2Pointer; i < version2.length(); i++) {
        		char ch = version2.charAt(i);
        		if(ch == '.') {
        			v2Revision = Integer.valueOf(v2Builder.toString());
        			v2Pointer = i + 1;
        			break;
        		} else {        			
        			v2Builder.append(ch);
        			if(i == version2.length() - 1) {
        				v2Revision = Integer.valueOf(v2Builder.toString());
        				v2Pointer = i + 1;
        			}
        		}
        	}
        	if(v1Revision > v2Revision) {        		
        		compareVersion = 1;
        		isComapare = false;
        	} else if(v1Revision < v2Revision) {
        		compareVersion = -1;
        		isComapare = false;
        	}
        }
        return compareVersion;
	}
	
	@SuppressWarnings("unused")
	private int way2(String version1, String version2) {
		int compareVersion = 0;
		List<Integer> revisions1 = new ArrayList<>(Arrays.stream(version1.split("\\.")).map(Integer::valueOf).toList());
		List<Integer> revisions2 = new ArrayList<>(Arrays.stream(version2.split("\\.")).map(Integer::valueOf).toList());
		if(revisions1.size() > revisions2.size()) {
			while(revisions1.size() != revisions2.size()) {
				revisions2.add(0);
			}
		}
		if(revisions1.size() < revisions2.size()) {
			while(revisions1.size() != revisions2.size()) {
				revisions1.add(0);
			}
		}
		for(int i = 0; i < revisions1.size(); i++) {
			int revision1 = revisions1.get(i);
			int revision2 = revisions2.get(i);
			if(revision1 > revision2) {
				compareVersion = 1;
				break;
			} else if(revision1 < revision2) {
				compareVersion = -1;
				break;
			}
		}
		return compareVersion;
	}
}
