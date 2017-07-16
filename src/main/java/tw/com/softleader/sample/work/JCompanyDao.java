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
import java.util.stream.Collectors;

/**
 * Created by Jerry.lin on 2017/7/9.
 */
public class JCompanyDao implements GenericDao<JCompany>{


    private JPersonDao jPersonDao = new JPersonDao();

    public JCompanyDao() {
            this.dataSource = DataSourceUtil
                    .getInstance()
                    .getDataSource();

    }

    private  DataSource dataSource;

    @Override
    public JCompany findOne(Long id) {
        JCompany entity = null;
        String sqlCmd = "SELECT * FROM jcompany where id = " +id ;
        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sqlCmd);){




            while (rs.next()) {

                entity = new JCompany();
                entity.setId(rs.getLong("id"));
                entity.setName(rs.getString("NAME"));
                entity.setTel(rs.getString("tel"));
                List<JPerson> jPeople = jPersonDao.findByJCompanyId(rs.getLong("id"));
                entity.setjPersonList(jPeople);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Collection<JCompany> findAll() {
        Collection<JCompany> JPersons = new ArrayList<>();
        Collection<JPerson> works = new ArrayList<>();
        JCompany entity = null;
        try {

            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();

            String sqlCmd = "SELECT * FROM jcompany ;";

            ResultSet rs = stmt.executeQuery(sqlCmd);

            while (rs.next()) {

                entity = new JCompany();
                entity.setId(rs.getLong("id"));
                entity.setName(rs.getString("NAME"));
                entity.setTel(rs.getString("tel"));
                List<JPerson> jPeople = jPersonDao.findByJCompanyId(rs.getLong("id"));
                entity.setjPersonList(jPeople);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return JPersons;
    }

    @Override
    public void insert(JCompany entity) {
        try {

            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();


            String sqlCmd = "INSERT INTO jcompany(name,tel) VALUES ('" + entity.getName() + "', '" + entity.getTel() + "');";

            stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);

            ResultSet keySet = stmt.getGeneratedKeys();

            if(keySet.next()) {
                Long generatedId = keySet.getLong("ID");
                entity.setId(generatedId);
            }
            for (JPerson person: entity.getjPersonList()) {
                person.setjCompany(entity);
                jPersonDao.insert(person);
            }
            keySet.close();

            stmt.close();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(JCompany entity) {
        try {

            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();

            String sqlCmd = "UPDATE jcompany SET name = '" + entity.getName() + "', tel = '" + entity.getTel() + "' where id = '" + entity.getId() + "' ";
            stmt.executeUpdate(sqlCmd);
            final List<Long> dbJPersonIds = jPersonDao.findByJCompanyId(entity.getId()).stream().map(jPerson -> jPerson.getId()).collect(Collectors.toList());
            final Collection<Long> jPersonId = entity.getjPersonList().stream().map(jPerson -> jPerson.getId()).collect(Collectors.toList());
            for (Long id: dbJPersonIds) {
                if (!jPersonId.contains(id)) {
                    jPersonDao.delete(id);
                }
            }

            for (JPerson person: entity.getjPersonList()) {
                if (person.getId() == null) {
                    jPersonDao.insert(person);
                } else {
                    jPersonDao.update(person);
                }
            }



            stmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            final List<JPerson> works = jPersonDao.findByJCompanyId(id);
            works.forEach(person -> jPersonDao.delete(person.getId()));
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            String sqlCmd = "DELETE FROM jcompany where id = "+id;
            stmt.executeUpdate(sqlCmd);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
