public class IsSubsequence {
    /**
     * String s and t, return true if s is a subsequence of t
     * A subsequence is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters
     * @param s
     * @param t
     * @return
     */
    public static boolean isSubsequence(String s, String t){
        if(s.length() == 0) return true;
        int pos = 0;
        for(int i = 0; i < t.length(); i++){
            char x = t.charAt(i);
            char y = s.charAt(pos);
            if(x == y){
                pos++;
            }
            if (pos == s.length()) break;
        }
        return pos == s.length();
    }
    public static boolean isSubsequence1(String s, String t){
        int slen = s.length();
        int tlen = t.length();
        if(slen == 0) return true;
        int i, j;
        for(i = j = 0; j < slen && i < tlen; i++){
            if(t.charAt(i) == s.charAt(j)){
                j++;
            }
        }
        return j == slen;
    }
    public static void main(String[] args) {
        System.out.println(isSubsequence("b", "baab"));
    }
}
