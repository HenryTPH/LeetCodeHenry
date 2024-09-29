public class CreditCardMask {
    public static String Maskify(String str){
        // throw new UnsupportedOperationException("Unimplemented");
        StringBuilder sb = new StringBuilder();
        int len = str.length();
        if (len <= 4) return str;
        for(int i = 0; i < len; i++){
            if(i >= len-4){
                sb.append(str.charAt(i));
                continue;
            }
            sb.append('#');
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(Maskify("0123456789"));
    }
}