public class LongestSubarrOf1AfterDelOneEle {
    /**
     * Given a binary array, dele one element form it
     * Return the size of the longest non-empty subarray containing only 1's
     * in the resulting array
     * Return 0 if there is no subarray
     * 
     * @param args
     */
    public int longestSubarray(int[] nums){
        int zeroCount = 0;
        int longestWindow = 0;
        int start = 0;
        for(int end = 0; end < nums.length; end++){
            zeroCount += nums[end] == 0 ? 1 : 0;
            while(zeroCount > 1){
                zeroCount -= nums[start] == 0 ? 1 : 0;
                start++;
            }
            longestWindow = Math.max(longestWindow, end - start);
        }
        return longestWindow;
    }
    public int longestSubarrayQueue(int[] numse){
        return 0;
    }
    // public int longestSubarrayOneLoop(int[] nums){
    //     int count = 0;
    //     int n = nums.length;
        
    // }
    public static void main(String[] args) {
        
    }
}
