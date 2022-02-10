package facades;

import beans.Category;
import beans.Company;
import beans.Coupon;
import dao.CompaniesDAO;
import dao.CouponsDAO;
import db.db_manager.DBManagerCoupons;
import dbdao.CompaniesDBDAO;
import dbdao.CouponsDBDAO;
import exceptions.AlreadyExistException;
import exceptions.DBDAOException;
import exceptions.NotExistException;
import login.ClientType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyFacade extends ClientFacade {
    private int companyID;

    private static CompaniesDAO companiesDAO = new CompaniesDBDAO();
    private static CouponsDAO couponsDAO = new CouponsDBDAO();

    /**
     * Default Constructor - used to create a customer facade after correct login
     */
    public CompanyFacade() {

    }

    /**
     * The method checks if the company exists in the database, and gets its ID from the database
     *
     * @param email    Email from login entrance
     * @param password Password from login entrance
     * @throws NotExistException Invoked if the company does not exist in the system
     * @throws DBDAOException    Invoked if getOneCompany() method has failed
     */
    @Override
    public boolean login(String email, String password) throws NotExistException, DBDAOException {
        if (companiesDAO.isCompanyExists(email, password)) {
            this.companyID = companiesDAO.getOneCompany(email, password).getId();
            return true;
        }
        return false;
    }

    /**
     * The method allows a company to add a coupon to the database,
     * as long as the coupon title doesn't match an existing coupon title of the same company.
     *
     * @param coupon Coupon instance
     * @throws NotExistException     Invoked if the coupon does not exist in the database
     * @throws AlreadyExistException Invoked if the coupon already exists in the database
     * @throws DBDAOException        Invoked if the add action failed
     */
    public boolean addCoupon(Coupon coupon) throws NotExistException, AlreadyExistException, DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, this.companyID);
        values.put(2, coupon.getTitle());
        if (couponsDAO.isExists(DBManagerCoupons.COUNT_COUPONS_BY_COMPANY_ID_AND_TITLE, values)) {
            throw new AlreadyExistException(ClientType.COUPON);
        }
        return couponsDAO.addCoupon(coupon);
    }

    /**
     * The method allows a company to update a coupon on the database,
     * if the coupon's ID and the company's ID hasn't changed.
     *
     * @param coupon Coupon instance
     * @throws NotExistException Invoked if the coupon does not exist in the database
     * @throws DBDAOException    Invoked if the update action failed
     */
    public boolean updateCoupon(Coupon coupon) throws NotExistException, DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, coupon.getId());
        values.put(2, this.companyID);
        if (couponsDAO.isExists(DBManagerCoupons.COUNT_COUPONS_BY_ID_AND_COMPANY_ID, values)) {
            return couponsDAO.updateCoupon(coupon);
        }
        throw new NotExistException(ClientType.COUPON);
    }

    /**
     * The method allows to delete a coupon from the database by coupon ID,
     * if the coupon's ID and the company's ID entered exists in the database.
     *
     * @param id Coupon's ID
     * @throws NotExistException Invoked if the coupon does not exist in the database
     * @throws DBDAOException    Invoked if the delete action failed
     */
    public boolean deleteCoupon(int id) throws NotExistException, DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, id);
        values.put(2, this.companyID);
        if (couponsDAO.isExists(DBManagerCoupons.COUNT_COUPONS_BY_ID_AND_COMPANY_ID, values)) {
            return couponsDAO.deleteCoupon(id);
        }
        throw new NotExistException(ClientType.COUPON);
    }

    /**
     * The method gets a list of one company's coupons from the database
     *
     * @return List of one company's coupons from the database
     * @throws DBDAOException Invoked if the read action failed
     */
    public List<Coupon> getCompanyCoupons() throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, this.companyID);
        return couponsDAO.getAllCoupons(DBManagerCoupons.GET_COUPONS_BY_COMPANY_ID, values);
    }

    /**
     * The method gets a list of one company's coupons by category from the database
     *
     * @param category Specific category enum
     * @return List of coupons that are owned by the specific company according to a specific category
     * @throws DBDAOException Invoked if read action has failed
     */
    public List<Coupon> getCompanyCouponsByCategory(Category category) throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, this.companyID);
        values.put(2, category.VALUE);
        return couponsDAO.getAllCoupons(DBManagerCoupons.GET_COUPONS_BY_COMPANY_ID_AND_CATEGORY_ID, values);
    }

    /**
     * The method gets a list of one company's coupons by max price from the database
     *
     * @param maxPrice maximum price to search for
     * @return List of coupons that are owned by the specific company according to specific max price
     * @throws DBDAOException Invoked if read action has failed
     */
    public List<Coupon> getCompanyCouponsByMaxPrice(double maxPrice) throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, this.companyID);
        values.put(2, maxPrice);
        return couponsDAO.getAllCoupons(DBManagerCoupons.GET_COUPONS_BY_COMPANY_ID_AND_MAX_PRICE, values);
    }

    /**
     * The method uses the specific company's id to retrieve their details from the database
     *
     * @return Company instance
     * @throws DBDAOException Invoked if read action has failed
     */
    public Company getCompanyDetails() throws DBDAOException {
        return companiesDAO.getOneCompany(this.companyID);
    }

}
