
import java.util.regex.Pattern;

public class RegexValPinCode {
    public static boolean validatePin(String pin){
        String pattern = "^\\d{4}$|^\\d{6}$";
        return Pattern.matches(pattern, pin);
    }
    public static void main(String[] args) {
        System.out.println(validatePin("1234"));
    }
}
