import java.util.LinkedList;
import java.util.Queue;

public class MaxConsecutiveOnesIII {
    /**
     * Given a binary array nums and an integer k
     * Return the maximum number of consecutive 1's in the array if you can flip at most k 0's
     */
    public static int longestOnes(int[] nums, int k){
        int count = 0;
        int left = 0;
        int right = 0;
        int max = Integer.MIN_VALUE;
        int len = 0;
        while(right < nums.length){
            int rightNum = nums[right];
            if(count == k && rightNum == 0){
                max = Math.max(max, len);
                int leftNum = nums[left];
                if(leftNum == 0){
                    left++;
                    count--;
                } else {
                    left++;
                }
                len--;
            } else {
                if(rightNum == 0){
                    count++;
                }
                len++;
                right++;
            }
        }
        return Math.max(max, len);
    }
    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,0,0};
        System.out.println(longestOnes(nums, 0));
    }
}
