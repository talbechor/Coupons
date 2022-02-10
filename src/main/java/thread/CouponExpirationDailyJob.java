package thread;

import dao.CouponsDAO;
import dbdao.CouponsDBDAO;

public class CouponExpirationDailyJob implements Runnable {
    private final CouponsDAO couponDao;
    private boolean isRunning;

    private static final int TIME_CHECK = 1000 * 60 * 60 * 24;

    /**
     * This method is used to create a new Daily job thread,
     * using the deleteExpiredCoupons() method from the CouponsDBDAO and setting the isRunning boolean
     * to true for the run() function
     */
    public CouponExpirationDailyJob() {
        this.couponDao = new CouponsDBDAO();

        this.isRunning = true;
    }

    /**
     * This method will start the process of deleting the expired coupons from the database,
     * and sleep for 24 hours. the run function will not stop until the stop function is used
     */
    @Override
    public void run() {
        System.out.println("Thread is running...");
        while (isRunning) {
            try {
                couponDao.deleteExpiredCoupons();
                Thread.sleep(TIME_CHECK);

            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        }
        System.out.println("Thread has stopped");
    }

    /**
     * This method will end the thread and the process of deleting expired coupons.
     */
    public void stop() {
        System.out.println("Thread is stopping...");
        this.isRunning = false;
    }

}
