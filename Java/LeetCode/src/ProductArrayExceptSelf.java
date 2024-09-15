import java.util.Arrays;

public class ProductArrayExceptSelf {
    /**
     * You must write an algorithm that runs in O(n) time and without using the division operation.
     * @param nums: interger array
     * @return answer: integer array
     * answer[i] is the product of all elements of nums except nums[i]
     */
    // Brute force solution
    public static int[] productExceptSelf(int[] nums){
        int[] result = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            int product = 1;
            for(int j = 0; j < nums.length; j++){
                if(j!=i){
                    product *= nums[j];
                }
            }
            result[i] = product;
        }
        return result;
    }
    // Prefix product and Suffix product Time Complexity and Space Complexity are both O(n) 
    public static int[] productExceptSelfPreSuf(int[] nums){
        int n = nums.length;
        int[] result = new int[n];
        int[] pre = new int[n];
        int[] suf = new int[n];
        pre[0] = 1;
        suf[n-1] = 1;
        for(int i = 1; i < n; i++){
            pre[i] = pre[i-1] * nums[i-1];
        }
        for(int i = n - 2; i >= 0; i--){
            suf[i] = suf[i+1] * nums[i+1];
        }
        for(int i = 0; i < n; i++){
            result[i] = pre[i] * suf[i];
        }
        return result;
    }
    // Prefix and Suffix product with Time Complexity O(n) and Space Complexity at O(1)
    public static int[] productExceptSelfO1(int[] nums){
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result,1);
        int curr = 1;
        for(int i = 0; i < n; i++){
            result[i] *= curr;
            curr *= nums[i];
        }
        curr = 1;
        for(int i = n-1; i >=0; i++){
            result[i] *= curr;
            curr *= nums[i];
        }
        return result;
    }
    public static void main(String[] args) {
        int[] n = {-1,1,0,-3,3};
        int[] result = productExceptSelfPreSuf(n);
        for(int k: result){
            System.out.println(k);
        }
    }
}
