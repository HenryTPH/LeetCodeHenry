import java.util.Arrays;

public class FindCityWithSmallestNoOfNeighborsAtThresholdDist {
    /*
     * There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.

Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.

Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.
     */
    final static int INF = 99999;
    public static int findTheCity(int n, int[][] edges, int distanceThreshold){
        int[][] distances = new int[n][n];
        int[] cities = new int[n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j){
                    distances[i][j] = 0;
                } else {
                    distances[i][j] = INF;
                }
            }
        }
        for(int i = 0; i < edges.length; i++){
            int src = edges[i][0];
            int dest = edges[i][1];
            int weight = edges[i][2];
            distances[src][dest] = weight;
            distances[dest][src] = weight;
        }
        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(distances[i][k] + distances[k][j] < distances[i][j]){
                        distances[i][j] = distances[i][k] + distances[k][j];
                    }
                }
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i != j && distances[i][j] <= distanceThreshold){
                    cities[i]++;
                }
            }
        }
        int maxPosition = -1;
        int maxValue = Integer.MAX_VALUE;

        for (int i = 0; i < cities.length; i++) {
            if (cities[i] < maxValue) {
                maxValue = cities[i];
                maxPosition = i;
            } else if (cities[i] == maxValue) {
                maxPosition = i; 
            }
        }
        return maxPosition;
    }
    public static void main(String[] args) {
        int[][] edges = {{0,1,3},{1,2,1},{1,3,4},{2,3,1}};
        System.out.println(findTheCity(4, edges, 4));
    }
}
