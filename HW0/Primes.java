import java.util.Scanner;

public class Primes{

    public Primes(){}

    public int[] PrimeChecker(int n){

        int[] primes = new int[n + 1];                                      //Create array of size n to store primes
        int primeCount = 0;                                                     

        for(int i = 2; i <= n; i++){                                        //Start at 2 because 0 and 1 are not prime.
            if(
                (i == 2 || i == 3 || i == 5 || i == 7) ||                   //If i is 2, 3, 5, or 7
                (i % 2 != 0 && i % 3 != 0 && i % 5 != 0 && i % 7 != 0)      //Or if i is not divisible by 2, 3, 5, or 7 (4, 6, 8 are all divisible by 2)
                )
            {
                primes[primeCount++] = i;
            }
        }
        
        int[] result = new int[primeCount];
        
        for(int j = 0; j < primeCount; j++){
                result[j] = primes[j];
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if(sc.hasNextInt() == true){
            
            int n = sc.nextInt();
            while (n < 0 || n > 10000) {
                n = sc.nextInt();
            }
            Primes p = new Primes();
            int[] primeNumbers = p.PrimeChecker(n);

            // Print the prime numbers
            for (int prime : primeNumbers) {
                System.out.println(prime);
            }
        }
    }
}