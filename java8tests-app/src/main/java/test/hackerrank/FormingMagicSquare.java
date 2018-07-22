package test.hackerrank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FormingMagicSquare {

    // Complete the formingMagicSquare function below.
    static int formingMagicSquare(final int[][] s) {
    	int finalCost = 0, curCost = 0;

    	final int[][][] magicSquarePossibilitesArray = new int[][][]{
			{{8,1,6},{3,5,7},{4,9,2}},
			{{6,1,8},{7,5,3},{2,9,4}},
			{{4,9,2},{3,5,7},{8,1,6}},
			{{2,9,4},{7,5,3},{6,1,8}},
			{{8,3,4},{1,5,9},{6,7,2}},
			{{4,3,8},{9,5,1},{2,7,6}},
			{{6,7,2},{1,5,9},{8,3,4}},
			{{2,7,6},{9,5,1},{3,3,8}},
		};
		
		for( int indexMagicSquare = 0; indexMagicSquare < magicSquarePossibilitesArray.length; indexMagicSquare++ ){
			curCost = cost(magicSquarePossibilitesArray[indexMagicSquare], s);
			if( indexMagicSquare > 0 ){
				if( curCost < finalCost ){
					finalCost = curCost;
				}
			}else{
				finalCost = curCost;
			}
		}
    	
    	return finalCost;
    }
    
    static final int cost(final int[][] magicSquare, final int[][] square){
    	int totalCost = 0;
    	
    	for( int index = 0; index < magicSquare.length; index++ ){
    		int[] magicSquareLine = magicSquare[index];
    		int[] squareLine = square[index];
    		
    		for( int colIndex = 0; colIndex < magicSquareLine.length; colIndex++ ){
    			int result = magicSquareLine[colIndex] - squareLine[colIndex];
    			if( result < 0 ){
    				result = result * -1;
    			}
    			totalCost += result;
    		}
    	}
    	return totalCost;
    }
    
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\OUTPUT_PATH"));

        int[][] s = new int[3][3];

        for (int i = 0; i < 3; i++) {
            String[] sRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int sItem = Integer.parseInt(sRowItems[j]);
                s[i][j] = sItem;
            }
        }

        int result = formingMagicSquare(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

}
