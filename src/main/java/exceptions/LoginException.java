package exceptions;

import login.ClientType;
import utils.PrintColors;

/**
 * An exception that is invoked when a login attempt has failed
 */
public class LoginException extends Exception {
    public LoginException() {
        super();
    }

    /**
     * An exception that is invoked when a login attempt has failed
     *
     * @param action     The specific action that failed
     * @param clientType The specific client type whom the action was taken on
     */
    public LoginException(ClientType clientType, Action action) {
        super(PrintColors.RED_BOLD + clientType.toString() + " " + "Failed to " + action.toString() +
                " please check Email / Password / Client Type " + PrintColors.ANSI_RESET);
    }

}
