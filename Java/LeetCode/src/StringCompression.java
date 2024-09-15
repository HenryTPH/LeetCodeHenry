public class StringCompression {
    /**
     * Array of characters, compressing by algorithms
     * - Begin with an empty string s. 
     * - Each group of consecutive repeating characters in chars:
     *  + If the group's length is 1, append the character to s
     *  + Otherwise, append the character followed by the group's length
     * - The compressed string should be stored in the input character array 
     * - Group lengths that are 10 or longer will be split into multiple characters in chars.
     * - Return the new length of the array
     * - Algorithm must use only constant extra space.
     * @param args
     */
    public static int compress(char[] chars){
        StringBuilder s = new StringBuilder();
        char c = chars[0];
        int count = 1;
        for(int i = 1; i < chars.length; i++){
            char curr = chars[i];
            if(c == curr){
                count++;
            } else {
                s.append(c);
                if(count > 1) s.append(count);
                c = curr;
                count = 1;                
            }
        }
        s.append(c);
        if(count > 1) s.append(count);
        String rs = s.toString();
        for(int i = 0; i < s.length(); i++){
            chars[i] = rs.charAt(i);
        }
        return s.toString().length();
    }
    public static void main(String[] args) {
        char[] chars = {'a','a','b','b','b','c','c','c','c','c'};
        System.out.println(compress(chars));
    }
}
