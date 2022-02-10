package db.db_manager;

public class DBManagerCustomers {

    //Table
    public static final String CREATE_CUSTOMERS_TABLE = "CREATE TABLE IF NOT EXISTS `coupons`.`customers` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `first_name` VARCHAR(45) NULL," +
            "  `last_name` VARCHAR(45) NULL," +
            "  `email` VARCHAR(45) NULL," +
            "  `password` VARCHAR(45) NULL," +
            "  PRIMARY KEY (`id`))";

    //Create
    public static final String ADD_CUSTOMER = " INSERT INTO  `coupons`.`customers` " +
            " (`first_name`,`last_name`,`email`,`password`) "
            + " VALUES ( ?, ?, ?, ?) ";

    //Update
    public static final String UPDATE_CUSTOMER = " UPDATE `coupons`.`customers` " +
            " SET  first_name = ?, last_name = ?, email = ?, password = ? "
            + " WHERE id = ? ";

    //Delete
    public static final String DELETE_CUSTOMER = " DELETE FROM `coupons`.`customers` WHERE id=? ";

    //Read all
    public static final String GET_ALL_CUSTOMERS = " SELECT * FROM  `coupons`.`customers` ";

    //Read by filter
    public static final String GET_CUSTOMER_BY_ID = " SELECT * FROM `coupons`.`customers` WHERE id=? ";
    public static final String GET_CUSTOMER_BY_FIRST_NAME = " SELECT * FROM `coupons`.`customers` WHERE first_name=? ";
    public static final String GET_CUSTOMER_BY_LAST_NAME = " SELECT * FROM `coupons`.`customers` WHERE last_name=? ";
    public static final String GET_CUSTOMER_BY_EMAIL = " SELECT * FROM `coupons`.`customers` WHERE email=? ";

    public static final String GET_CUSTOMER_BY_FULL_NAME = " SELECT * FROM `coupons`.`customers` WHERE first_name=? AND last_name=? ";
    public static final String GET_CUSTOMERS_BY_EMAIL_AND_PASSWORD =
            " SELECT * FROM  `coupons`.`customers` WHERE email=? AND password =? ";

    //Count Customer
    public static final String COUNT_CUSTOMER_BY_ID =
            "SELECT count(*) AS counter FROM `coupons`.`customers` WHERE id=? ";
    public static final String COUNT_CUSTOMER_BY_EMAIL =
            "SELECT count(*) AS counter FROM `coupons`.`customers` WHERE email=?";

    public static final String COUNT_CUSTOMER_BY_EMAIL_AND_PASSWORD =
            " SELECT count(*) AS counter FROM `coupons`.`customers` WHERE email=? AND password=? ";

    public static final String COUNT_COUPON_PURCHASE =
            " SELECT count(*) AS counter FROM `coupons`.`customers_vs_coupons` WHERE customer_id=? AND coupon_id=?";

    //Find customer coupons
    public static final String FIND_CUSTOMER_COUPONS =
            " SELECT * FROM `coupons`.`coupons` " +
                    " INNER JOIN `coupons`.`customers_vs_coupons` " +
                    " ON `coupons`.`coupons`.id = `coupons`.`customers_vs_coupons`.coupon_id " +
                    " WHERE `coupons`.`customers_vs_coupons`.customer_id=? ";

    public static final String FIND_CUSTOMER_COUPONS_BY_CATEGORY =
            " SELECT * FROM `coupons`.`coupons` " +
                    " INNER JOIN `coupons`.`customers_vs_coupons` " +
                    " ON `coupons`.`coupons`.id = `coupons`.`customers_vs_coupons`.coupon_id " +
                    " WHERE `coupons`.`customers_vs_coupons`.customer_id=? AND `coupons`.`coupons`.category_id=?";

    public static final String FIND_CUSTOMER_COUPONS_BY_MAX_PRICE =
            "SELECT * FROM `coupons`.`coupons` INNER JOIN `coupons`.`customers_vs_coupons` " +
                    " ON `coupons`.`coupons`.id = `coupons`.`customers_vs_coupons`.coupon_id " +
                    "WHERE `coupons`.`customers_vs_coupons`.customer_id=? AND `coupons`.`coupons`.price BETWEEN 0 and ? ";

}
