package dao;

import beans.Coupon;
import exceptions.DBDAOException;
import exceptions.NotExistException;

import java.util.List;
import java.util.Map;

public interface CouponsDAO {

    boolean isExists(String sql, Map<Integer, Object> values) throws NotExistException;

    boolean addCoupon(Coupon coupon) throws DBDAOException;

    boolean updateCoupon(Coupon coupon) throws DBDAOException;

    boolean deleteCoupon(int id) throws DBDAOException;

    List<Coupon> getAllCoupons(String sql, Map<Integer, Object> value) throws DBDAOException;

    List<Coupon> getAllCoupons() throws DBDAOException;

    Coupon getOneCoupon(int couponID) throws DBDAOException;

    boolean addCouponPurchase(int customerID, int couponID) throws DBDAOException;

    boolean updateCouponAmount(int couponID) throws DBDAOException;

    boolean deleteCouponPurchase(int customerID, int couponID) throws DBDAOException;

    boolean deleteExpiredCoupons() throws DBDAOException;

}
