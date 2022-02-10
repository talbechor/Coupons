package test;

import db.connection_pool.ConnectionPool;
import facades.AdminFacade;
import facades.CompanyFacade;
import facades.CustomerFacade;
import login.ClientType;
import login.LoginManager;
import thread.CouponExpirationDailyJob;

public class Test {
    /**
     * This method is used to run all the relevant tests in order to check the program is working properly
     */
    public static void testAll() {
        try {
            if (SchemaTest.createSchema()) {
                System.out.println("The database has been set!");
            } else {
                System.exit(0);
            }

            CouponExpirationDailyJob couponExpirationDailyJob = new CouponExpirationDailyJob();
            Thread thread = new Thread(couponExpirationDailyJob);
            thread.start();

            AdminTest.test();
            CompanyTest.test();
            CustomerTest.test();
//            testBadLogin();

            couponExpirationDailyJob.stop();
//            SchemaTest.cleanDB();
            ConnectionPool.getInstance().closeAllConnections();

        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

    /**
     * This method is used to run test bad login requests.
     *
     * @throws Exception - Invoked due to bad logins that will invoke login exceptions
     */
    public static void testBadLogin() throws Exception {
        AdminFacade adminBadLogin = (AdminFacade) LoginManager.getInstance().login("admin.com", "admin", ClientType.ADMINISTRATOR);
        CompanyFacade companyBadLogin = (CompanyFacade) LoginManager.getInstance().login("max1.com", "12345", ClientType.COMPANY);
        CustomerFacade customerBadLogin = (CustomerFacade) LoginManager.getInstance().login("tal1.com", "0608", ClientType.CUSTOMER);


    }

}
