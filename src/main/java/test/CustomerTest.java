package test;

import beans.Category;
import beans.Coupon;
import facades.CustomerFacade;
import login.ClientType;
import login.LoginManager;
import utils.PrintColors;
import utils.TablePrinter;

import java.sql.Date;
import java.time.LocalDate;

public class CustomerTest {
    /**
     * The method is used to test the login manager on customer facade and to test all the customer facade methods.
     * The method prints all the methods' results using the TablePrinter class.
     *
     * @throws Exception The method might invoke exceptions as a part of the testing
     */
    public static void test() throws Exception {
        CustomerFacade customerFacade = (CustomerFacade) LoginManager.getInstance().login("tal.com", "0608", ClientType.CUSTOMER);

        customerFacade.purchaseCoupon(new Coupon(1, 2, 1, "hotel 5", "in eilat",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(12)), 7, 2500, "danHotel.com"));
        customerFacade.purchaseCoupon(new Coupon(2, 2, 1, "MCdonals", "big meal",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(12)), 60, 45, "mc.com"));

        System.out.println(PrintColors.GREEN_BOLD + "\t CUSTOMER COUPONS" + PrintColors.CYAN_BOLD + "  ID = 1");
        System.out.println(PrintColors.BLUE_BOLD);
        TablePrinter.print(customerFacade.getCustomerCoupons());

        System.out.println(PrintColors.GREEN_BOLD + "\t CUSTOMER COUPONS BY CATEGORY" + PrintColors.CYAN_BOLD + " Category = FOOD , Index = 1");
        System.out.println(PrintColors.BLUE_BOLD);
        TablePrinter.print(customerFacade.getCustomerCouponsByCategory(Category.FOOD));

        System.out.println(PrintColors.GREEN_BOLD + "\t CUSTOMER COUPONS BY MAX PRICE" + PrintColors.CYAN_BOLD + " Price = 60");
        System.out.println(PrintColors.BLUE_BOLD);
        TablePrinter.print(customerFacade.getCustomerCouponsByMaxPrice(60.0));

        System.out.println(PrintColors.GREEN_BOLD + "\t CUSTOMER DETAILS" + PrintColors.CYAN_BOLD);
        System.out.println(PrintColors.BLUE_BOLD);
        TablePrinter.print(customerFacade.getCustomerDetails());
    }

}
