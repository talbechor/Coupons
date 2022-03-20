package utils;

import db.connection_pool.ConnectionPool;

import java.sql.*;
import java.util.Map;

public class DBTools {
    /**
     * This method is used to run an SQL command, and returns a boolean that determines if the command ran successfully
     *
     * @param sql SQL command
     * @return boolean that determines if the command ran successfully
     */
    public static boolean runQuery(String sql) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            return true;
        } catch (InterruptedException | SQLException err) {
            System.out.println(err.getMessage());
            return false;
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    /**
     * This method is used to run an SQL command and relevant values to replace question marks (?),
     * and returns a boolean that determines if the command ran successfully
     *
     * @param sql        SQL command
     * @param parameters Map of relevant parameters
     * @return boolean that determines if the command ran successfully
     */
    public static boolean runQuery(String sql, Map<Integer, Object> parameters) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            parameters.forEach((key, value) -> {
                try {
                    if (value instanceof Integer) {
                        preparedStatement.setInt(key, (Integer) value);
                    } else if (value instanceof String) {
                        preparedStatement.setString(key, String.valueOf(value));
                    } else if (value instanceof Date) {
                        preparedStatement.setDate(key, (Date) value);
                    } else if (value instanceof Boolean) {
                        preparedStatement.setBoolean(key, (Boolean) value);
                    } else if (value instanceof Double) {
                        preparedStatement.setDouble(key, (Double) value);
                    } else if (value instanceof Float) {
                        preparedStatement.setFloat(key, (Float) value);
                    }
                } catch (SQLException err) {
                    System.out.println(err.getMessage());
                }
            });
            preparedStatement.execute();
            return true;

        } catch (InterruptedException | SQLException err) {
            System.out.println(err.getMessage());
            return false;

        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    /**
     * This method is used to run an SQL command specifically in order to get data from the database,
     * along with relevant values to replace question marks (?), and returns the data retrieved from the database
     *
     * @param sql        SQL command
     * @param parameters Map of relevant parameters
     * @return Result set that stores the requested data from the database
     */
    public static ResultSet runQueryForResult(String sql, Map<Integer, Object> parameters) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            parameters.forEach((key, value) -> {
                try {
                    if (value instanceof Integer) {
                        preparedStatement.setInt(key, (Integer) value);
                    } else if (value instanceof String) {
                        preparedStatement.setString(key, String.valueOf(value));
                    } else if (value instanceof Date) {
                        preparedStatement.setDate(key, (Date) value);
                    } else if (value instanceof Boolean) {
                        preparedStatement.setBoolean(key, (Boolean) value);
                    } else if (value instanceof Double) {
                        preparedStatement.setDouble(key, (Double) value);
                    } else if (value instanceof Float) {
                        preparedStatement.setFloat(key, (Float) value);
                    } else if (value instanceof Enum) {
                        preparedStatement.setString(key, String.valueOf(value));
                    }

                } catch (SQLException err) {
                    System.out.println(err.getMessage());
                }
            });
            return preparedStatement.executeQuery();

        } catch (InterruptedException | SQLException err) {
            System.out.println(err.getMessage());
            return null;
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }

    }

    /**
     * This method is used to run an SQL command specifically in order to get data from the database by ID,
     * and relevant ID to replace the question mark (?), and returns the data retrieved from the database
     *
     * @param sql SQL command
     * @param id  relevant ID for search
     * @return Result set that stores the requested data from the database
     */
    public static ResultSet runQueryForResult(String sql, int id) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();

        } catch (InterruptedException | SQLException err) {
            System.out.println(err.getMessage());
            return null;
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

}
