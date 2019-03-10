import java.util.Map;
import java.util.TreeMap;

public class RomanNumber {
    private static TreeMap<Integer, String> associativeMap = new TreeMap<>();

    static {
        associativeMap.put(0, "Undefined value in Roman numerals!");
        associativeMap.put(1, "I");
        associativeMap.put(2, "II");
        associativeMap.put(3, "III");
        associativeMap.put(4, "IV");
        associativeMap.put(5, "V");
        associativeMap.put(6, "VI");
        associativeMap.put(7, "VII");
        associativeMap.put(8, "VIII");
        associativeMap.put(9, "IX");
        associativeMap.put(10, "X");
        associativeMap.put(11, "XI");
        associativeMap.put(12, "XII");
        associativeMap.put(13, "XIII");
        associativeMap.put(14, "XIV");
        associativeMap.put(15, "XV");
        associativeMap.put(16, "XVI");
        associativeMap.put(17, "XVII");
        associativeMap.put(18, "XVIII");
        associativeMap.put(19, "XIX");
        associativeMap.put(20, "XX");
    }

    public static String toRoman(Integer number) {
        return associativeMap.get(number);
    }

    public static String toArab(String number) {
        int result = -1;

        for (Map.Entry<Integer, String> item : associativeMap.entrySet()) {
            if (item.getValue().equals(number)) {
                result = item.getKey();
            }
        }

        return Integer.toString(result);
    }
}

