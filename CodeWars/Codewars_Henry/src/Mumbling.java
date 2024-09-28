public class Mumbling {
    public static String accum(String s){
        StringBuilder sb = new StringBuilder();
        sb.append(s.toUpperCase().charAt(0));
        sb.append('-');
        for(int i = 1; i < s.length(); i++){
            sb.append(helperFunction(i, s.toUpperCase().charAt(i)));
            if(i < s.length() - 1) sb.append('-');
        }
        return sb.toString();
    }
    public static String helperFunction(int pos, char c){
        String rs = "" + c;
        for(int i = 0; i < pos; i++){
            rs += Character.toLowerCase(c);
        }
        return rs;
    }
    public static void main(String[] args) {
        System.out.println(accum(""));
    }
}
