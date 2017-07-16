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
public class JPersonDao implements GenericDao<JPerson>{


    private WorkDao workDao = new WorkDao();

    public JPersonDao() {
            this.dataSource = DataSourceUtil
                    .getInstance()
                    .getDataSource();

    }

    private  DataSource dataSource;

    @Override
    public JPerson findOne(Long id) {
        JPerson entity = null;
        String sqlCmd = "SELECT * FROM jperson where id = " +id ;
        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sqlCmd);){




            while (rs.next()) {

                entity = new JPerson();
                entity.setId(rs.getLong("id"));
                entity.setName(rs.getString("NAME"));
                entity.setIdno(rs.getLong("idno"));
                List<Work> works = workDao.findByJPersonId(rs.getLong("id"));
                entity.setWorks(works);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Collection<JPerson> findAll() {
        Collection<JPerson> JPersons = new ArrayList<>();
        Collection<Work> works = new ArrayList<>();
        JPerson entity = null;
        try {

            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();

            String sqlCmd = "SELECT * FROM jperson ;";

            ResultSet rs = stmt.executeQuery(sqlCmd);

            while (rs.next()) {

                entity = new JPerson();

                entity.setId(rs.getLong("id"));
                entity.setName(rs.getString("NAME"));
                entity.setIdno(rs.getLong("idno"));
                entity.setWorks(workDao.findByJPersonId(rs.getLong("id")));
                JPersons.add(entity);
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
    public void insert(JPerson entity) {
        try {

            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();


            String sqlCmd = "INSERT INTO jperson(name,idno,jcompanyid) VALUES ('" + entity.getName() + "', '" + entity.getIdno() + "', '" + entity.getjCompany().getId() + "');";

            stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);

            ResultSet keySet = stmt.getGeneratedKeys();

            if(keySet.next()) {
                Long generatedId = keySet.getLong("ID");
                entity.setId(generatedId);
            }
            if (entity.getWorks() != null) {
                for (Work work: entity.getWorks()) {
                    work.setjPerson(entity);
                    workDao.insert(work);
                }
            }
            keySet.close();

            stmt.close();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(JPerson entity) {
        try {

            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();

            String sqlCmd = "UPDATE jperson SET idno = '" + entity.getIdno() + "', name = '" + entity.getName() + "' where id = '" + entity.getId() + "' ";
            stmt.executeUpdate(sqlCmd);
            final List<Long> dbWorkIds = workDao.findByJPersonId(entity.getId()).stream().map(work -> work.getId()).collect(Collectors.toList());
            final Collection<Long> works = entity.getWorks().stream().map(work -> work.getId()).collect(Collectors.toList());
            for (Long id: dbWorkIds) {
                if (!works.contains(id)) {
                    workDao.delete(id);
                }
            }

            for (Work work: entity.getWorks()) {
                if (work.getId() == null) {
                    workDao.insert(work);
                } else {
                    workDao.update(work);
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
            final List<Work> works = workDao.findByJPersonId(id);
            works.forEach(work -> workDao.delete(work.getId()));
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            String sqlCmd = "DELETE FROM jPerson where ID = "+id;
            stmt.executeUpdate(sqlCmd);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<JPerson> findByJCompanyId(Long id){
        JPerson entity = null;
        ArrayList<JPerson> jPeople = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();


            String sqlCmd = "SELECT * FROM jperson where jcompanyid =" + id;

            ResultSet rs = stmt.executeQuery(sqlCmd);

            while(rs.next()) {

                JPerson jPerson = new JPerson();
                jPerson.setId(rs.getLong("ID"));
                jPerson.setName(rs.getString("NAME"));
                jPerson.setIdno(rs.getLong("IDNO"));
                jPerson.setWorks(workDao.findByJPersonId(rs.getLong("ID")));
                jPeople.add(jPerson);

            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jPeople;
    }

}
