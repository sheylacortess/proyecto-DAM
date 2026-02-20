package Clases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hemos buscado esta clase por internet para poder validar el email introducido por el usuario
 * <p>
 * web: https://java.19633.com/es/Java-2/1002020408.html
 */

public class EmailUtils {
    private static final String EMAIL_REGEX = "^[a-zA-Z][a-zA-Z0-9._-]*\\@\\w+(\\.)*\\w+\\.\\w+$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);

    public static boolean validarEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }
}
