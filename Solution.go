
package main
import "math"

const SMALLEST_PRIME = 2

var RANGE_OF_VALUES = []int{1, int(math.Pow(10.0, 5.0))}
var SMALLEST_PRIME_GREATER_THAN_MAX_VALUE = 3 + RANGE_OF_VALUES[1]

func minOperations(input []int) int {
    var sieveOfEratosthenes []bool = createSieveOfEratosthenes()

    return findMinOperationsForEvenIndices(input, sieveOfEratosthenes) +
           findMinOperationsForOddIndices(input, sieveOfEratosthenes)
}

func createSieveOfEratosthenes() []bool {
    var isPrime []bool = make([]bool, SMALLEST_PRIME_GREATER_THAN_MAX_VALUE + 1)
    for i := range isPrime {
        isPrime[i] = true
    }
    isPrime[0] = false
    isPrime[1] = false

    upperLimit := int(math.Sqrt(float64(SMALLEST_PRIME_GREATER_THAN_MAX_VALUE)))
    for value := SMALLEST_PRIME; value <= upperLimit; value++ {
        if !isPrime[value] {
            continue
        }
        var current = value * 2
        for current <= SMALLEST_PRIME_GREATER_THAN_MAX_VALUE {
            isPrime[current] = false
            current += value
        }
    }

    return isPrime
}

func findMinOperationsForEvenIndices(input []int, sieveOfEratosthenes []bool) int {
    var minOperations = 0
    for i := 0; i < len(input); i += 2 {
        var value = input[i]
        for !sieveOfEratosthenes[value] {
            minOperations++
            value++
        }
    }
    return minOperations
}

func findMinOperationsForOddIndices(input []int, sieveOfEratosthenes []bool) int {
    var minOperations = 0
    for i := 1; i < len(input); i += 2 {
        if !sieveOfEratosthenes[input[i]] {
            continue
        }
        if input[i] > 2 {
            minOperations++
        } else {
            minOperations += 2
        }
    }
    return minOperations
}
