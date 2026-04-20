
#include <span>
#include <cmath>
#include <array>
#include <vector>
using namespace std;

class Solution {

    static const int SMALLEST_PRIME = 2;
    inline static array<int, 2> RANGE_OF_VALUES{ 1, static_cast<int>(pow(10, 5)) };
    inline static int SMALLEST_PRIME_GREATER_THAN_MAX_VALUE = 3 + RANGE_OF_VALUES[1];

public:
    int minOperations(vector<int>& input) {
        vector<bool> sieveOfEratosthenes = createSieveOfEratosthenes();

        return findMinOperationsForEvenIndices(input, sieveOfEratosthenes)
                + findMinOperationsForOddIndices(input, sieveOfEratosthenes);
    }

private:
    static vector<bool> createSieveOfEratosthenes() {
        vector<bool> isPrime(SMALLEST_PRIME_GREATER_THAN_MAX_VALUE + 1, true);
        isPrime[0] = false;
        isPrime[1] = false;

        int upperLimit = sqrt(SMALLEST_PRIME_GREATER_THAN_MAX_VALUE);
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

    static int findMinOperationsForEvenIndices(span<const int> input, const vector<bool>& sieveOfEratosthenes) {
        int minOperations = 0;
        for (int i = 0; i < input.size(); i += 2) {
            int value = input[i];
            while (!sieveOfEratosthenes[value]) {
                ++minOperations;
                ++value;
            }
        }
        return minOperations;
    }

    static int findMinOperationsForOddIndices(span<const int> input, const vector<bool>& sieveOfEratosthenes) {
        int minOperations = 0;
        for (int i = 1; i < input.size(); i += 2) {
            if (!sieveOfEratosthenes[input[i]]) {
                continue;
            }
            if (input[i] > 2) {
                ++minOperations;
            }
            else {
                minOperations += 2;
            }
        }
        return minOperations;
    }
};
