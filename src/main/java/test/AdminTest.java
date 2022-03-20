package test;

import beans.Company;
import beans.Customer;
import facades.AdminFacade;
import login.ClientType;
import login.LoginManager;
import utils.PrintColors;
import utils.TablePrinter;


public class AdminTest {
    /**
     * The method is used to test the login manager on admin facade and to test all the admin facade methods.
     * The method prints all the methods' results using the TablePrinter class.
     *
     * @throws Exception The method might invoke exceptions as a part of the testing
     */
    public static void test() throws Exception {

//        AdminFacade adminBadLogin = (AdminFacade) LoginManager.getInstance().login("dmin@admin.com", "admin", ClientType.ADMINISTRATOR);

        AdminFacade adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);

        adminFacade.addCompany(new Company(0, "facebook", "mark.com", "232323"));
        adminFacade.addCompany(new Company(0, "max", "max.com", "12345"));
        adminFacade.addCompany(new Company(0, "bezeq", "bezeq.com", "78910"));

        System.out.println(PrintColors.GREEN_BOLD + "\t ALL COMPANIES IN THE SYSTEM");
        System.out.println(PrintColors.BLUE_BOLD);
        TablePrinter.print(adminFacade.getAllCompanies());

        adminFacade.updateCompany(new Company(1, "facebook", "markZuck.com", "232323"));
        adminFacade.deleteCompany(3);

        System.out.println(PrintColors.GREEN_BOLD + "\t ONE COMPANY ");
        System.out.println(PrintColors.BLUE_BOLD);
        TablePrinter.print(adminFacade.getOneCompany(2));

        adminFacade.addCustomer(new Customer(0, "tal", "bechor", "tal.com", "0608"));
        adminFacade.addCustomer(new Customer(0, "oren", "levi", "oren.com", "0103"));
        adminFacade.addCustomer(new Customer(0, "omer", "cohen", "omer.com", "222"));
        adminFacade.addCustomer(new Customer(0, "lea", "lea", "lea.com", "999"));

        System.out.println(PrintColors.GREEN_BOLD + "\t ALL CUSTOMER IN THE SYSTEM ");
        System.out.println(PrintColors.BLUE_BOLD);
        TablePrinter.print(adminFacade.getAllCustomers());


        adminFacade.updateCustomer(new Customer(2, "oren", "levi", "oren1levi.com", "111"));
        adminFacade.deleteCustomer(3);


        System.out.println(PrintColors.GREEN_BOLD + "\t ONE CUSTOMER ");
        System.out.println(PrintColors.BLUE_BOLD);
        TablePrinter.print(adminFacade.getOneCustomer(1));
    }

}
