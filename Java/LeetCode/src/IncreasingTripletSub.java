public class IncreasingTripletSub {
    /**
     * Intuition: An increasing triplet in the array always has a < b. Keep track of the two smalles values if
     * If an element greater than both a and b, it means there is an increasing triplet
     * @param nums: array of integer
     * @return boolean
     * if a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]
     */
    public static boolean increasingTriplet(int[] nums){
        if(nums.length == 0 || nums.length < 3) return false;
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] <= x){
                x = nums[i];
            }else if(nums[i] <= y){
                y = nums[i];
            } else {
                return true;
            }
        }
        
        return false;
    }
    public static void main(String[] args) {
        int[] n = {2, 1, 5, 0, 4, 6};
        System.out.println(increasingTriplet(n));
    }
}
