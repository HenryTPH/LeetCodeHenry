public class MoveZeroes {
    /**
     * Array integer nums, move all 0 to the end of it but maintain the relative order of the non-zero elements
     * Do it in place without making a copy of the array.
     * @param nums
     */
    public static void moveZeroes(int[] nums){
        int curr = 0;
        for(int i = 0; i < nums.length; i++){
            int val = nums[curr];
            if(val == 0){
                for(int j = curr; j < nums.length-1; j++){
                    nums[j] = nums[j+1];
                }
                nums[nums.length-1] = val;
            } else {
                curr++;
            }
        }
        if(curr == 0) nums[nums.length-1] = curr;
    }
    // Another version
    public static void moveZeroes1(int[] nums) {
        int current = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                nums[current] = nums[i];
                current++;
            }
        }
        for(int i = current; i < nums.length; i++){
            nums[i] = 0;
        }
    }
    public static void main(String[] args) {
        int[] n = {0,1,0,3,12};
        moveZeroes(n);
        for(int i: n){
            System.out.println(i);
        }
    }
}
