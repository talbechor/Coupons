package dbdao;

import beans.Coupon;
import dao.CouponsDAO;
import db.db_manager.DBManagerCoupons;
import exceptions.Action;
import exceptions.DBDAOException;
import exceptions.NotExistException;
import login.ClientType;
import utils.DBTools;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouponsDBDAO implements CouponsDAO {

    /**
     * A generic method which receives sql command and values, and checks if there are matching records
     * in the database.
     *
     * @param sql    SQL Command
     * @param values Map with relevant values
     * @return boolean that determines if results were found.
     * @throws NotExistException A custom exception created to be invoked in case of no matching results.
     */
    @Override
    public boolean isExists(String sql, Map<Integer, Object> values) throws NotExistException {
        ResultSet resultSet = DBTools.runQueryForResult(sql, values);
        try {
            assert resultSet != null;
            resultSet.next();
            return (resultSet.getInt("counter") > 0);
        } catch (Exception err) {
            throw new NotExistException(ClientType.COUPON);

        }
    }

    /**
     * The method receives an instance of a Coupon, and adds it to the database.
     *
     * @param coupon A coupon instance
     * @return Boolean that determines if the action was successful.
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public boolean addCoupon(Coupon coupon) throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, coupon.getCompanyID());
        values.put(2, coupon.getCategory().VALUE);
        values.put(3, coupon.getTitle());
        values.put(4, coupon.getDescription());
        values.put(5, coupon.getStartDate());
        values.put(6, coupon.getEndDate());
        values.put(7, coupon.getAmount());
        values.put(8, coupon.getPrice());
        values.put(9, coupon.getImage());
        if (DBTools.runQuery(DBManagerCoupons.ADD_COUPON, values)) {
            return true;
        }
        throw new DBDAOException(Action.CREATE, ClientType.COUPON);
    }

    /**
     * The method receives an instance of a Coupon, and updates its details in the database by the Coupon ID.
     *
     * @param coupon A coupon instance
     * @return Boolean that determines if the action was successful.
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public boolean updateCoupon(Coupon coupon) throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, coupon.getCategory().VALUE);
        values.put(2, coupon.getTitle());
        values.put(3, coupon.getDescription());
        values.put(4, coupon.getStartDate());
        values.put(5, coupon.getEndDate());
        values.put(6, coupon.getAmount());
        values.put(7, coupon.getPrice());
        values.put(8, coupon.getImage());
        values.put(9, coupon.getId());
        if (DBTools.runQuery(DBManagerCoupons.UPDATE_COUPON, values)) {
            return true;
        }
        throw new DBDAOException(Action.UPDATE, ClientType.COUPON);
    }

    /**
     * The method receives an instance of a Coupon, and deletes that instance from the database by the received ID.
     *
     * @param id A coupon's ID
     * @return Boolean that determines if the action was successful.
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public boolean deleteCoupon(int id) throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, id);
        if (DBTools.runQuery(DBManagerCoupons.DELETE_COUPON, values)) {
            return true;
        }
        throw new DBDAOException(Action.DELETE, ClientType.COUPON);

    }

    /**
     * A generic method which receives SQL command and values, and returns a list of Coupons from the database
     * according to the received values.
     *
     * @param sql    SQL Command
     * @param values Map with relevant values
     * @return List of Coupons from the database according to the received values
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public List<Coupon> getAllCoupons(String sql, Map<Integer, Object> values) throws DBDAOException {
        List<Coupon> coupons = new ArrayList<>();
        ResultSet resultSet = DBTools.runQueryForResult(sql, values);
        try {
            assert resultSet != null;
            while (resultSet.next()) {
                Coupon coupon = new Coupon(
                        resultSet.getInt("id"),
                        resultSet.getInt("company_id"),
                        resultSet.getInt("category_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date"),
                        resultSet.getInt("amount"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image")
                );
                coupons.add(coupon);
            }
            try {
                if (coupons.isEmpty()) {
                    throw new NotExistException(ClientType.COUPON);
                }
            } catch (NotExistException err) {
                System.out.println(err.getMessage() + ", List is empty ");
            }
        } catch (Exception err) {
            throw new DBDAOException(Action.READ, ClientType.COUPON);
        }
        return coupons;
    }

    /**
     * The method will return a list of all Coupons from the database.
     *
     * @return List of all Coupons from the database.
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public List<Coupon> getAllCoupons() throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        List<Coupon> coupons = new ArrayList<>();
        ResultSet resultSet = DBTools.runQueryForResult(DBManagerCoupons.GET_ALL_COUPONS, values);
        try {
            assert resultSet != null;
            while (resultSet.next()) {
                Coupon coupon = new Coupon(
                        resultSet.getInt("id"),
                        resultSet.getInt("company_id"),
                        resultSet.getInt("category_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date"),
                        resultSet.getInt("amount"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image")
                );
                coupons.add(coupon);
            }
            try {
                if (coupons.isEmpty()) {
                    throw new NotExistException(ClientType.COUPON);
                }
            } catch (NotExistException err) {
                System.out.println(err.getMessage() + ", List is empty ");
            }
        } catch (Exception err) {
            throw new DBDAOException(Action.READ, ClientType.COUPON);
        }
        return coupons;
    }

    /**
     * The method receives an ID of a Coupon, and will return the Coupon instance from the database with
     * the matching ID.
     *
     * @param couponID Coupon's ID
     * @return Coupon instance from the database with the matching ID.
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public Coupon getOneCoupon(int couponID) throws DBDAOException {
        ResultSet resultSet = DBTools.runQueryForResult(DBManagerCoupons.GET_COUPON_BY_ID, couponID);
        Coupon coupon = null;
        try {
            assert resultSet != null;
            if (resultSet.next()) {
                coupon = new Coupon(
                        resultSet.getInt("id"),
                        resultSet.getInt("company_id"),
                        resultSet.getInt("category_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date"),
                        resultSet.getInt("amount"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image")
                );
            }
            try {
                if (coupon == null) {
                    throw new NotExistException();
                }
            } catch (NotExistException err) {
                System.out.println("Coupon " + err.getMessage());
            }
        } catch (Exception err) {
            throw new DBDAOException(Action.READ, ClientType.COUPON);
        }
        return coupon;
    }

    /**
     * The method receives an ID of a coupon and an ID of a customer, and adds it to the table
     * that saves the purchase at the database.
     *
     * @param customerID Customer's ID
     * @param couponID   Coupon's ID
     * @return Boolean that determines if the action was successful.
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public boolean addCouponPurchase(int customerID, int couponID) throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customerID);
        values.put(2, couponID);
        if (DBTools.runQuery(DBManagerCoupons.ADD_COUPON_PURCHASE, values)) {
            return true;
        }
        throw new DBDAOException(Action.CREATE, ClientType.COUPON);
    }

    /**
     * The method receives an ID of a coupon and decreases the coupon's amount at the database by one,
     * used after a coupon has been purchased
     *
     * @param couponID Coupon's ID
     * @return Boolean that determines if the action was successful.
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public boolean updateCouponAmount(int couponID) throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, couponID);
        if (DBTools.runQuery(DBManagerCoupons.UPDATE_COUPON_TABLE_AMOUNT, values)) {
            return true;
        }
        throw new DBDAOException(Action.UPDATE, ClientType.COUPON);
    }

    /**
     * The method receives an ID of a coupon and an ID of a customer, and deletes the purchase from the table
     * that saves purchases at the database.
     *
     * @param customerID Customer's ID
     * @param couponID   Coupon's ID
     * @return Boolean that determines if the action was successful.
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public boolean deleteCouponPurchase(int customerID, int couponID) throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customerID);
        values.put(2, couponID);
        if (DBTools.runQuery(DBManagerCoupons.DELETE_COUPON_PURCHASE, values)) {
            return true;
        }
        throw new DBDAOException(Action.DELETE, ClientType.COUPON);
    }

    /**
     * The method deletes all expired coupons from the database.
     *
     * @return Boolean that determines if the action was successful.
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public boolean deleteExpiredCoupons() throws DBDAOException {
        if (DBTools.runQuery(DBManagerCoupons.DELETE_EXPIRED_COUPONS)) {
            return true;
        }
        throw new DBDAOException(Action.DELETE, ClientType.COUPON);
    }

}
