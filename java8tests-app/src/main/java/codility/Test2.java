package codility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test2 test2 = new Test2();
		System.out.println(test2.solution(new int[] {20,10,30,30,40,10}));
	}
	
    public int solution(int[] A) {
        // write your code in Java SE 8
    	List<CountNumber> countNumberList = new ArrayList<>();
    	Arrays.sort(A);
    	for( int index = A.length -1; index >= 1; index-- ) {
    		CountNumber countNumber = new CountNumber(A[index]);
    		if( !countNumberList.contains(countNumber) ) {
    			boolean search = true;
    			int searchLimit = index;
    			do {
    				if( !(Arrays.binarySearch(A, 0, searchLimit, countNumber.getNumber().intValue()) < 0) ) {
    					countNumber.setQuantity(countNumber.getQuantity() + 1);
    					index = searchLimit;
    					if( (searchLimit - 1) >= 0 ) {
    						searchLimit--;
    					}
    				}else {
    					index = searchLimit;
    					search = false;
    				}
    			}while(search);
    			countNumberList.add(countNumber);
    		}
    	}
    	Collections.sort(countNumberList, new Comparator<CountNumber>() {
			@Override
			public int compare(CountNumber arg0, CountNumber arg1) {
				return arg0.getQuantity().compareTo(arg1.getQuantity());
			}
    		
		});
    	return countNumberList.get(countNumberList.size()-1).getNumber().intValue();
    	
    }
    
    private static class CountNumber implements Comparable<CountNumber> {
    	private Integer number;
    	private Integer quantity;
    	
		public CountNumber(Integer number, Integer quantity) {
			this.number = number;
			this.quantity = quantity;
		}
		
		public CountNumber(Integer number) {
			this.number = number;
			this.quantity = 1;
		}
		
		public Integer getNumber() {
			return number;
		}

		public Integer getQuantity() {
			return quantity;
		}
		
		@Override
		public boolean equals(Object arg0) {
			if( arg0 == null )
				return false;
			if( !(arg0 instanceof CountNumber) )
				return false;
			if( arg0 == this )
				return true;
			
			return this.getNumber().intValue() == ((CountNumber) arg0).getNumber().intValue();
		}

		@Override
		public int hashCode() {
			return this.getNumber().intValue();
		}

		@Override
		public int compareTo(CountNumber other) {
			return this.number.compareTo(other.getNumber());
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
    }

}
