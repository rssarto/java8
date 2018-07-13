package test.streams;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FormingMagicSquare {

    // Complete the formingMagicSquare function below.
    static int formingMagicSquare(int[][] s) {
    	int finalCost = 0;
    	/*
    	final int leftDiagonal = s[0][0] + s[1][1] + s[2][2]; 
    	final int rightDiagonal = s[0][2] + s[1][1] + s[2][0]; 
    	
    	int magicNumber = 0;
    	int[] diagonalMagicNumberArray = null;
    	
    	if( leftDiagonal >= rightDiagonal ){
    		magicNumber =  leftDiagonal;
    		diagonalMagicNumberArray = new int[]{0,1,2};
    	}else{
    		magicNumber = rightDiagonal;
    		diagonalMagicNumberArray = new int[]{2,1,0};
    	}
    	
    	final int[][] magicSquareMatrix = s.clone();
    	
    	for( int line = 0; line < 3; line++ ){
    		int[] arrLine = magicSquareMatrix[line];
    		int currentLinSum = Arrays.stream(arrLine).sum();
    		if( currentLinSum != magicNumber ){
    			for( int col = 0; col < 3; col++ ){
    				if( !(diagonalMagicNumberArray[line] == col) ){
        				int currentColSum = magicSquareMatrix[0][col] + magicSquareMatrix[1][col] + magicSquareMatrix[2][col];
        				if( currentColSum != magicNumber ){
        					int diffToSum = magicNumber - currentLinSum;
        					finalCost += diffToSum < 0 ? diffToSum * -1 : diffToSum ;
        					magicSquareMatrix[line][col] = magicSquareMatrix[line][col] + diffToSum;
        					break;
        				}
    				}
    			}
    		}
    	}
    	*/
    	
    	List<Integer> evenNumbers = Arrays.asList(2,4,6,8);
    	List<Integer> oddNumbers = Arrays.asList(1,3,7,9);
    	List<Integer> usedEvenNumbers = new ArrayList<>();
    	List<Integer> usedOddNumbers = new ArrayList<>();
    	
    	int[] arrTop = s[0];
    	int[] arrMiddle = s[1];
    	int[] arrBottom = s[2];
    	
    	if( arrMiddle[1] != 5 ){
    		int middleCost = arrMiddle[1] - 5;
    		if( middleCost < 0 ){
    			middleCost = middleCost * -1;
    		}
    		finalCost += middleCost;
    	}
    	
    	
    	
    	return finalCost;
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
