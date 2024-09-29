
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SquareEveryDigit {
    public static int squareDigits(int n){
        if(n == 0) return 0;
        String s = "";
        while(n > 0){
            int digit = n % 10;
            n /= 10;
            s = String.valueOf((int) Math.pow(digit, 2)) + s;
        }
        return Integer.parseInt(s);
    }
    public static int squareDigitsUsingStream(int n){
        return Integer.parseInt(String.valueOf(n)
                                .chars()
                                .map(i -> Integer.parseInt(String.valueOf((char) i)))
                                .map(i -> i*i)
                                .mapToObj(String::valueOf)
                                .collect(Collectors.joining("")));
    }
    public static void main(String[] args) {
        System.out.println(squareDigits(3212));
    }
}
