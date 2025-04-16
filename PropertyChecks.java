package numbers;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PropertyChecks {
    private PropertyChecks() {
        throw new AssertionError("Should not be called");
    }

    public static List<Pair<Property, Property>> CONFLICTING_PRESENT_PROPERTIES = List.of(
            new Pair<>(Property.EVEN, Property.ODD),
            new Pair<>(Property.SQUARE, Property.SUNNY),
            new Pair<>(Property.DUCK, Property.SPY),
            new Pair<>(Property.SAD, Property.HAPPY)
    );

    public static List<Pair<Property, Property>> CONFLICTING_ABSENT_PROPERTIES = List.of(
            new Pair<>(Property.EVEN, Property.ODD),
            new Pair<>(Property.SAD, Property.HAPPY)
    );

    public static boolean isEven(long number) {
        return number % 2 == 0;
    }

    public static boolean isOdd(long number) {
        return number % 2 != 0;
    }

    public static boolean isBuzz(long number) {
        return number % 7 == 0 || number % 10 == 7;
    }

    public static boolean isDuck(long number) {
        return Long.toString(number).contains("0");
    }

    public static boolean isPalindromic(long number) {
        String string = Long.toString(number);
        String reversedString = new StringBuilder(string).reverse().toString();

        int stopIndex = string.length() / 2;

        for (int i = 0; i <= stopIndex; i++) {
            if (string.charAt(i) != reversedString.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isGapful(long number) {
        if (number / 100 == 0) {
            return false;
        }
        String string = Long.toString(number);
        short dividend = Short.parseShort("" + string.charAt(0) + string.charAt(string.length() - 1));
        return number % dividend == 0;
    }

    public static boolean isSpy(long number) {
        String strValue = String.valueOf(number);

        int product = 1;
        int sum = 0;
        for (int i = 0; i < strValue.length(); i++) {
            int digit = Character.getNumericValue(strValue.charAt(i));
            product *= digit;
            sum += digit;
        }
        return product == sum;
    }

    public static boolean isSquare(long number) {
        long sqrt = (int) Math.sqrt(number);
        return sqrt * sqrt == number;
    }

    public static boolean isSunny(long number) {
        long nextNumber = number + 1;
        return isSquare(nextNumber);
    }

    public static boolean isJumping(long number) {
        char[] charDigits = String.valueOf(number).toCharArray();
        for (int i = 0; i < charDigits.length - 1; i++) {
            int difference = Math.abs(charDigits[i] - charDigits[i + 1]);
            if (difference != 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isHappy(long number) {
        Set<Long> sequenceNumbers = new HashSet<>();
        return isHappy(number, sequenceNumbers);
    }

    private static boolean isHappy(long number, Set<Long> sequenceNumbers) {
        if (number == 1) return true;
        if (sequenceNumbers.contains(number)) return false;

        sequenceNumbers.add(number);

        long digitSquaresSum = 0;
        while (number > 0) {
            long digit = number % 10;
            digitSquaresSum += digit * digit;
            number /= 10;
        }

        return isHappy(digitSquaresSum, sequenceNumbers);
    }

    public static boolean isSad(long number) {
        return !isHappy(number);
    }

}
