import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class CourseSchedule2 {
    /**
     * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static int[] findOrder(int numCourses, int[][] prerequisites){
        List<List<Integer>> graph = new ArrayList<>();
        int[] stateOfVisit = new int[numCourses];
        int[] rs = new int[numCourses];
        for(int i = 0; i < numCourses; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] pos: prerequisites){
            graph.get(pos[1]).add(pos[0]);
            graph.get(pos[1]).sort((a, b) -> b-a);
        }
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < numCourses; i++){
            if(stateOfVisit[i] == 0 ){
                if(checkCycle(i, graph, stateOfVisit, stack)) return new int[0];                
            }
        }
        // if(stack.isEmpty()) return rs;
        for(int i = 0; i < numCourses; i++){
            rs[i] = stack.pop();
        }
        return rs;
    }
    public static boolean checkCycle(int node, List<List<Integer>> graph, int[] stateOfVisit, Stack<Integer> stack){
        stateOfVisit[node] = 1;
        for(int i: graph.get(node)){
            if(stateOfVisit[i] == 1) return true;
            if(stateOfVisit[i] == 0 && checkCycle(i, graph, stateOfVisit, stack)) return true;
        }
        stateOfVisit[node] = 2;
        stack.push(node);
        return false;
    }
    public static void main(String[] args) {
        int[][] arr = {{0, 1}, {1,0}};
        System.out.println(findOrder(2, arr));
    }
}
