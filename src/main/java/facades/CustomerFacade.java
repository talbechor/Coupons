package facades;

import beans.Category;
import beans.Coupon;
import beans.Customer;
import dao.CouponsDAO;
import dao.CustomersDAO;
import db.db_manager.DBManagerCoupons;
import db.db_manager.DBManagerCustomers;
import dbdao.CouponsDBDAO;
import dbdao.CustomersDBDAO;
import exceptions.AlreadyExistException;
import exceptions.DBDAOException;
import exceptions.NotExistException;
import login.ClientType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerFacade extends ClientFacade {
    private int customerID;

    private static CouponsDAO couponsDAO = new CouponsDBDAO();
    private static CustomersDAO customersDAO = new CustomersDBDAO();

    /**
     * Default Constructor - used to create a customer facade after correct login
     */
    public CustomerFacade() {

    }

    /**
     * The method checks if the customer exists in the database, and gets his ID from the database
     *
     * @param email    Email from login entrance
     * @param password Password from login entrance
     * @return boolean that determines if the customer exists, and if his ID was set to customerID attribute
     * @throws NotExistException Invoked if the customer does not exist in the system
     * @throws DBDAOException    Invoked if getOneCustomer() method has failed
     */

    @Override
    public boolean login(String email, String password) throws NotExistException, DBDAOException {
        if (customersDAO.isCustomerExists(email, password)) {
            this.customerID = customersDAO.getOneCustomer(email, password).getId();
            return true;
        }
        return false;
    }

    /**
     * The method allows the customer to purchase a coupon if the purchase is valid
     *
     * @param coupon Coupon instance
     * @return boolean that determines if the customer has succeeded to purchase the coupon
     * @throws NotExistException     Invoked if the customer is trying to purchase a coupon that doesn't exist
     *                               / the coupon's amount is zero / the coupon is expired
     * @throws AlreadyExistException Invoked if the customer already purchased this coupon
     * @throws DBDAOException        Invoked if the purchase action failed
     */

    public boolean purchaseCoupon(Coupon coupon) throws NotExistException, AlreadyExistException, DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, this.customerID);
        values.put(2, coupon.getId());
        if (customersDAO.isExists(DBManagerCustomers.COUNT_COUPON_PURCHASE, values)) {
            throw new AlreadyExistException(ClientType.COUPON);
        }
        values.clear();
        values.put(1, coupon.getId());
        if (!couponsDAO.isExists(DBManagerCoupons.COUNT_COUPONS_BY_ID, values)) {
            throw new NotExistException(ClientType.COUPON);
        }
        if (!couponsDAO.isExists(DBManagerCoupons.COUNT_COUPONS_AMOUNT_BY_ID, values)) {
            throw new NotExistException(ClientType.COUPON);
        }
        if (couponsDAO.isExists(DBManagerCoupons.COUNT_EXPIRED_COUPONS_BY_ID, values)) {
            throw new NotExistException(ClientType.COUPON);
        }
        if (!couponsDAO.addCouponPurchase(this.customerID, coupon.getId())) {
            throw new NotExistException(ClientType.COUPON);
        }
        return couponsDAO.updateCouponAmount(coupon.getId());
    }

    /**
     * The method gets a list of one customer's coupons from the database
     *
     * @return List of coupons that is purchased by the specific customer
     * @throws DBDAOException Invoked if the read action failed
     */

    public List<Coupon> getCustomerCoupons() throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, this.customerID);
        return couponsDAO.getAllCoupons(DBManagerCustomers.FIND_CUSTOMER_COUPONS, values);
    }

    /**
     * The method gets a list of one customer's coupons by category from the database
     *
     * @param category Specific category enum
     * @return List of coupons that is purchased by the specific customer according to a specific category
     * @throws DBDAOException Invoked if read action has failed
     */

    public List<Coupon> getCustomerCouponsByCategory(Category category) throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, this.customerID);
        values.put(2, category.VALUE);
        return couponsDAO.getAllCoupons(DBManagerCustomers.FIND_CUSTOMER_COUPONS_BY_CATEGORY, values);
    }

    /**
     * The method gets a list of one customer's coupons by max price from the database
     *
     * @param maxPrice maximum price to search for
     * @return List of coupons that is purchased by the specific customer according to specific max price
     * @throws DBDAOException Invoked if read action has failed
     */

    public List<Coupon> getCustomerCouponsByMaxPrice(double maxPrice) throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, this.customerID);
        values.put(2, maxPrice);
        return couponsDAO.getAllCoupons(DBManagerCustomers.FIND_CUSTOMER_COUPONS_BY_MAX_PRICE, values);
    }

    /**
     * The method uses the specific customer's id to retrieve their details from the database
     *
     * @return Customer instance
     * @throws DBDAOException Invoked if read action has failed
     */

    public Customer getCustomerDetails() throws DBDAOException {
        return customersDAO.getOneCustomer(this.customerID);
    }

}
