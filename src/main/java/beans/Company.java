package beans;

import exceptions.Action;
import exceptions.MethodNotAllowedException;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private int id;
    private String name;
    private String email;
    private String password;
    private List<Coupon> coupons;

    /**
     * Constructor - Used to create a new Company instance with values
     *
     * @param id       ID to match SQL records
     * @param name     Company's name
     * @param email    Company's login email
     * @param password Company's login password
     */
    public Company(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        setEmail(email);
        setPassword(password);
        setCoupons(new ArrayList<>());
    }

    /**
     * Returns the ID of the company's instance.
     */
    public int getId() {
        return id;
    }

    /**
     * Updates the ID of a company's instance.
     *
     * @param id ID sent by user to replace current ID
     * @throws MethodNotAllowedException A custom exception created to be invoked when this method is activated
     *                                   (Method is not allowed to use)
     */
    private void setId(int id) throws MethodNotAllowedException {
        throw new MethodNotAllowedException(Action.UPDATE);
    }

    /**
     * Returns the name of the company's instance.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the company instance's name
     *
     * @param name Name sent by user to replace current name
     * @throws MethodNotAllowedException A custom exception created to be invoked when this method is activated
     *                                   (Method is not allowed to use)
     */
    private void setName(String name) throws MethodNotAllowedException {
        throw new MethodNotAllowedException(Action.UPDATE);
    }

    /**
     * Returns the email of the company's instance.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Updates the email of a company's instance.
     *
     * @param email Email sent by user to replace current email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the password of the company's instance.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Updates the password of a company's instance.
     *
     * @param password Password sent by user to replace current password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the list of coupons of the company's instance.
     */
    public List<Coupon> getCoupons() {
        return coupons;
    }

    /**
     * Updates the coupons list of a company's instance.
     *
     * @param coupons List of coupons sent by user to replace current coupons list
     */
    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    /**
     * Returns the details of a company's instance by String.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("ID = ");
        builder.append(this.id);
        builder.append(", Name = ");
        builder.append(this.name);
        builder.append(", Email = ");
        builder.append(this.email);
        builder.append(" password = ");
        builder.append(this.password);
        builder.append(", Coupons = ");
        builder.append(this.coupons);

        return builder.toString();
    }

}
