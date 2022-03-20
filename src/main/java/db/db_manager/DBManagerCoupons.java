package db.db_manager;

public class DBManagerCoupons {

    //Table
    public static final String CREATE_COUPONS_TABLE = "CREATE TABLE IF NOT EXISTS `coupons`.`coupons` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `company_id` INT NULL," +
            "  `category_id` INT NULL," +
            "  `title` VARCHAR(45) NULL," +
            "  `description` VARCHAR(45) NULL," +
            "  `start_date` DATE NULL," +
            "  `end_date` DATE NULL," +
            "  `amount` INT NULL," +
            "  `price` DOUBLE NULL," +
            "  `image` VARCHAR(45) NULL," +
            "  PRIMARY KEY (`id`)," +
            "  INDEX `company_id_idx` (`company_id` ASC) VISIBLE," +
            "  INDEX `category_id_idx` (`category_id` ASC) VISIBLE," +
            "  CONSTRAINT `company_id`" +
            "    FOREIGN KEY (`company_id`)" +
            "    REFERENCES `coupons`.`companies` (`id`)" +
            "    ON DELETE CASCADE" +
            "    ON UPDATE NO ACTION," +
            "  CONSTRAINT `category_id`" +
            "    FOREIGN KEY (`category_id`)" +
            "    REFERENCES `coupons`.`categories` (`id`)" +
            "    ON DELETE CASCADE" +
            "    ON UPDATE NO ACTION);";

    //Create
    public static final String ADD_COUPON = " INSERT INTO  `coupons`.`coupons` " +
            " ( `company_id`, `category_id` , `title`, `description`, `start_date`, `end_date` , `amount`, `price`, `image` )"
            + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

    //Update
    public static final String UPDATE_COUPON = " UPDATE `coupons`.`coupons` " +
            " SET category_id=?, title=?, description=?, start_date=?, end_date=?, amount=?, price=?, image=?" +
            " WHERE id=?";

    //Delete
    public static final String DELETE_COUPON = " DELETE FROM `coupons`.`coupons` WHERE id=? ";

    public static final String DELETE_EXPIRED_COUPONS =
            " DELETE FROM `coupons`.`coupons` WHERE id>0 AND end_date< curdate() ";

    //Read all
    public static final String GET_ALL_COUPONS = " SELECT * FROM  `coupons`.`coupons` ";

    //Read by filter
    public static final String GET_COUPON_BY_ID = " SELECT * FROM  `coupons`.`coupons` WHERE id=? ";
    public static final String GET_COUPONS_BY_COMPANY_ID = "  SELECT * FROM  `coupons`.`coupons` WHERE company_id=? ";
    public static final String GET_COUPON_BY_CATEGORY_ID = "  SELECT * FROM  `coupons`.`coupons` WHERE category_id=? ";
    public static final String GET_COUPON_BY_DESCRIPTION = "  SELECT * FROM  `coupons`.`coupons` WHERE description=? ";
    public static final String GET_COUPON_BY_TITLE = " SELECT * FROM  `coupons`.`coupons` WHERE title=? ";
    public static final String GET_COUPON_BY_START_DATE = " SELECT * FROM  `coupons`.`coupons` WHERE start_date=? ";
    public static final String GET_COUPON_BY_END_DATE = " SELECT * FROM  `coupons`.`coupons` WHERE end_date=? ";
    public static final String GET_COUPON_BY_AMOUNT = " SELECT * FROM  `coupons`.`coupons` WHERE amount=? ";
    public static final String GET_COUPON_BY_PRICE = " SELECT * FROM  `coupons`.`coupons` WHERE price=? ";
    public static final String GET_COUPON_BY_IMAGE = " SELECT * FROM  `coupons`.`coupons` WHERE image=? ";

    public static final String GET_COUPONS_BY_COMPANY_ID_AND_CATEGORY_ID =
            "SELECT * FROM  `coupons`.`coupons` WHERE company_id=? AND category_id=?";
    public static final String GET_COUPONS_BY_COMPANY_ID_AND_MAX_PRICE =
            "  SELECT * FROM  `coupons`.`coupons` WHERE company_id=? AND price<=? ";

    //Read by range
    public static final String GET_COUPON_BY_AMOUNT_RANGE =
            "  SELECT * FROM  `coupons`.`coupons` WHERE amount BETWEEN ? and ? ";

    //Count coupons
    public static final String COUNT_COUPONS_BY_ID = "SELECT count(*) AS counter FROM `coupons`.`coupons` WHERE id=?";
    public static final String COUNT_COUPONS_BY_COMPANY_ID_AND_TITLE =
            "SELECT count(*) AS counter FROM `coupons`.`coupons` WHERE company_id=? AND title=?";
    public static final String COUNT_COUPONS_BY_ID_AND_COMPANY_ID =
            "SELECT count(*) AS counter FROM `coupons`.`coupons` WHERE id=? AND company_id=?";
    public static final String COUNT_COUPONS_AMOUNT_BY_ID =
            "SELECT count(*) AS counter FROM `coupons`.`coupons` WHERE id=? AND amount>0";
    public static final String COUNT_EXPIRED_COUPONS_BY_ID =
            "SELECT count(*) AS counter FROM `coupons`.`coupons` WHERE id=? and DATE(end_date)<DATE(now())";

    //Coupon purchase
    public static final String ADD_COUPON_PURCHASE = "  INSERT INTO `coupons`.`customers_vs_coupons` " +
            " ( `customer_id`, `coupon_id` ) " + " VALUES ( ? , ? ) ";
    public static final String UPDATE_COUPON_TABLE_AMOUNT =
            " UPDATE `coupons`.`coupons` SET amount=amount-1 WHERE id=? ";
    public static final String DELETE_COUPON_PURCHASE = " DELETE FROM `coupons`.`customers_vs_coupons` " +
            "WHERE customer_id=? AND coupon_id=? ";

}
