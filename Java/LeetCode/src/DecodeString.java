import java.util.Stack;

import javax.security.auth.Subject;

public class DecodeString {
    /**
     * Given an encoded String, return a decoded string
     * The rule is:
     * - k[encoded_string]: encoded_string repeated k times
     * - k is positive number
     * - No extra white spaces, square brackets are well-formed
     * - Original data does not contain any digits
     * - Digits only for those digits numbers k
     * @param s
     * @return
     */
    public static String decodeString(String s){
        Stack<Integer> number = new Stack<>();
        Stack<String> st = new Stack<>();
        String rs = "";
        int index = 0;
        while(index < s.length()){
            char c = s.charAt(index);
            if(c == '['){
                st.push(rs);
                rs = "";
                index++;
            } else if (c == ']'){
                StringBuilder sb = new StringBuilder(st.pop());
                int num = number.pop();
                for(int i = 0; i < num; i++){
                    sb.append(rs);
                }
                rs = sb.toString();
                index++;
            } else if (Character.isDigit(c)){
                int count = 0;
                while(Character.isDigit(s.charAt(index))){
                    count = 10 * count + (s.charAt(index++) - '0');
                }
                number.push(count);
            } else {
                rs += c;
                index++;
            }
        }
        return rs;
    }

    public static String decodeStringRecursion(String s){
        Stack<Character> stack = new Stack<>();
        for(char c: s.toCharArray()){
            if(c == ']'){
                StringBuilder sb = new StringBuilder();
                int num = 0;
                int count = 0;
                while (!stack.isEmpty() && stack.peek() != '[') {
                    sb.append(stack.pop());
                }
                String str = sb.reverse().toString();
                stack.pop();
                while(!stack.isEmpty() && Character.isDigit(stack.peek())){
                    int n = Character.getNumericValue(stack.pop());
                    int pow = (int) Math.pow(10, count++);
                    num += pow * n;
                }
                for(int i = 0; i < num; i++){
                    for(char ch: str.toCharArray()){
                        stack.push(ch);
                    }
                }
            }
            if(c != ']')stack.push(c);
        }
        StringBuilder rs = new StringBuilder();
        for(Character c: stack){
            rs.append(c);
        }
        return rs.toString();
    }
    public static String simpleDecode(String s){
        Stack<Character> num = new Stack<>();
        Stack<Character> ch = new Stack<>();
        for(char i: s.toCharArray()){
            if(Character.isDigit(i)) num.push(i);
            if(Character.isAlphabetic(i)) ch.push(i);
        }
        String stToS = "";
        for(char c: num){
            stToS += c;
        }        
        int number = Integer.parseInt(stToS);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < number; i++){
            for(char c: ch){
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(decodeStringRecursion("3[a]2[bc]"));
    }
}
