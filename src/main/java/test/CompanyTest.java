package test;

import beans.Category;
import beans.Coupon;
import facades.CompanyFacade;
import login.ClientType;
import login.LoginManager;
import utils.PrintColors;
import utils.TablePrinter;

import java.sql.Date;
import java.time.LocalDate;

public class CompanyTest {
    /**
     * The method is used to test the login manager on admin facade and to test all the admin facade methods.
     * The method prints all the methods' results using the TablePrinter class.
     *
     * @throws Exception The method might invoke exceptions as a part of the testing
     */
    public static void test() throws Exception {
        CompanyFacade companyFacade = (CompanyFacade) LoginManager.getInstance().login("max.com", "12345", ClientType.COMPANY);

        companyFacade.addCoupon(new Coupon(0, 2, 4, "hotel", "in eilat",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(12)), 10, 2500, "danHotel.com"));
        companyFacade.addCoupon(new Coupon(0, 2, 1, "MCdonals", "big meal",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(12)), 60, 45, "mc.com"));
        companyFacade.addCoupon(new Coupon(0, 2, 1, "sushi", "combination",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(12)), 30, 90, "frame.com"));


        System.out.println(PrintColors.GREEN_BOLD + "\t COMPANY COUPONS" + PrintColors.CYAN_BOLD + "  id = 2");
        System.out.println(PrintColors.BLUE_BOLD);
        TablePrinter.print(companyFacade.getCompanyCoupons());

        companyFacade.updateCoupon(new Coupon(1, 2, 4, "hotel 5", "in eilat", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(12)),
                10, 2500, "danHotel.com"));
        companyFacade.deleteCoupon(3);

        System.out.println(PrintColors.GREEN_BOLD + "\t COMPANY COUPONS BY CATEGORY" + PrintColors.CYAN_BOLD + " Category = FOOD , Index = 1");
        System.out.println(PrintColors.BLUE_BOLD);
        TablePrinter.print(companyFacade.getCompanyCouponsByCategory(Category.FOOD));

        System.out.println(PrintColors.GREEN_BOLD + "\t COMPANY COUPONS BY MAX PRICE" + PrintColors.CYAN_BOLD + "  Price = 60");
        System.out.println(PrintColors.BLUE_BOLD);
        TablePrinter.print(companyFacade.getCompanyCouponsByMaxPrice(60.0));

        System.out.println(PrintColors.GREEN_BOLD + "\t COMPANY DETAILS" + PrintColors.CYAN_BOLD);
        System.out.println(PrintColors.BLUE_BOLD);
        TablePrinter.print(companyFacade.getCompanyDetails());

    }

}
