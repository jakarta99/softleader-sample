package tw.com.softleader.sample.work;

import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Jerry.lin on 2017/7/9.
 */
public class WorkDao implements GenericDao<Work>{




    public WorkDao () {
            this.dataSource = DataSourceUtil
                    .getInstance()
                    .getDataSource();

    }

    private  DataSource dataSource;

    @Override
    public Work findOne(Long id) {
        Work entity = null;
        String sqlCmd = "SELECT * FROM work where ID = " + id;
        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sqlCmd);){


            final JPersonDao jPersonDao = new JPersonDao();

            if(rs.next()) {

                entity = new Work();
                entity.setId(rs.getLong("ID"));
                entity.setName(rs.getString("NAME"));
                entity.setjPerson(jPersonDao.findOne(rs.getLong("jpersonid")));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Collection<Work> findAll() {
        Collection<Work> Works = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            final JPersonDao jPersonDao = new JPersonDao();


            String sqlCmd = "SELECT * FROM work";

            ResultSet rs = stmt.executeQuery(sqlCmd);

            while(rs.next()) {

                Work work = new Work();
                work.setId(rs.getLong("ID"));
                work.setName(rs.getString("NAME"));
                work.setjPerson(jPersonDao.findOne(rs.getLong("jpersonid")));
                Works.add(work);

            }
            rs.close();
            stmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Works;
    }

    @Override
    public void insert(Work entity) {
        try   {
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();

            String personId = entity.getjPerson() == null ? 0+"" : entity.getjPerson().getId().toString() ;
            System.out.println();;

            String sqlCmd = "INSERT INTO work (name,jpersonid) VALUES ('"+entity.getName()+ "', '"+ personId   +"');";

            stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);
            ResultSet keySet = stmt.getGeneratedKeys();
            if(keySet.next()) {
                Long generatedId = keySet.getLong("ID");
                entity.setId(generatedId);
            }

            keySet.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Work entity) {
        try  {

            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            String personId = entity.getjPerson() == null ? 0+"" : entity.getjPerson().getId().toString() ;

            String sqlCmd = "UPDATE work SET  name = '" + entity.getName() + "', jpersonid = '" + personId + "' WHERE ID = " + entity.getId();
            stmt.executeUpdate(sqlCmd);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();

            String sqlCmd = "DELETE FROM work where ID = "+id;
            stmt.executeUpdate(sqlCmd);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<Work> findByJPersonId(Long id){
        Work entity = null;
        ArrayList<Work> works = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();


            String sqlCmd = "SELECT * FROM work where jpersonid =" + id;

            ResultSet rs = stmt.executeQuery(sqlCmd);

            while(rs.next()) {

                Work work = new Work();
                work.setId(rs.getLong("ID"));
                work.setName(rs.getString("NAME"));
                works.add(work);

            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return works;
    }
}
