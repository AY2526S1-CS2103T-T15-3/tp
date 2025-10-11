package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DecimalFormat;

/**
 * Represents a Person's salary in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSalary(String)}
 */
public class Salary {

    public static final String MESSAGE_CONSTRAINTS =
            "Salaries can take any non-negative values, "
            + "should not be blank, "
            + "and should have up to 2 decimal places.";

    /*
     * For a salary to be valid, it must be non-negative.
     * It can be an integer (e.g. "100") or a decimal with up to 2 decimal places (e.g. "100.10").
     * Users can optionally include commas as separators for every 3 digits (e.g. "1,000" or "10,000.99").
     * Leading and/or trailing whitespaces are not allowed.
     */
    public static final String VALIDATION_REGEX = "\\d+(\\.\\d{1,2})?";

    public final String value;

    /**
     * Constructs a {@code Salary}.
     * Stores the salary without commas to preserve formatting.
     *
     * @param salary A valid salary.
     */
    public Salary(String salary) {
        requireNonNull(salary);
        String sanitizedSalary = salary.replace(",", "");
        checkArgument(isValidSalary(sanitizedSalary), MESSAGE_CONSTRAINTS);
        value = sanitizedSalary;
    }

    /**
     * Returns true if a given string is a valid salary.
     */
    public static boolean isValidSalary(String test) {
        String sanitizedSalary = test.replace(",", "");
        return sanitizedSalary.matches(VALIDATION_REGEX);
    }

    /**
     * Formats the salary with a '$' sign in front and commas separating thousands.
     * If the salary has decimal places, it will be formatted to two decimal places.
     * @return A formatted string representation of the salary.
     */
    @Override
    public String toString() {
        try {
            double amount = Double.parseDouble(this.value);
            DecimalFormat formatter = new DecimalFormat("$#,##0.00");
            return formatter.format(amount);
        } catch (NumberFormatException e) {
            return value;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Salary)) {
            return false;
        }

        Salary otherSalary = (Salary) other;
        return value.equals(otherSalary.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
