package numbers;

import java.util.*;

public class UserInput {
    public final long startNumber;
    public final Integer length;
    public final List<Property> properties;
    public final List<Property> excludedProperties;
    private final List<String> conflictingPropertiesNames = new LinkedList<>();


    public UserInput(String input) throws Exception {
        String[] args = input.split("\\s+");
        int argsLength = args.length;

        if (argsLength == 0) {
            throw new Exception("Empty request! Should never receive it!");
        }

        this.startNumber = this.getCheckStartNumber(args[0]);

        if (argsLength >= 2) {
            this.length = this.getCheckLength(args[1]);
        } else {
            this.length = null;
        }

        if (argsLength >= 3) {
            String[] propertyArgs = Arrays.copyOfRange(args, 2, argsLength);
            Pair<List<Property>, List<Property>> result = this.getCheckProperties(propertyArgs);
            this.properties = result.first;
            this.excludedProperties = result.second;
        } else {
            this.properties = null;
            this.excludedProperties = null;
        }
    }

    private long getCheckStartNumber(String arg) throws Exception {
        long number;
        try {
            number = Long.parseLong(arg);
        } catch (NumberFormatException e) {
            throw new Exception("The first parameter should be a natural number or zero.");
        }

        if (number < 0) {
            throw new Exception("The first parameter should be a natural number or zero.");
        }

        if (number == 0) {
            throw new TerminationException("Goodbye!");
        }
        return number;
    }

    private int getCheckLength(String arg) throws Exception {
        int length;

        try {
            length = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new Exception("The second parameter should be a natural number!");
        }

        if (length <= 0) {
            throw new Exception("The second parameter should be a natural number.");
        }

        return length;
    }

    private Pair<List<Property>, List<Property>> getCheckProperties(String[] propertyArgs) throws Exception {
        List<Property> properties = new LinkedList<>();
        List<Property> excludedProperties = new LinkedList<>();

        List<String> incorrectArgs = new LinkedList<>();
        for (String propertyArg : propertyArgs) {
            String current = propertyArg.toUpperCase();
            try {
                if (isNegatedProperty(current)) {
                    excludedProperties.add(Property.valueOf(current.substring(1)));
                } else {
                    properties.add(Property.valueOf(current));
                }
            } catch (IllegalArgumentException e) {
                incorrectArgs.add(current);
            }
        }
        if (!incorrectArgs.isEmpty()) {
            String messagePattern = incorrectArgs.size() == 1 ? "The property [%s] is wrong.\n"
                    : "The properties [%s] are wrong.\n";
            throw new Exception(String.format(messagePattern, String.join(", ", incorrectArgs).toUpperCase())
                    + "Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
        }

        checkConflicts(properties, excludedProperties);

        return new Pair<>(properties, excludedProperties);
    }

    private static boolean isNegatedProperty(String stringProperty) {
        return stringProperty.startsWith("-");
    }

    private static void checkConflicts(List<Property> properties, List<Property> excludedProperties) throws Exception {
        List<String> conflictingPropertyNames = new LinkedList<>();
        for (Pair<Property, Property> propertiesPair : PropertyChecks.CONFLICTING_PRESENT_PROPERTIES) {
            if (properties.contains(propertiesPair.first) && properties.contains(propertiesPair.second)) {
                conflictingPropertyNames.add(propertiesPair.first.name());
                conflictingPropertyNames.add(propertiesPair.second.name());
            }
        }
        for (Pair<Property, Property> propertiesPair : PropertyChecks.CONFLICTING_ABSENT_PROPERTIES) {
            if (excludedProperties.contains(propertiesPair.first)
                    && excludedProperties.contains(propertiesPair.second)) {
                conflictingPropertyNames.add("-" + propertiesPair.first.name());
                conflictingPropertyNames.add("-" + propertiesPair.second.name());
            }
        }

        List<Property> absentPresentPropertiesIntersection = new LinkedList<>(properties); // Convert the first list to a HashSet
        absentPresentPropertiesIntersection.retainAll(excludedProperties);

        for (Property property : absentPresentPropertiesIntersection) {
            String propertyName = property.name();
            conflictingPropertyNames.add(propertyName);
            conflictingPropertyNames.add("-" + propertyName);
        }

        if (!conflictingPropertyNames.isEmpty()) {
            throw new Exception(String.format("The request contains mutually exclusive properties: [%s]\n" +
                            "There are no numbers with these properties.",
                    String.join(", ", conflictingPropertyNames).toUpperCase()));
        }
    }
}
