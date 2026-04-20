
using System;

public class Solution
{
    private static readonly int SMALLEST_PRIME = 2;
    private static readonly int[] RANGE_OF_VALUES = { 1, (int)Math.Pow(10, 5) };
    private static readonly int SMALLEST_PRIME_GREATER_THAN_MAX_VALUE = 3 + RANGE_OF_VALUES[1];

    public int MinOperations(int[] input)
    {
        bool[] sieveOfEratosthenes = CreateSieveOfEratosthenes();

        return FindMinOperationsForEvenIndices(input, sieveOfEratosthenes)
                + FindMinOperationsForOddIndices(input, sieveOfEratosthenes);
    }

    private static bool[] CreateSieveOfEratosthenes()
    {
        bool[] isPrime = new bool[SMALLEST_PRIME_GREATER_THAN_MAX_VALUE + 1];
        Array.Fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        int upperLimit = (int)Math.Sqrt(SMALLEST_PRIME_GREATER_THAN_MAX_VALUE);
        for (int value = SMALLEST_PRIME; value <= upperLimit; ++value)
        {
            if (!isPrime[value])
            {
                continue;
            }
            int current = value * 2;
            while (current <= SMALLEST_PRIME_GREATER_THAN_MAX_VALUE)
            {
                isPrime[current] = false;
                current += value;
            }
        }

        return isPrime;
    }

    private static int FindMinOperationsForEvenIndices(int[] input, bool[] sieveOfEratosthenes)
    {
        int minOperations = 0;
        for (int i = 0; i < input.Length; i += 2)
        {
            int value = input[i];
            while (!sieveOfEratosthenes[value])
            {
                ++minOperations;
                ++value;
            }
        }
        return minOperations;
    }

    private static int FindMinOperationsForOddIndices(int[] input, bool[] sieveOfEratosthenes)
    {
        int minOperations = 0;
        for (int i = 1; i < input.Length; i += 2)
        {
            if (!sieveOfEratosthenes[input[i]])
            {
                continue;
            }
            if (input[i] > 2)
            {
                ++minOperations;
            }
            else
            {
                minOperations += 2;
            }
        }
        return minOperations;
    }
}
