public class ReverseWordsString {
    /**
     * Given an input string s, reverse the order of the words
     * The words in s will be separated by at least one space
     * @param word: A sequence of non-space characters.
     * @return a string of the words in reverse order concatenated by a single space
     * Note: s may contain leading or trailing spaces or multiple spaces between 2 words. 
     * The returned string should only have a single space separating the words. Do not include any extra spaces.
     */
    public static String reverseWords(String s){
        String[] str = s.split(" "); // We can use regex: \\s+ - matches sequence of one or more whitespace characters
        StringBuilder sb = new StringBuilder();
        for(int i = str.length-1; i >= 0; i--){
            String word = str[i];
            if(!word.equals("")){
                sb.append(word);
                if(i != 0) sb.append(" ");
            }            
        }
        if(sb.charAt(sb.length()-1) == ' ') sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
    public static void main(String[] args) {
        String s = "  hello world ";
        System.out.println(reverseWords(s));
    }
}
