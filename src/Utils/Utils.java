package Utils;

public class Utils {
    public static int stringToNatural(String string) {
        if(!isNumeric(string) || string.equals(""))
            return -1;
        int result = Integer.parseInt(string);
        return result;

    }

    public static boolean isNumeric(String string){
        for (char c : string.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
