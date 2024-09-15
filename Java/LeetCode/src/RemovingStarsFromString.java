import java.util.Stack;

public class RemovingStarsFromString {
    /**
     * Given a string s containing a star *
     * One operation, you can:
     * - Choose a star in s
     * Reomve the closest non-star character to its left, as well as remove the star itself
     * Return the string after all stars have been moved
     * @param s
     * @return
     */
    public static String removeStars(String s){
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();    
        for(Character c: s.toCharArray()){
            if(c == '*'){
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
    public static void main(String[] args) {
        String s = "abb*cdfg*****x*";
        System.out.println(removeStars(s));
    }
}
