import java.util.Arrays;
import java.util.PriorityQueue;

public class PathWithMinEffort {
    /**
     * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
     * @param heights
     * @return
     */
    public static int minimumEffortPath(int[][] heights){
        int n = heights.length;
        int m = heights[0].length;
        int[][] differences = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                differences[i][j] = Integer.MAX_VALUE;
            }
        }
        differences[0][0] = 0;
        // We will have from x, y, value
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.add(new int[]{0, 0, 0});
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right
        while(!pq.isEmpty()){
            int[] current = pq.poll();
            int effort = current[2];
            int x = current[0];
            int y = current[1];
            if(x == n-1 && y == m-1) return effort;
            for(int[] dir: directions){
                if(x + dir[0] >= 0 && x + dir[0] < n 
                        && y + dir[1] >= 0 && y + dir[1] < m){
                    int new_effort = Math.max(effort, Math.abs(heights[x + dir[0]][y + dir[1]] - heights[x][y]));
                    if(new_effort < differences[x + dir[0]][y + dir[1]]){
                        differences[x + dir[0]][y + dir[1]] = new_effort;
                        pq.add(new int[]{x + dir[0], y + dir[1], new_effort});
                    }                    
                }
            }
        }
        return -1;
    }
    // This is the solution from LeetCode using DFS
    public int minimumEffortPathDFS(int[][] heights){
        int rows = heights.length;
        int cols = heights[0].length;
        int[] directions_x = {0, 1, -1, 0};
        int[] directions_y = {1, 0, 0, -1};
        boolean[][] visited = new boolean[rows][cols];
        int lowerLimit = 0;
        int upperLimit = Integer.MAX_VALUE;
        while(lowerLimit < upperLimit){
            int mid = (upperLimit + lowerLimit)/2;
            for(boolean[] row: visited){
                Arrays.fill(row, false);
            }
            dfs(0, 0, mid, heights, visited, rows, cols, directions_x, directions_y);
            if(visited[rows - 1][cols - 1]){
                upperLimit = mid;
            } else {
                lowerLimit = mid + 1;
            }
        }
        return lowerLimit;
    }
    private void dfs(int x, int y, int limitEffort, int[][] heights, boolean[][] visited, int rows, int cols, int[] directions_x, int[] directions_y){
        if(visited[x][y]) return;
        visited[x][y] = true;
        if(x == rows - 1 && y == cols - 1) return;
        for(int i = 0; i < 4; i++){
            int newX = x + directions_x[i];
            int newY = y + directions_y[i];
            if(newX < 0 || newX >= rows || newY < 0 || newY >= cols){
                continue;
            }
            int newEffort = Math.abs(heights[x][y] - heights[newX][newY]);
            if(newEffort <= limitEffort){
                dfs(newX, newY, limitEffort, heights, visited, rows, cols, directions_x, directions_y);
            }
        }
    }
    public static void main(String[] args) {
        int[][] heights = {{1,2,2}, {3,8,2}, {5,3,5}};
        System.out.println(minimumEffortPath(heights));
    }
}
