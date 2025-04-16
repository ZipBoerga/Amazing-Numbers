package numbers;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AmazingNumbers {
    public static void runApp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!\n\n" +
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n\n" +
                "\t* the first parameter represents a starting number;\n\n" +
                "\t* the second parameter shows how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n\n");

        while (true) {
            System.out.println("Enter a request:");
            String input = scanner.nextLine();

            UserInput userInput;
            try {
                userInput = new UserInput(input);
            } catch (TerminationException e) {
                System.out.println(e.getMessage());
                userInput = null; // TODO: ho to get rid of it?
                System.exit(0);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            if (userInput.properties != null) {
                printProperties(
                        getNumberRecordsForProperties(userInput.startNumber, userInput.length, userInput.properties,
                                userInput.excludedProperties)
                );
            } else if (userInput.length != null) {
                printProperties(getNumberRecords(userInput.startNumber, userInput.length));
            } else {
                printProperties(new NumberRecord(userInput.startNumber));
            }
        }
    }


    private static NumberRecord[] getNumberRecords(long startNumber, int length) {
        NumberRecord[] records = new NumberRecord[(int) length];
        for (int i = 0; i < length; i++) {
            records[i] = new NumberRecord(startNumber + i);
        }
        return records;
    }

    private static NumberRecord[] getNumberRecordsForProperties(long startNumber, int length,
                                                                List<Property> properties,
                                                                List<Property> excludedProperties) {
        List<NumberRecord> records = new LinkedList<>();

        int iteration = 0;
        while (records.size() < length) {
            NumberRecord record = new NumberRecord(startNumber + iteration++);
            boolean isFit = true;
            for (Property property : properties) {
                isFit &= property.test(record.getNumber());
            }
            for (Property property : excludedProperties) {
                isFit &= !property.test(record.getNumber());
            }

            if (isFit) {
                records.add(record);
            }

        }
        return records.toArray(NumberRecord[]::new);
    }

    private static void printProperties(NumberRecord[] records) {
        for (NumberRecord record : records) {
            List<String> properties = new LinkedList<>();
            if (record.isBuzz()) properties.add("buzz");
            if (record.isDuck()) properties.add("duck");
            if (record.isPalindromic()) properties.add("palindromic");
            if (record.isGapful()) properties.add("gapful");
            if (record.isEven()) properties.add("even");
            if (record.isOdd()) properties.add("odd");
            if (record.isSpy()) properties.add("spy");
            if (record.isSquare()) properties.add("square");
            if (record.isSunny()) properties.add("sunny");
            if (record.isJumping()) properties.add("jumping");
            if (record.isSad()) properties.add("sad");
            if (record.isHappy()) properties.add("happy");

            System.out.println(record.getNumber() + " is " + String.join(", ", properties));
        }
    }

    private static void printProperties(NumberRecord record) {
        String output = "Properties of " + record.getNumber()
                + "\n\tbuzz: " + record.isBuzz()
                + "\n\tduck: " + record.isDuck()
                + "\n\tpalindromic: " + record.isPalindromic()
                + "\n\tgapful: " + record.isGapful()
                + "\n\teven: " + record.isEven()
                + "\n\todd: " + record.isOdd()
                + "\n\tspy: " + record.isSpy()
                + "\n\tsquare: " + record.isSquare()
                + "\n\tsunny: " + record.isSunny()
                + "\n\tjumping: " + record.isJumping()
                + "\n\tsad: " + record.isSad()
                + "\n\thappy: " + record.isHappy();
        System.out.println(output);
    }

}
