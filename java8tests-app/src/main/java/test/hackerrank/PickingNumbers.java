package test.hackerrank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PickingNumbers {


    // Complete the pickingNumbers function below.
    static int pickingNumbers(int[] a) {
    	Arrays.sort(a);
    	int greaterArrayLengh = 0, lastNumber = 0;
    	List<Integer> tempList = null;
    	for( int index =0; index< a.length; index++ ){
    		if( index > 0 ){
    			if( (a[index] - lastNumber ) <= 1 ){
    				tempList.add(a[index]);
    				if( index + 1 >= a.length ){
        				if( tempList.size() > 1 && tempList.size() > greaterArrayLengh ){
        					greaterArrayLengh = tempList.size();
        				}
    				}
    			}else{
    				if( tempList.size() > 1 && tempList.size() > greaterArrayLengh ){
    					greaterArrayLengh = tempList.size();
    				}
    				tempList = new ArrayList<>();
    				tempList.add(a[index]);
    				lastNumber = a[index];
    			}
    			
    		}else{
    			tempList = new ArrayList<>();
    			tempList.add(a[index]);
    			lastNumber = a[index];
    		}
    	}
    	return greaterArrayLengh;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\PICKING_NUMBERS"));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int result = pickingNumbers(a);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
