package beans;

/**
 * All the existing categories for coupons
 */
public enum Category {
    FOOD,
    ELECTRICITY,
    RESTAURANT,
    VACATION;

    public final int VALUE = 1 + ordinal();

}
