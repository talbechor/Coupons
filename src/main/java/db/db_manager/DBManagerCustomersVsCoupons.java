package db.db_manager;

public class DBManagerCustomersVsCoupons {

    //Tables
    public static final String CREATE_CUSTOMERS_VS_COUPONS_TABLE =
            "CREATE TABLE IF NOT EXISTS `coupons`.`customers_vs_coupons` (" +
                    "  `customer_id` INT NOT NULL," +
                    "  `coupon_id` INT NOT NULL," +
                    "  PRIMARY KEY (`customer_id`, `coupon_id`)," +
                    "  INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE," +
                    "  CONSTRAINT `customer_id`" +
                    "    FOREIGN KEY (`customer_id`)" +
                    "    REFERENCES `coupons`.`customers` (`id`)" +
                    "    ON DELETE CASCADE" +
                    "    ON UPDATE NO ACTION," +
                    "  CONSTRAINT `coupon_id`" +
                    "    FOREIGN KEY (`coupon_id`)" +
                    "    REFERENCES `coupons`.`coupons` (`id`)" +
                    "    ON DELETE CASCADE" +
                    "    ON UPDATE NO ACTION);";

}
