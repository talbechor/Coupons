package dbManager;

public class DBManagerCoupon {


    //Database
    public static final String CREATE_DB = "CREATE SCHEMA IF NOT EXISTS `coupons`";
    public static final String DROP_DB = "DROP SCHEMA `coupons`";

    //Tables
    public static final String CREATE_COMPANIES_TABLE = "CREATE TABLE IF NOT EXISTS `coupons`.`companies` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `name` VARCHAR(45) NULL," +
            "  `email` VARCHAR(45) NULL," +
            "  `password` VARCHAR(45) NULL," +
            "  PRIMARY KEY (`id`))";

    public static final String CREATE_CUSTOMERS_TABLE = "CREATE TABLE IF NOT EXISTS `coupons`.`customers` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `first_name` VARCHAR(45) NULL," +
            "  `last_name` VARCHAR(45) NULL," +
            "  `email` VARCHAR(45) NULL," +
            "  `password` VARCHAR(45) NULL," +
            "  PRIMARY KEY (`id`))";

    public static final String CREATE_CATEGORIES_TABLE = "CREATE TABLE IF NOT EXISTS `coupons`.`categories` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `name` VARCHAR(45) NULL," +
            "  PRIMARY KEY (`id`))";
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

    public static final String CREATE_CUSTOMERS_VS_COUPONS_TABLE =
            "CREATE TABLE IF NOT EXISTS `coupons`.`customers_vs_coupons` (" +
                    "  `customer_id` INT NOT NULL," +
                    "  `coupon_id` INT NOT NULL," +
                    "  PRIMARY KEY (`customer_id`, `coupon_id`)," +
                    "  INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE," +
                    "  CONSTRAINT `customer_id`" +
                    "    FOREIGN KEY (`customer_id`)" +
                    "    REFERENCES `coupons`.`customers` (`id`)" +
                    "    ON DELETE NO ACTION" +
                    "    ON UPDATE NO ACTION," +
                    "  CONSTRAINT `coupon_id`" +
                    "    FOREIGN KEY (`coupon_id`)" +
                    "    REFERENCES `coupons`.`coupons` (`id`)" +
                    "    ON DELETE NO ACTION" +
                    "    ON UPDATE NO ACTION)";
}
