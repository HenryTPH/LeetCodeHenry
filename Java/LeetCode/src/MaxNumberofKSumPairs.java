import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MaxNumberofKSumPairs {
    /**
     * Integer array nums and integer k
     * One operation, you can pick 2 numers from the array whose sum equals k and remove them from the array
     * Return the maximum number of operations you can perform on the array.
     * @param nums
     * @param k
     * @return
     */
    public static int maxOperations(int[] nums, int k){        
        int count = 0;
        int first = 0;
        int second = 1;
        if (nums.length <= 1) return 0;
        List<Integer> arr = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int curr = arr.remove(nums[first]);
        while(first < nums.length - 1){
            
            int secval = arr.get(second);
            if(k == curr + secval){
                count++;
                arr.remove(second);
                first++;
                arr.remove(nums[first]);
                second = first+1;
            } else {
                if (second < nums.length){
                    second++;
                } else {
                    first++;
                    arr.remove(nums[first]);
                }
            }
        }
        return count;
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        System.out.println(maxOperations(arr, 5));
    }
}
