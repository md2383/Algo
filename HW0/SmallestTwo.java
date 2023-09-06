import java.util.Scanner;

public class SmallestTwo {

    public SmallestTwo(){}
    
    public int[] SmallestTester(int[] numbers){

        int[] result = new int[2];
        
        int smallest = Integer.MAX_VALUE;                                       //Start at max value so that any number will always be smaller
        int secondSmallest = Integer.MAX_VALUE - 1;                             //Start at max value - 1 so number will always be smaller than smallest
        
        for(int i = 0; i < numbers.length; i++){                                //Loop through each number to find the smallest and second smallest                                   
        
            if(numbers[i] < smallest){                                          //If number is smaller than smallest, set smallest to number and second smallest to smallest
                secondSmallest = smallest;
                smallest = numbers[i];
            }
        
            else if(numbers[i] < secondSmallest && numbers[i] != smallest){     //If number is smaller than second smallest and not same as smallest, set second smallest to number
                secondSmallest = numbers[i];
            }
        }

        result[0] = smallest;
        result[1] = secondSmallest;

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        if(sc.hasNextInt() == true){
            
            int n = sc.nextInt();
            
            int[] numbers = new int[n];
            for(int i = 0; i < n; i++){
                numbers[i] = sc.nextInt();
            }

            SmallestTwo s = new SmallestTwo();
            int[] smallestTwo = s.SmallestTester(numbers);

            // Print the smallest two numbers
            for (int number : smallestTwo) {
                System.out.println(number);
            }
        }
    }

}
