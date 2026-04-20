
import kotlin.math.pow
import kotlin.math.sqrt

class Solution {

    private companion object {
        const val SMALLEST_PRIME = 2
        val RANGE_OF_VALUES = intArrayOf(1, (10.0).pow(5.0).toInt())
        val SMALLEST_PRIME_GREATER_THAN_MAX_VALUE = 3 + RANGE_OF_VALUES[1]
    }

    fun minOperations(input: IntArray): Int {
        val sieveOfEratosthenes = createSieveOfEratosthenes()

        return findMinOperationsForEvenIndices(input, sieveOfEratosthenes) +
                findMinOperationsForOddIndices(input, sieveOfEratosthenes)
    }

    private fun createSieveOfEratosthenes(): BooleanArray {
        val isPrime = BooleanArray(SMALLEST_PRIME_GREATER_THAN_MAX_VALUE + 1) { true }
        isPrime[0] = false
        isPrime[1] = false

        val upperLimit = sqrt(SMALLEST_PRIME_GREATER_THAN_MAX_VALUE.toDouble()).toInt()
        for (value in SMALLEST_PRIME..upperLimit) {
            if (!isPrime[value]) {
                continue
            }
            var current = value * 2
            while (current <= SMALLEST_PRIME_GREATER_THAN_MAX_VALUE) {
                isPrime[current] = false
                current += value
            }
        }

        return isPrime
    }

    private fun findMinOperationsForEvenIndices(input: IntArray, sieveOfEratosthenes: BooleanArray): Int {
        var minOperations = 0
        for (i in 0..<input.size step 2) {
            var value = input[i]
            while (!sieveOfEratosthenes[value]) {
                ++minOperations
                ++value
            }
        }
        return minOperations
    }

    private fun findMinOperationsForOddIndices(input: IntArray, sieveOfEratosthenes: BooleanArray): Int {
        var minOperations = 0
        for (i in 1..<input.size step 2) {
            if (!sieveOfEratosthenes[input[i]]) {
                continue
            }
            if (input[i] > 2) {
                ++minOperations
            } else {
                minOperations += 2
            }
        }
        return minOperations
    }
}
