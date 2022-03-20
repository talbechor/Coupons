package exceptions;

import login.ClientType;
import utils.PrintColors;

/**
 * An exception that is invoked when action in DBDAO fails
 */
public class DBDAOException extends Exception {
    public DBDAOException() {
        super();
    }

    /**
     * An exception that is invoked when action in DBDAO fails
     *
     * @param action     The specific action that failed
     * @param clientType The specific client type whom the action was taken on
     */
    public DBDAOException(Action action, ClientType clientType) {
        super(PrintColors.RED_BOLD + "failed to " + action.toString() + " " + clientType.toString() + PrintColors.ANSI_RESET);
    }

}
