import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoudAndRich {
    /*
     * There is a group of n people labeled from 0 to n - 1 where each person has a different amount of money and a different level of quietness.

You are given an array richer where richer[i] = [ai, bi] indicates that ai has more money than bi and an integer array quiet where quiet[i] is the quietness of the ith person. All the given data in richer are logically correct (i.e., the data will not lead you to a situation where x is richer than y and y is richer than x at the same time).

Return an integer array answer where answer[x] = y if y is the least quiet person (that is, the person y with the smallest value of quiet[y]) among all people who definitely have equal to or more money than the person x.
     */
    public static int[] loudAndRich(int[][] richer, int[] quiet){
        List<List<Integer>> map = new ArrayList<>();
        while(map.size() < quiet.length) map.add(new ArrayList<>());
        for(int[] arr: richer){
            int larger = arr[0];
            int smaller = arr[1];
            map.get(smaller).add(larger);
        }
        int[] output = new int[quiet.length];
        Arrays.fill(output, -1);
        for(int i = 0; i < quiet.length; i++){
            dfs(i, output, map, quiet, new boolean[output.length]);
        }
        return output;
    }
    public static int dfs(int current, int[] output, List<List<Integer>> map, int[] quiet, boolean[] visited){
        if(output[current] != -1) return output[current];
        output[current] = current;
        if(visited[current]) return output[current];
        visited[current] = true;
        for(int i: map.get(current)){
            if(visited[i]) continue;
            int leastQuieter = dfs(i, output, map, quiet, visited);
            if(quiet[leastQuieter] < quiet[output[current]]){
                output[current] = leastQuieter;
            }            
        }
        return output[current];
    }
    public static void main(String[] args) {
        int[][] richer = {{1,0},{2,1},{3,1},{3,7},{4,3},{5,3},{6,3}};
        int[] quiet = {3,2,5,4,6,1,7,0};
        int[] rs = loudAndRich(richer, quiet);
        for (int i : rs) {
            System.out.println(i);
        }
    }
}
