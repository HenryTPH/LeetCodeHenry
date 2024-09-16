import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class EvaluateDivision {
    /**
     * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries){
        double[] rs = new double[queries.size()];
        HashMap<String, HashMap<String, Double>> map = new HashMap<>();
        for(int i = 0; i < equations.size(); i++){
            String top = equations.get(i).get(0);
            String bottom = equations.get(i).get(1);
            if(!map.containsKey(top)){
                map.put(top, new HashMap<>());
            }
            map.get(top).put(bottom, values[i]);
            if(!map.containsKey(bottom)){
                map.put(bottom, new HashMap<>());
            }
            map.get(bottom).put(top, 1.0/values[i]);
        }
        for(int j = 0; j < queries.size(); j++){
            String src = queries.get(j).get(0);
            String dest = queries.get(j).get(1);
            HashSet<String> visited = new HashSet<>();
            double value = dfs(src, dest, map, visited);
            rs[j] = value < 0 ? -1 : value;
        }
        return rs;
    }
    public double dfs(String src, String dest, HashMap<String, HashMap<String, Double>> map, HashSet<String> visited){
        if(!map.containsKey(src)){
           return -1;
        }
        if(src.equals(dest)) return 1;
        visited.add(src);
        double rs = -1;
        for(String key: map.get(src).keySet()){
            if(!visited.contains(key)){
                rs = Math.max(rs, map.get(src).get(key) * dfs(key, dest, map, visited));
            }
        }
        return rs;
    }
    public static void main(String[] args) {
        
    }
}
