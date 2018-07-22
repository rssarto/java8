package codility;

import java.util.Arrays;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    public int solution(int[] A) {
    	Arrays.sort(A);
    	final int median = A.length / 2;
    	return A[median];
    	/*
    	int result = -1;
    	int firstGreaterThanZero = 1;
    	do {
    		if( Arrays.binarySearch(A, firstGreaterThanZero) < 0 ) {
    			result = firstGreaterThanZero;
    		}
    		firstGreaterThanZero++;
    	}while(result == -1);
        return result;*/
    	
    }	

}
