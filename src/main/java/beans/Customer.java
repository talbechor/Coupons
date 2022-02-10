package beans;

import exceptions.Action;
import exceptions.MethodNotAllowedException;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Coupon> coupons;

    /**
     * Constructor - used to create a new Customer instance with values
     *
     * @param id        id to match SQL records
     * @param firstName Customer's first name
     * @param lastName  Customer's last name
     * @param email     Customer's login email
     * @param password  Customer's login password
     */
    public Customer(int id, String firstName, String lastName, String email, String password) {
        this.id = id;
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        setCoupons(new ArrayList<>());
    }

    /**
     * Returns the ID of the customer's instance.
     */
    public int getId() {
        return id;
    }

    /**
     * Updates the customer instance's ID.
     *
     * @param id ID sent by user to replace current ID
     * @throws MethodNotAllowedException - A custom exception created to be invoked when this method is activated
     *                                   (Method is not allowed to use)
     */
    private void setId(int id) throws MethodNotAllowedException {
        throw new MethodNotAllowedException(Action.UPDATE);
    }

    /**
     * Returns the first name of the customer's instance.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Updates the first name of a customer's instance.
     *
     * @param firstName First name sent by user to replace current first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the customer's instance.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Updates the last name of a customer's instance.
     *
     * @param lastName Last name sent by user to replace current last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the email of the customer's instance.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Updates the email of a customer's instance.
     *
     * @param email Email sent by user to replace current email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the password of the customer's instance.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Updates the password of a customer's instance.
     *
     * @param password Password sent by user to replace current password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the list of coupons of the customer's instance.
     */
    public List<Coupon> getCoupons() {
        return coupons;
    }

    /**
     * Updates the coupons list of a customer's instance.
     *
     * @param coupons List of coupons sent by user to replace current coupons list
     */
    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    /**
     * Returns the details of a customer's instance by String.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("ID = ");
        builder.append(this.id);
        builder.append(", First Name = ");
        builder.append(this.firstName);
        builder.append(", Last Name = ");
        builder.append(this.lastName);
        builder.append(", Email = ");
        builder.append(this.email);
        builder.append(" password = ");
        builder.append(this.password);
        builder.append(", Coupons = ");
        builder.append(this.coupons);

        return builder.toString();
    }

}
