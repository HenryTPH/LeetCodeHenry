import java.util.Stack;

public class AsteroidCollision {
    /**
     * An asteroids in a row
     * The value represents its size, and the sign represents its direction
     * - positive: right
     * - negative: left
     * Each asteroid move at the same speed
     * Find out the state of the asteroids after all collisions
     * If 2 asteroids meet, the smaller one will explode.
     * If both are the same size, both will explode
     * Two asteroids moving in the same direction will never meet
     * @param asteroids
     * @return
     */
    public static int[] asteroidCollision(int[] asteroids){
        Stack<Integer> st = new Stack<>();
        for(int item: asteroids){
            boolean addToStack = true;
            while(!st.isEmpty() && st.peek() > 0 && item < 0){
                if(st.peek() < -item){
                    st.pop();
                    continue;
                } else if(st.peek() == -item){
                    st.pop();
                }                
                addToStack = false;
                break;
            }
            if(addToStack) st.push(item);
        }
        int[] rs = new int[st.size()];
        int idx = 0;
        for(int item: st){
            rs[idx++] = item;
        }
        return rs;
    }
    public static void main(String[] args) {
        int[] n = {2, 2, 1, -2};
        int[] rs = asteroidCollision(n);
        for(int i: rs){
            System.out.println(i);
        }
    }
}
