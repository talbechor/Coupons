package exceptions;

import login.ClientType;
import utils.PrintColors;

/**
 * Exception that is invoked when client type already exists in the system
 */
public class AlreadyExistException extends Exception {

    public AlreadyExistException() {
        super();
    }

    /**
     * Exception that is invoked when client type already exists in the system
     *
     * @param clientType The specific client type that already exist
     */
    public AlreadyExistException(ClientType clientType) {
        super(PrintColors.RED_BOLD + clientType + " Already exist in the system" + PrintColors.ANSI_RESET);
    }

}
