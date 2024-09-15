import java.util.List;
import java.util.Arrays;

public class KidsWithCandies {
    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        Boolean[] result = new Boolean[candies.length];
        int largestElement = findLargest(candies);
        for(int i = 0; i < result.length; i++){
            if(candies[i] + extraCandies >= largestElement){
                result[i] = true;
            } else {
                result[i] = false;
            }
        }        
        return Arrays.asList(result);
    }
    public static int findLargest(int[] candies){
        int maxValue = candies[0];
        for(int i = 1; i < candies.length; i++){
            if(maxValue < candies[i]){
                maxValue = candies[i];
            }
        }
        return maxValue;
    }
    public static void main(String[] args) {
        int[] candies = {2,3,5,1,3};
        List<Boolean> result = kidsWithCandies(candies, 3);
        for(Boolean b: result){
            System.out.println(b);
        }
    }
}
