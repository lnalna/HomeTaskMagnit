package magnit.test.dao;

import magnit.test.exception.DaoException;
import magnit.test.pojo.Entry;

import java.sql.*;
import java.util.ArrayList;


public class DaoDatabaseActions {

    private static final String SELECT_ACTION = "SELECT FIELD FROM TEST ORDER BY FIELD ASC";
    private static final String DELETE_ACTION = "DELETE FROM TEST";
    private static final String INSERT_ACTION = "INSERT INTO TEST ( FIELD ) VALUES (?)";


    private Connection getConnection() throws SQLException {
        Connection connection = DaoDataSource.getDataSource().getConnection();
        return connection;
    }

    /*
    Delete all records from table
     */
    public void deleteAllFields() throws Exception{
        try(Statement stmt = getConnection().createStatement()){
            stmt.executeUpdate(DELETE_ACTION);
        } catch (SQLException e){
            throw new DaoException(e);
        }
    }

    /*
    Select all records from table
     */
    public ArrayList<Entry> selectAllFields() throws Exception {
        ArrayList<Entry> entries = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery(SELECT_ACTION);
            while (resultSet.next()) {
                Entry entry = new Entry();
                entry.setField(resultSet.getInt("FIELD"));
                entries.add(entry);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return entries;
    }

    public void insertFields(int from, int to) throws Exception{
        try (Connection connection = getConnection()){
            connection.setAutoCommit(false);
            try (PreparedStatement ps = connection.prepareStatement(INSERT_ACTION)){

                for(int i=from; i<=to; i++){
                    ps.setInt(1,i);
                    ps.addBatch();
                }
                ps.executeBatch();
                connection.commit();
            } catch (SQLException e){
                throw new DaoException(e);
            }
            connection.setAutoCommit(true);

        } catch (SQLException e){
            throw new DaoException(e);
        }
    }
}
