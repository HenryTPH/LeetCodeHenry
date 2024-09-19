import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {
    /**
     * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites){
        List<List<Boolean>> map = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<Integer>();
        HashSet<Integer> finished = new HashSet<>();
        for(int i = 0; i < numCourses; i++){
            List<Boolean> list = new ArrayList<>(Collections.nCopies(numCourses, false));
            map.add(list);
        }
        for(int i = 0; i < prerequisites.length; i++){
            map.get(prerequisites[i][1]).set(prerequisites[i][0], true);
            if(!queue.contains(prerequisites[i][1])) queue.add(prerequisites[i][1]);
        }
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            if(finished.contains(curr)){
                continue;
            } else {
                finished.add(curr);
            }
            List<Integer> check = new ArrayList<>();
            if(!helperFunction(map, check, curr)) return false;
        }
        return true;
    }
    private static boolean helperFunction(List<List<Boolean>> map, List<Integer> check, int current){
        if(!map.get(current).contains(true)) return true;
        check.add(current);
        for(int i = 0; i < map.get(current).size(); i++){
            boolean curr = map.get(current).get(i);
            // if(check.isEmpty()) check.add(i);
            if(curr){
                if(check.contains(i)) return false;
                /*
                 * Preserve the Current Path State: By creating a new copy of check and passing it into each recursive call of helperFunction, 
                 * the current path information is preserved without being altered by other recursive calls. This is crucial because each recursive 
                 * branch should independently track its own traversal path.
                 */
                if(!helperFunction(map, new ArrayList<>(check), i)) return false;
            } 
        }
        return true;
    }
    
    public static void main(String[] args) {
        int[][] test = {{1,0},{2,0},{0,2}};
        System.out.println(canFinish(3, test));
    }
}
