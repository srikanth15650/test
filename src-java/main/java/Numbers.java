
public class Numbers {

    public static int maxOnesAfterFlip(int[] arr) {
        int totalOnes = 0;

        // Step 1: Count total 1s in original array
        for (int num : arr) {
            if (num == 1) {
                totalOnes++;
            }
        }

        // Step 2: Create gain array and use Kadane's algorithm to find max gain
        int maxGain = Integer.MIN_VALUE;
        int currentGain = 0;

        for (int num : arr) {
            int value = (num == 0) ? 1 : -1;

            currentGain = Math.max(value, currentGain + value);
            maxGain = Math.max(maxGain, currentGain);
        }

        // Step 3: If all are 1s, flipping any will reduce the count
        if (maxGain < 0) {
            maxGain = 0;
        }

        return totalOnes + maxGain;
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 0, 1, 0};
        int result = maxOnesAfterFlip(arr);
        System.out.println("Maximum number of 1s after one flip: " + result);
    }
}