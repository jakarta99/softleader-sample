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


    public JPersonDao() {
            this.dataSource = DataSourceUtil
                    .getInstance()
                    .getDataSource();

    }

    private  DataSource dataSource;

    @Override
    public JPerson findOne(Long id) {
        JPerson person = null;
        try {


            String sqlCmd = "SELECT * FROM jperson where id = " +id ;
            Connection connection3 = dataSource.getConnection();
            Statement stmt3 = connection3.createStatement();
            ResultSet rs3 = stmt3.executeQuery(sqlCmd);
            while (rs3.next()) {

                person = new JPerson();
                person.setId(rs3.getLong("id"));
                person.setName(rs3.getString("NAME"));
                person.setIdno(rs3.getLong("idno"));

                ArrayList<Work> works = new ArrayList<>();
                Connection connection2 = dataSource.getConnection();
                Statement stmt2 = connection2.createStatement();
                String sqlCmd2 = "SELECT * FROM work where jpersonid =" + id;
                ResultSet rs2 = stmt2.executeQuery(sqlCmd2);

                while(rs2.next()) {

                    Work work = new Work();
                    work.setId(rs2.getLong("ID"));
                    work.setName(rs2.getString("NAME"));
                    works.add(work);

                }
                rs2.close();
                stmt2.close();
                connection2.close();
                person.setWorks(works);
            }
            rs3.close();
            stmt3.close();
            rs3.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return person;
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
                entity.setWorks(findByJPersonId(rs.getLong("id")));
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
    public void insert(JPerson person) {
        try {

            Connection connection2 = dataSource.getConnection();
            Statement stmt2 = connection2.createStatement();


            String sqlCmd2 = "INSERT INTO jperson(name,idno,jcompanyid) VALUES ('" + person.getName() + "', '" + person.getIdno() + "', '" + person.getjCompany().getId() + "');";

            stmt2.execute(sqlCmd2, Statement.RETURN_GENERATED_KEYS);

            ResultSet keySet2 = stmt2.getGeneratedKeys();

            if(keySet2.next()) {
                Long generatedId2 = keySet2.getLong("ID");
                person.setId(generatedId2);
            }
            if (person.getWorks() != null) {

                for (Work work: person.getWorks()) {
                    work.setjPerson(person);
                    Connection connection3 = dataSource.getConnection();
                    Statement stmt3 = connection3.createStatement();
                    String personId = work.getjPerson() == null ? 0+"" : work.getjPerson().getId().toString() ;
                    String sqlCmd3 = "INSERT INTO work (name,jpersonid) VALUES ('"+work.getName()+ "', '"+ personId   +"');";

                    stmt3.execute(sqlCmd3, Statement.RETURN_GENERATED_KEYS);
                    ResultSet keySet3 = stmt3.getGeneratedKeys();
                    if(keySet3.next()) {
                        Long generatedId3 = keySet3.getLong("ID");
                        work.setId(generatedId3);
                    }

                    keySet3.close();
                    stmt3.close();
                    connection3.close();
                }
            }
            keySet2.close();

            stmt2.close();

            connection2.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(JPerson person) {
        try {

            Connection connection2 = dataSource.getConnection();
            Statement stmt2 = connection2.createStatement();

            String sqlCmd2 = "UPDATE jperson SET idno = '" + person.getIdno() + "', name = '" + person.getName() + "' where id = '" + person.getId() + "' ";
            stmt2.executeUpdate(sqlCmd2);
            final List<Long> dbWorkIds = findByJPersonId(person.getId()).stream().map(work -> work.getId()).collect(Collectors.toList());
            final Collection<Long> works = person.getWorks().stream().map(work -> work.getId()).collect(Collectors.toList());

            for (Long id: dbWorkIds) {
                if (!works.contains(id)) {
                    Connection connection3 = dataSource.getConnection();
                    Statement stmt3 = connection3.createStatement();

                    String sqlCmd3 = "DELETE FROM work where ID = "+id;
                    stmt3.executeUpdate(sqlCmd3);
                    stmt3.close();
                    connection3.close();
                }
            }

            for (Work work: person.getWorks()) {
                if (work.getId() == null) {
                    Connection connection3 = dataSource.getConnection();
                    Statement stmt3 = connection3.createStatement();

                    String personId = work.getjPerson() == null ? 0+"" : work.getjPerson().getId().toString() ;
                    System.out.println();

                    String sqlCmd3 = "INSERT INTO work (name,jpersonid) VALUES ('"+work.getName()+ "', '"+ personId   +"');";

                    stmt3.execute(sqlCmd3, Statement.RETURN_GENERATED_KEYS);
                    ResultSet keySet3 = stmt3.getGeneratedKeys();
                    if(keySet3.next()) {
                        Long generatedId = keySet3.getLong("ID");
                        work.setId(generatedId);
                    }

                    keySet3.close();
                    stmt3.close();
                    connection3.close();
                } else {
                    Connection connection3 = dataSource.getConnection();
                    Statement stmt3 = connection3.createStatement();
                    String personId = work.getjPerson() == null ? 0+"" : work.getjPerson().getId().toString() ;

                    String sqlCmd3 = "UPDATE work SET  name = '" + work.getName() + "', jpersonid = '" + personId + "' WHERE ID = " + work.getId();
                    stmt3.executeUpdate(sqlCmd3);
                    stmt3.close();
                    connection3.close();
                }
            }



            stmt2.close();
            connection2.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            final List<Work> works = findByJPersonId(id);
            works.forEach(work -> {

                try {
                    Connection connection2 = dataSource.getConnection();
                    Statement stmt2 = connection2.createStatement();
                    String sqlCmd2 = "DELETE FROM work where ID = " + work.getId();
                    stmt2.executeUpdate(sqlCmd2);
                    stmt2.close();
                    connection2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
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
    private List<Work> findByJPersonId(Long id){
        ArrayList<Work> works= new ArrayList<>();;
        try {
            Work entity = null;
            Connection connection2 = dataSource.getConnection();
            Statement stmt2 = connection2.createStatement();
            String sqlCmd2 = "SELECT * FROM work where jpersonid =" + id;

            ResultSet rs2 = stmt2.executeQuery(sqlCmd2);

            while(rs2.next()) {

                Work work = new Work();
                work.setId(rs2.getLong("ID"));
                work.setName(rs2.getString("NAME"));
                works.add(work);

            }
            rs2.close();
            stmt2.close();
            connection2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return works;
    }






}
