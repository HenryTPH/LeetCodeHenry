import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UniqueNumberOfOccurrences {
    /**
     * An array of integer, return true if the number of occurrences of each value in the array is unique
     * or false otherwise
     * @param arr
     * @return
     */
    public static boolean uniqueOccurrences(int[] arr){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i: arr){
            map.put(i,map.getOrDefault(i, 0) + 1);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            if(list.contains(entry.getValue())){
                return false;
            } else {
                list.add(entry.getValue());
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int[] arr = {1,2};
        System.out.println(uniqueOccurrences(arr));
    }
}
