package exceptions;

/**
 * This Enum represents all the SQL tables and database created by the program
 */
public enum SQLTables {
    SCHEMA("database"),
    CATEGORIES("`categories` table"),
    CATEGORY_VALUE("category value"),
    COMPANIES("`companies` table"),
    CUSTOMERS("`customers` table"),
    COUPONS("`coupons` table"),
    CUSTOMERS_VS_COUPONS("`customers_vs_coupons` table");

    public final String MESSAGE;

    /**
     * Enum constructor
     *
     * @param message message for each enum
     */
    SQLTables(String message) {
        this.MESSAGE = message;
    }

    /**
     * Returns message from the enum
     */
    public String getMESSAGE() {
        return MESSAGE;
    }

}
