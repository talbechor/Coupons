package beans;

import exceptions.Action;
import exceptions.MethodNotAllowedException;

import java.sql.Date;
import java.time.LocalDate;

public class Coupon {
    private int id;
    private int companyID;
    private Category category;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;

    private int categoryIndex;

    /**
     * Constructor - Used to create a new Coupon instance with values
     *
     * @param id            ID to match SQL records
     * @param companyID     coupon's company ID
     * @param categoryIndex coupon's category index
     * @param title         coupon's title
     * @param description   coupon's description
     * @param startDate     coupon's start date
     * @param endDate       coupon's end date
     * @param amount        coupon's amount
     * @param price         coupon's price
     * @param image         coupon's image
     */
    public Coupon(int id, int companyID, int categoryIndex, String title,
                  String description, Date startDate, Date endDate, int amount, double price, String image) {
        this.id = id;
        this.companyID = companyID;
        setCategoryIndex(categoryIndex);
        setTitle(title);
        setDescription(description);
        setStartDate(startDate);
        setEndDate(endDate);
        setAmount(amount);
        setPrice(price);
        setImage(image);
    }

    /**
     * Returns the ID of the coupon's instance.
     */
    public int getId() {
        return id;
    }

    /**
     * Updates the ID of a coupon's instance.
     *
     * @param id ID sent by user to replace current ID
     * @throws MethodNotAllowedException - A custom exception created to be invoked when this method is activated
     *                                   (Method is not allowed to use)
     */
    private void setId(int id) throws MethodNotAllowedException {
        throw new MethodNotAllowedException(Action.UPDATE);
    }

    /**
     * Returns the company ID of the coupon's instance.
     */
    public int getCompanyID() {
        return companyID;
    }

    /**
     * Updates the company ID of a coupon's instance.
     *
     * @param companyID Company ID sent by user to replace current Company ID
     * @throws MethodNotAllowedException - A custom exception created to be invoked when this method is activated
     *                                   (Method is not allowed to use)
     */
    private void setCompanyID(int companyID) throws MethodNotAllowedException {
        throw new MethodNotAllowedException(Action.UPDATE);
    }

    /**
     * Returns the category of the coupon's instance.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Updates the category of a coupon's instance.
     *
     * @param category Category sent by user to replace current category
     */
    private void setCategory(Category category) {
        for (Category item : Category.values()) {
            if (item.VALUE == category.VALUE) {
                this.category = category;
                return;
            }
        }
    }

    /**
     * Returns the title of the coupon's instance.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Updates the title of a coupon's instance.
     *
     * @param title Title sent by user to replace current title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the description of the coupon's instance.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates the description of a coupon's instance.
     *
     * @param description Description sent by user to replace current description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the start date of the coupon's instance.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Updates the start date of a coupon's instance.
     *
     * @param startDate Start date sent by user to replace current start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the end date of the coupon's instance.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Checks that end date is not before start date,
     * And updates the end date of a coupon's instance.
     *
     * @param endDate End date sent by user to replace current end date
     */
    public void setEndDate(Date endDate) {
        if (endDate.after(this.startDate)) {
            this.endDate = endDate;
            return;
        }
        System.out.println("Invalid value, end date was set to tomorrow");
        this.endDate = Date.valueOf(LocalDate.now().plusDays(1));
    }

    /**
     * Returns the amount of the coupon's instance.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Updates the amount of a coupon's instance.
     *
     * @param amount Amount sent by user to replace current amount
     */
    public void setAmount(int amount) {
        if (amount < 0) {
            System.out.println("Invalid value, amount was set to 0");
            this.amount = 0;
            return;
        }
        this.amount = amount;
    }

    /**
     * Returns the price of the coupon's instance.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Updates the price of a coupon's instance.
     *
     * @param price Price sent by user to replace current price
     */
    public void setPrice(double price) {
        if (price < 0) {
            System.out.println("Invalid value, price was set to 0.0");
            this.price = 0.0;
            return;
        }
        this.price = price;
    }

    /**
     * Returns the image of the coupon's instance.
     */
    public String getImage() {
        return image;
    }

    /**
     * Updates the image of a coupon's instance.
     *
     * @param image Image sent by user to replace current image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Returns the category index of the coupon's instance.
     */
    public int getCategoryIndex() {
        return categoryIndex;
    }

    /**
     * Updates the category index of a coupon's instance, along with the category
     *
     * @param categoryIndex Category index sent by user to replace current category index and Category
     */
    public void setCategoryIndex(int categoryIndex) {
        if (categoryIndex < 1 || categoryIndex > 4) {
            System.out.println("Invalid value, category index was set to 1");
            this.categoryIndex = 1;
        } else {
            this.categoryIndex = categoryIndex;
        }
        setCategory(Category.values()[categoryIndex - 1]);
    }

    /**
     * Returns the details of a coupon's instance by String.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("ID = ");
        builder.append(id);
        builder.append(", Company ID = ");
        builder.append(companyID);
        builder.append(", Category = ");
        builder.append(category);
        builder.append(", Title = '");
        builder.append(title);
        builder.append("', Description = '");
        builder.append(description);
        builder.append("', Start Date = ");
        builder.append(startDate);
        builder.append(", End Date = ");
        builder.append(endDate);
        builder.append(", Amount = ");
        builder.append(amount);
        builder.append(", Price = ");
        builder.append(price);
        builder.append(", Image = '");
        builder.append(image);
        builder.append('\'');

        return builder.toString();
    }

}
