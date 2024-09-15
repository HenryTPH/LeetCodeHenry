import java.util.ArrayList;
import java.util.List;

public class FindPivotIndex {
    /**
     * An array of integers nums, calculate the pivaot index of this array
     * which is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right
     * If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left
     * This also applies to the right edge of the array
     * Reutrn the left most pivot index, if no index exists, return -1
     * @param nums
     * @return
     */
    public static int pivotIndex(int[] nums){
        int total = 0;
        for(int i = 0; i < nums.length; i++){
            total += nums[i];
        }
        int sum = 0;
        List<Integer> pivot = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            int right = total - nums[i] - sum;
            if(right == sum) pivot.add(i);
            sum += nums[i];
        }
        return pivot.size() == 0 ? -1 : pivot.get(0);
    }
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(pivotIndex(nums));
    }
}
