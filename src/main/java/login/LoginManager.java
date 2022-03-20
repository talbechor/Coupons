package login;

import exceptions.Action;
import exceptions.DBDAOException;
import exceptions.LoginException;
import exceptions.NotExistException;
import facades.AdminFacade;
import facades.ClientFacade;
import facades.CompanyFacade;
import facades.CustomerFacade;
import utils.PrintColors;

public class LoginManager {

    private static LoginManager instance = null;

    /**
     * A private constructor of the login manager,
     * that will be used in the getInstance() function to create a new instance of login manager,
     * as the LoginManager class is a Singleton.
     */
    private LoginManager() {
        System.out.println("A new login manager instance was created...");
    }

    /**
     * This method checks if a LoginManager instance has been created.
     * If the instance is null, this function will use the constructor to create a new instance.
     * The function also has a double check, to make sure there was no new instance created by another thread during
     * the time of the first check.
     */
    public static LoginManager getInstance() {
        //First, check that instance is null
        if (instance == null) {
            //Synchronized-critical code , makes sure that another thread will not pass here
            synchronized (LoginManager.class) {
                //Double-check, that no other thread has already created an instance
                if (instance == null) {
                    instance = new LoginManager();
                }
            }
        }
        return instance;
    }

    /**
     * This method checks the inserted user credentials, and returns the relevant client system accordingly.
     *
     * @param email      Client's email
     * @param password   Client's password
     * @param clientType Specific client type that wants to log in
     * @throws DBDAOException    A custom exception created to be invoked in case of failure in executing the action.
     * @throws NotExistException A custom exception created to be invoked in case of no matching results.
     * @throws LoginException    A custom exception created to be invoked in case of incorrect login details.
     */
    public ClientFacade login(String email, String password, ClientType clientType) throws DBDAOException, NotExistException, LoginException {
        switch (clientType) {
            case ADMINISTRATOR:
                AdminFacade adminFacade = new AdminFacade();
                if (adminFacade.login(email, password)) {
                    System.out.println(PrintColors.PURPLE_BOLD + "Welcome, admin!" + PrintColors.ANSI_RESET);
                    return adminFacade;
                } else {
                    throw new LoginException(ClientType.ADMINISTRATOR, Action.LOGIN);
                }
            case COMPANY:
                CompanyFacade companyFacade = new CompanyFacade();
                if (companyFacade.login(email, password)) {
                    System.out.println(PrintColors.PURPLE_BOLD + "Welcome, company!" + PrintColors.ANSI_RESET);
                    return companyFacade;
                } else {
                    throw new LoginException(ClientType.COMPANY, Action.LOGIN);
                }
            case CUSTOMER:
                CustomerFacade customerFacade = new CustomerFacade();
                if (customerFacade.login(email, password)) {
                    System.out.println(PrintColors.PURPLE_BOLD + "Welcome, customer!" + PrintColors.ANSI_RESET);
                    return customerFacade;
                } else {
                    throw new LoginException(ClientType.CUSTOMER, Action.LOGIN);
                }
            default:
                System.out.println("Client type not found in system");
                return null;
        }

    }

}
