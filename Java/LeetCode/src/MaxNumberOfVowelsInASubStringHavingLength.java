import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxNumberOfVowelsInASubStringHavingLength {
    /**
     * Given a string s and an integer k
     * Return the maximum number of vowel letters in any substring of s with length k
     * @param s
     * @param k
     * @return
     */
    public static int maxVowels(String s, int k){
        int count = 0;
        int max = 0;
        int start = 0;
        List<Character> vowels = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        for(int end = 0; end < s.length(); end++){
            if(isVowels(s.charAt(end))){
                count++;
            }
            if(end - start + 1 == k){
                max = Math.max(count, max);
                if(isVowels(s.charAt(start))){
                    count--;
                }            
                start++;
            }
        }
        return max;
    }
    private static boolean isVowels(char c){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
    public static void main(String[] args) {
        String s = "abciiidef";
        System.out.println(maxVowels(s, 3));
    }
}
