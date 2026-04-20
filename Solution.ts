
function minOperations(input: number[]): number {
    const sieveOfEratosthenes = createSieveOfEratosthenes();

    return findMinOperationsForEvenIndices(input, sieveOfEratosthenes)
        + findMinOperationsForOddIndices(input, sieveOfEratosthenes);
};

class Util {
    static SMALLEST_PRIME = 2;
    static RANGE_OF_VALUES = [1, Math.pow(10, 5)];
    static SMALLEST_PRIME_GREATER_THAN_MAX_VALUE = 3 + Util.RANGE_OF_VALUES[1];
}

function createSieveOfEratosthenes(): boolean[] {
    const isPrime: boolean[] = new Array(Util.SMALLEST_PRIME_GREATER_THAN_MAX_VALUE + 1).fill(true);
    isPrime[0] = false;
    isPrime[1] = false;

    const upperLimit = Math.sqrt(Util.SMALLEST_PRIME_GREATER_THAN_MAX_VALUE);
    for (let value = Util.SMALLEST_PRIME; value <= upperLimit; ++value) {
        if (!isPrime[value]) {
            continue;
        }
        let current = value * 2;
        while (current <= Util.SMALLEST_PRIME_GREATER_THAN_MAX_VALUE) {
            isPrime[current] = false;
            current += value;
        }
    }

    return isPrime;
}

function findMinOperationsForEvenIndices(input: number[], sieveOfEratosthenes: boolean[]): number {
    let minOperations = 0;
    for (let i = 0; i < input.length; i += 2) {
        let value = input[i];
        while (!sieveOfEratosthenes[value]) {
            ++minOperations;
            ++value;
        }
    }
    return minOperations;
}

function findMinOperationsForOddIndices(input: number[], sieveOfEratosthenes: boolean[]): number {
    let minOperations = 0;
    for (let i = 1; i < input.length; i += 2) {
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
