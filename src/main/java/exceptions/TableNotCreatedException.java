package exceptions;

import beans.Category;
import utils.PrintColors;

/**
 * Exception that is invoked if a table has failed to create / drop
 */
public class TableNotCreatedException extends Exception {
    public TableNotCreatedException() {
        super("Failed to create");
    }

    /**
     * Exception that is invoked if a table has failed to create / drop
     *
     * @param message Relevant exception message
     */
    public TableNotCreatedException(String message) {
        super(message);
    }

    /**
     * Exception that is invoked if a table has failed to create / drop
     *
     * @param table  The specific table enum
     * @param action The specific action that failed
     */
    public TableNotCreatedException(SQLTables table, Action action) {
        super(PrintColors.RED_BOLD + "Failed to " + action.toString() + " " + table.getMESSAGE() + PrintColors.ANSI_RESET);
    }

    /**
     * An exception that is invoked when SQL table may fail to insert values into the categories table
     *
     * @param table    The specific table enum (Categories)
     * @param action   The specific action that failed
     * @param category The specific category enum that was failed to add
     */
    public TableNotCreatedException(SQLTables table, Action action, Category category) {
        super(PrintColors.RED_BOLD + "Failed to " + action.toString() + " " + table.getMESSAGE() + " " + category.toString() + PrintColors.ANSI_RESET);
    }

}
