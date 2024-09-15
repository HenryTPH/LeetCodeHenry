import java.io.Console;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;


// STUCK CANNOT DO
public class EqualRowAndColumnPairs {
    /**
     * A 0-indexed of matrix grid
     * Return the number of pairs (r-i, r-j) such that row r-i and col r-j are equal
     * A row and col is equal if they contain the same elements in the same order
     * @param grid
     * @return
     */
    public static int equalPairs(int[][] grid){
        HashMap<Double, Double> row = new HashMap<>();
        HashMap<Double, Double> col = new HashMap<>();
        double rowVal = 0;
        double colVal = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                rowVal += grid[i][j] * Math.pow(10,(grid.length - j -1));
                colVal += grid[j][i] * Math.pow(10,(grid.length - j - 1));
            }
            row.put((double) i, rowVal);
            col.put((double) i, colVal);
            rowVal = 0;
            colVal = 0;
        }
        System.out.println(row);
        System.out.println(col);
        int count = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                double r = row.get((double) i);
                double c = col.get((double) j);
                if(r == c){
                    count++;
                }
            }
        }
        return count;
    }
    public static int equalPairs1(int[][] grid){
        int n = grid.length;
        HashMap<Integer, int[]> r = new HashMap<>();
        HashMap<Integer, int[]> c = new HashMap<>();
        for(int i = 0; i < n; i++){
            r.put(i, grid[i]);
        }
        for (int i = 0; i < n; i++) {
            int[] column = new int[n];
            for (int j = 0; j < n; j++) {
                column[j] = grid[j][i];
            }
            c.put(i, column);
        }
        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) {
                if(Arrays.equals(r.get(i), c.get(j))){
                    count++;
                }
            }
        }
        return count;
    }
    public static void main(String[] args) {
        int[][] grid = {
            {3,3,3,6,18,3,3,3,3,3},
            {3,3,3,3,1,3,3,3,3,3},
            {3,3,3,3,1,3,3,3,3,3},
            {3,3,3,3,1,3,3,3,3,3},
            {1,1,1,11,19,1,1,1,1,1},
            {3,3,3,18,19,3,3,3,3,3},
            {3,3,3,3,1,3,3,3,3,3},
            {3,3,3,3,1,3,3,3,3,3},
            {3,3,3,1,6,3,3,3,3,3},
            {3,3,3,3,1,3,3,3,3,3}
        };
        System.out.println(equalPairs(grid));
    }
}
