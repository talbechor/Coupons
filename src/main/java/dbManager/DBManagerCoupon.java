package dbManager;

import java.awt.*;

public class DBManagerCoupon {

    //Tables

    public static final String CREATE_COUPONS_TABLE = "CREATE TABLE IF NOT EXISTS `coupons`.`coupons` (" +
            "  `id` INT NOT NULL," +
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
            "  INDEX `id_idx` (`company_id` ASC) VISIBLE," +
            "  INDEX `id_idx1` (`category_id` ASC) VISIBLE," +
            "  CONSTRAINT `company_id`" +
            "    FOREIGN KEY (`company_id`)" +
            "    REFERENCES `coupons`.`companies` (`id`)" +
            "    ON DELETE NO ACTION" +
            "    ON UPDATE NO ACTION," +
            "  CONSTRAINT `category_id`" +
            "    FOREIGN KEY (`category_id`)" +
            "    REFERENCES `coupons`.`categories` (`id`)" +
            "    ON DELETE NO ACTION" +
            "    ON UPDATE NO ACTION)";


    public static final String CREATE_NEW_COUPON="";
    public static final String DELETE_COUPON="";
    public static final String UPDATE_COUPON="";
    public static final String GET_ALL_COUPONS="";
    public static final String GET_SINGLE_COUPON="";

    public static final String GET_BY_DESCRIPTION="";
    public static final String GET_BY_TITLE="";
    public static final String GET_BY_START_DATE="";
    public static final String GET_BY_END_DATE="";
    public static final String GET_BY_AMOUNT="";
    public static final String GET_BY_PRICE="";
    public static final String GET_BY_IMAGE="";

}
