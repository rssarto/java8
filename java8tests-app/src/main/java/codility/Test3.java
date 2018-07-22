package codility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test3 test3 = new Test3();
		int result = test3.solution(new int[] {5,4,0,3,1,6,2});
		System.out.println(result);
	}
	
    public int solution(int[] A) {
    	if( A != null && A.length > 0 ) {
    		Set<Integer> setPosition = new HashSet<>();
    		List<Integer> longNecklaceList = new ArrayList<>();
    		List<Integer> currentNeckLace = null;
    		for( int index = 0; index < A.length; index++ ) {
    			if( !setPosition.contains(index) ) {
    				setPosition.add(index);
    				currentNeckLace = new ArrayList();
    				currentNeckLace.add(A[index]);
    				while(!setPosition.contains(currentNeckLace.get(currentNeckLace.size() -1 ))) {
    					setPosition.add(currentNeckLace.get(currentNeckLace.size() -1));
    					currentNeckLace.add(A[currentNeckLace.get(currentNeckLace.size() -1)]);
    				};
    				if( currentNeckLace.size() > longNecklaceList.size() ) {
    					longNecklaceList = currentNeckLace;
    				}
    			}
    		}
    		return longNecklaceList.size();
    	}
    	return 0;
    }	

}
