import java.util.Arrays;
import java.util.List;
import java.util.Stack;;
public class KeysAndRooms {
    /**
     * There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.

When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.

Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise.
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms){
        boolean[] visited = new boolean[rooms.size()];
        for(boolean b: visited) b = false;
        visited[0] = true;
        Stack<Integer> keys = new Stack<>();
        for(Integer i: rooms.get(0)){
            keys.add(i);
        }
        while(!keys.isEmpty()) {
            Integer curr = keys.pop();
            if(curr == null || visited[curr]) continue;
            visited[curr] = true;
            for(Integer i: rooms.get(curr)){
                keys.add(i);
            }
        }
        for(boolean v: visited){
            if(!v) return false;
        }
        return true;
    }
    boolean[] visitedB;
    int count = 0;
    List<List<Integer>> list;
    int n;
    public boolean canVisitAllRoomsUsingBfs(List<List<Integer>> rooms){
        n = rooms.size();
        visitedB = new boolean[n];
        list = rooms;
        dfs(0);
        return count == n;
    }
    void dfs(int n){
        if(visitedB[n]) return;
        visitedB[n] = true;
        count++;
        if(count == n) return;
        for(int node: list.get(n)){
            dfs(node);
        }
    }
    public static void main(String[] args) {
        
    }
}
