package exceptions;

import login.ClientType;
import utils.PrintColors;

/**
 * An exception method that is invoked when a relevant client type does not exist in the system
 */
public class NotExistException extends Exception {
    public NotExistException() {
        super();
    }

    /**
     * An exception method that is invoked when a relevant client type does not exist in the system
     *
     * @param clientType The specific client type whom the action was taken on
     */
    public NotExistException(ClientType clientType) {
        super(PrintColors.RED_BOLD + clientType + " Not exist in the system" + PrintColors.ANSI_RESET);
    }

}
