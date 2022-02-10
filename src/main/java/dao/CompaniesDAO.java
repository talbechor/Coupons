package dao;

import beans.Company;
import exceptions.DBDAOException;
import exceptions.NotExistException;

import java.util.List;
import java.util.Map;

public interface CompaniesDAO {

    boolean isCompanyExists(String email, String password) throws NotExistException;

    boolean isExists(String sql, Map<Integer, Object> values) throws NotExistException;

    boolean addCompany(Company company) throws DBDAOException;

    boolean updateCompany(Company company) throws DBDAOException;

    boolean deleteCompany(int companyID) throws DBDAOException;

    List<Company> getAllCompanies(String sql, Map<Integer, Object> value) throws DBDAOException;

    List<Company> getAllCompanies() throws DBDAOException;

    Company getOneCompany(int companyID) throws DBDAOException;

    Company getOneCompany(String email, String password) throws DBDAOException;

}
