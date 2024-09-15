import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.HashSet;

public class FindTheDiffOfTwoArrays {
    /**
     * Return a list answer of size 2
     * answer 0 is a list of all distinct integers in nums1 which are not present in nums2
     * answer 1 is a list of all distince integers in nums2 which are not present in nums1
     * @param nums1
     * @param nums2
     * @return
     */
    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2){
        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> set2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        for(int n: nums1){
            if(set2.contains(n)) set2.remove(n);
        }
        for(int n: nums2){
            if(set1.contains(n)) set1.remove(n);
        }
        return new ArrayList<>(Arrays.asList(new ArrayList<>(set1), new ArrayList<>(set2)));
    }
    public static void main(String[] args) {
        int[] n1 = {1,2,3};
        int[] n2 = {2,4,6};
        List<List<Integer>> rs = findDifference(n1, n2);
        for(List<Integer> l: rs){
            System.out.println(l);
        }
    }
}
