package exceptions;

import utils.PrintColors;

/**
 * An exception that is invoked when an un-allowed method is activated by user
 */
public class MethodNotAllowedException extends Exception {
    public MethodNotAllowedException() {
        super("Method not allowed!");
    }

    /**
     * An exception that is invoked when an un-allowed method is activated by user
     *
     * @param action The specific action that failed
     */
    public MethodNotAllowedException(Action action) {
        super(PrintColors.RED_BOLD + "Unable to" + action + PrintColors.ANSI_RESET);
    }
}
