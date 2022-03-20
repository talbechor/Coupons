package dbdao;

import beans.Company;
import dao.CompaniesDAO;
import db.db_manager.DBManagerCompanies;
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

public class CompaniesDBDAO implements CompaniesDAO {
    /**
     * The method receives a company's login credentials, and checks if there's a company with matching records
     * in the database.
     *
     * @param email    Company's email
     * @param password Company's password
     * @return boolean that determines if results were found.
     * @throws NotExistException A custom exception created to be invoked in case of no matching results.
     */
    @Override
    public boolean isCompanyExists(String email, String password) throws NotExistException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, email);
        values.put(2, password);
        ResultSet resultSet = DBTools.runQueryForResult(DBManagerCompanies.COUNT_COMPANY_BY_EMAIL_AND_PASSWORD, values);
        try {
            assert resultSet != null;
            resultSet.next();
            return (resultSet.getInt("counter") == 1);
        } catch (Exception err) {
            throw new NotExistException();
        }

    }

    /**
     * A generic method which receives SQL command and values, and checks if there are matching records
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
            throw new NotExistException();
        }
    }

    /**
     * The method receives an instance of a Company, and adds it to the database in case there is no company
     * with the same records.
     *
     * @param company Company instance
     * @return Boolean that determines if the action was successful.
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public boolean addCompany(Company company) throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, company.getName());
        values.put(2, company.getEmail());
        values.put(3, company.getPassword());
        if (DBTools.runQuery(DBManagerCompanies.ADD_COMPANY, values)) {
            return true;
        }
        throw new DBDAOException(Action.CREATE, ClientType.COMPANY);
    }

    /**
     * The method receives an instance of a Company, and updates its details in the database by the company ID.
     *
     * @param company A company instance
     * @return boolean that determines if the action was successful.
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public boolean updateCompany(Company company) throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, company.getEmail());
        values.put(2, company.getPassword());
        values.put(3, company.getId());
        if (DBTools.runQuery(DBManagerCompanies.UPDATE_COMPANY, values)) {
            return true;
        }
        throw new DBDAOException(Action.UPDATE, ClientType.COMPANY);

    }

    /**
     * The method receives an instance of a Company, and deletes that instance from the database by the received ID.
     *
     * @param companyID A company's ID
     * @return boolean that determines if the action was successful
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public boolean deleteCompany(int companyID) throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, companyID);
        if (DBTools.runQuery(DBManagerCompanies.DELETE_COMPANY, values)) {
            return true;
        }
        throw new DBDAOException(Action.DELETE, ClientType.COMPANY);
    }

    /**
     * A generic method which receives SQL command and values, and returns a list of companies from the database
     * according to the received values.
     *
     * @param sql    SQL Command
     * @param values Map with relevant values
     * @return List of companies from the database according to the received values
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public List<Company> getAllCompanies(String sql, Map<Integer, Object> values) throws DBDAOException {
        List<Company> companies = new ArrayList<>();
        ResultSet resultSet = DBTools.runQueryForResult(sql, values);
        try {
            assert resultSet != null;
            while (resultSet.next()) {
                Company company = new Company(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
                companies.add(company);
            }
            try {
                if (companies.isEmpty()) {
                    throw new NotExistException(ClientType.COMPANY);
                }
            } catch (NotExistException err) {
                System.out.println(err.getMessage() + ", List is empty ");
            }
        } catch (Exception err) {
            throw new DBDAOException(Action.READ, ClientType.COMPANY);
        }
        return companies;
    }

    /**
     * getAllCompanies() - The method will return a list of all companies from the database.
     *
     * @return List of all companies from the database.
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public List<Company> getAllCompanies() throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        List<Company> companies = new ArrayList<>();
        ResultSet resultSet = DBTools.runQueryForResult(DBManagerCompanies.GET_ALL_COMPANIES, values);
        Company company;
        try {
            assert resultSet != null;
            while (resultSet.next()) {
                company = new Company(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
                companies.add(company);
            }
            try {
                if (companies.isEmpty()) {
                    throw new NotExistException(ClientType.COMPANY);
                }
            } catch (NotExistException err) {
                System.out.println(err.getMessage() + ", List is empty ");
            }
        } catch (Exception err) {
            throw new DBDAOException(Action.READ, ClientType.COMPANY);
        }
        return companies;
    }

    /**
     * The method receives an ID of a company, and will return the company instance from the database
     * with the matching ID.
     *
     * @param companyID Company's ID
     * @return Company instance from the database with the matching ID, if it exists
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public Company getOneCompany(int companyID) throws DBDAOException {
        ResultSet resultSet = DBTools.runQueryForResult(DBManagerCompanies.GET_COMPANY_BY_ID, companyID);
        Company company = null;
        try {
            assert resultSet != null;
            if (resultSet.next()) {
                company = new Company(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }
            try {
                if (company == null) {
                    throw new NotExistException(ClientType.COMPANY);
                }
            } catch (NotExistException err) {
                System.out.println(err.getMessage());
            }
        } catch (Exception err) {
            throw new DBDAOException(Action.READ, ClientType.COMPANY);
        }
        return company;
    }

    /**
     * The method receives a company's login credentials, and will return company instance from the database
     * with the matching records.
     *
     * @param email    Company's email
     * @param password Company's password
     * @return Company instance from the database with the matching credentials
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public Company getOneCompany(String email, String password) throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, email);
        values.put(2, password);
        ResultSet resultSet = DBTools.runQueryForResult(DBManagerCompanies.GET_COMPANY_BY_EMAIL_AND_PASSWORD, values);
        Company company = null;
        try {
            assert resultSet != null;
            if (resultSet.next()) {
                company = new Company(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );

            }
            try {
                if (company == null) {
                    throw new NotExistException(ClientType.COMPANY);
                }
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        } catch (Exception err) {
            throw new DBDAOException(Action.READ, ClientType.COMPANY);
        }
        return company;
    }

}
