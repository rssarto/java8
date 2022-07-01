package inPowered;

public class Solution {

    public static void main (String[] args) {
        System.out.println(countCoolness(Integer.toBinaryString(21)));

    }

    private static int countCoolness(String binaryRepresentation){
        int counter = 0;
        if(binaryRepresentation.contains("101")){
            String searchString = "101";
            counter++;
            int lastIndexOf = binaryRepresentation.lastIndexOf(searchString);
            while(lastIndexOf != -1){
                String substring = binaryRepresentation.substring(0, lastIndexOf + 1);
                lastIndexOf = substring.lastIndexOf(searchString);
                if(lastIndexOf == -1){
                    break;
                }
                counter++;
            }
        }
        return counter;
    }

}
