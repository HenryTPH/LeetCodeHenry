public class MaxAverageSubArray {
    /**
     * Integer array nums, integer k
     * Find a contiguous subarray whose length is equal to k that has the max average value
     * and return this value.
     * Any answer with a calculation error less than 10^-5 will be accepted
     * @param nums
     * @param k
     * @return
     */
    public static double findMaxAverage(int[] nums, int k){
        if(nums.length < k || nums.length == 0) return 0;
        double maxSum = 0;        
        for(int i = 0; i < k; i++){
            maxSum += nums[i];
        }
        double maxAve = maxSum/k;
        double curr = maxSum;
        for(int i = k; i < nums.length; i++){
            curr += nums[i] - nums[i - k];

            maxAve = Math.max(maxAve, curr/k);
        }
        return maxAve;
    }
    public static double findMaxAverage1(int[] nums, int k){
        double max = Integer.MIN_VALUE;
        double sum = 0;
        int start = 0;
        for(int end = 0; end < nums.length; end++){
            sum += nums[end];
            if(end - start + 1 == k){
                max = Math.max(max, sum/k);
                sum = sum - nums[start];
                start++;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[] nums = {5};
        System.out.println(findMaxAverage(nums, 1));
    }
}
