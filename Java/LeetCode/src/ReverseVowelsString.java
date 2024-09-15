import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class ReverseVowelsString {
    /**
     * String s, reverse only all the vowels in the string and return it.
     * 'a', 'e', 'i', 'o', 'u'
     * Can appear in both lower and upper cases, more than once.
     * 'hello' -> 'holle'
     * 'leetcode' -> 'leotcede'
     */
    public static String reverseVowels(String s){
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        Stack<Character> words = new Stack<>();
        Queue<Integer> pos = new LinkedList<>();
        StringBuilder sb = new StringBuilder(s);
        for(int i = 0; i < s.length(); i++){
            char c = sb.charAt(i);
            if(vowels.contains(Character.toLowerCase(c))){
                words.push(c);
                pos.offer(i);
            }
        }
        while(!words.empty() && !pos.isEmpty()){
            int position = pos.poll();
            char c = words.pop();
            sb.setCharAt(position, c);
        }
        return sb.toString();
    }

    public static String reverseVowels2(String s) {
        Stack<Character> vowels = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (char c: s.toCharArray()){
            char lc = Character.toLowerCase(c);
            if(lc=='a' || lc == 'e' || lc == 'i' || lc == 'o' || lc == 'e'){
                vowels.push(c);
            }
        }
        for(char c: s.toCharArray()){
            char lc = Character.toLowerCase(c);
            if(lc=='a' || lc == 'e' || lc == 'i' || lc == 'o' || lc == 'e'){
                sb.append(vowels.pop());
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(reverseVowels("LEetCode"));
    }
}
