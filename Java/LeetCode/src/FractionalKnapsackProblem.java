import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsackProblem {
    static class ItemValue implements Comparator<ItemValue>{
        int weight, profit;
        public ItemValue(int weight, int profit){
            this.weight = weight;
            this.profit = profit;
        }
        @Override
        public int compare(ItemValue o1, ItemValue o2) {
            double cpr1 = (double) o1.profit / (double) o1.weight;
            double cpr2 = (double) o2.profit / (double) o2.weight;
            return cpr1 < cpr2 ? 1:-1;
        }        
    }
    private static double getMaxValue(ItemValue[] arr, int capacity){
        Arrays.sort(arr);
        double totalValue = 0d;
        for(ItemValue i: arr){
            int curWt = (int) i.weight;
            int curVal = (int) i.profit;
            if(capacity - curWt >= 0){
                capacity = capacity - curWt;
                totalValue += curVal;
            } else {
                double fraction = (double) capacity / (double) curWt;
                totalValue += curVal * fraction;
                capacity = (int) (capacity - curWt * fraction);
                break;
            }
        }
        return totalValue;
    }
    public static void main(String[] args) {
        
    }
}
