
import java.util.Arrays;

public class Solution {

    private static final int SMALLEST_PRIME = 2;
    private static final int[] RANGE_OF_VALUES = {1, (int) Math.pow(10, 5)};
    private static final int SMALLEST_PRIME_GREATER_THAN_MAX_VALUE = 3 + RANGE_OF_VALUES[1];

    public int minOperations(int[] input) {
        boolean[] sieveOfEratosthenes = createSieveOfEratosthenes();

        return findMinOperationsForEvenIndices(input, sieveOfEratosthenes)
                + findMinOperationsForOddIndices(input, sieveOfEratosthenes);
    }

    private static boolean[] createSieveOfEratosthenes() {
        boolean[] isPrime = new boolean[SMALLEST_PRIME_GREATER_THAN_MAX_VALUE + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        int upperLimit = (int) Math.sqrt(SMALLEST_PRIME_GREATER_THAN_MAX_VALUE);
        for (int value = SMALLEST_PRIME; value <= upperLimit; ++value) {
            if (!isPrime[value]) {
                continue;
            }
            int current = value * 2;
            while (current <= SMALLEST_PRIME_GREATER_THAN_MAX_VALUE) {
                isPrime[current] = false;
                current += value;
            }
        }

        return isPrime;
    }

    private static int findMinOperationsForEvenIndices(int[] input, boolean[] sieveOfEratosthenes) {
        int minOperations = 0;
        for (int i = 0; i < input.length; i += 2) {
            int value = input[i];
            while (!sieveOfEratosthenes[value]) {
                ++minOperations;
                ++value;
            }
        }
        return minOperations;
    }

    private static int findMinOperationsForOddIndices(int[] input, boolean[] sieveOfEratosthenes) {
        int minOperations = 0;
        for (int i = 1; i < input.length; i += 2) {
            if (!sieveOfEratosthenes[input[i]]) {
                continue;
            }
            if (input[i] > 2) {
                ++minOperations;
            } else {
                minOperations += 2;
            }
        }
        return minOperations;
    }
}
