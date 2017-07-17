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

                Connection connection2 = dataSource.getConnection();
                Statement stmt2 = connection2.createStatement();


                String sqlCmd2 = "SELECT * FROM jperson where jcompanyid =" + id;

                ResultSet rs2 = stmt2.executeQuery(sqlCmd2);
                List<JPerson> jPeople = new ArrayList<>();

                while(rs2.next()) {

                    JPerson jPerson = new JPerson();
                    jPerson.setId(rs2.getLong("ID"));
                    jPerson.setName(rs2.getString("NAME"));
                    jPerson.setIdno(rs2.getLong("IDNO"));
                    Connection connection3 = dataSource.getConnection();
                    Statement stmt3 = connection3.createStatement();


                    String sqlCmd3 = "SELECT * FROM work where jpersonid =" + jPerson.getId();

                    ResultSet rs3 = stmt3.executeQuery(sqlCmd3);
                    ArrayList<Work> works = new ArrayList<>();
                    while(rs3.next()) {
                        Work work = new Work();
                        work.setId(rs3.getLong("ID"));
                        work.setName(rs3.getString("NAME"));
                        work.setjPerson(jPerson);
                        works.add(work);
                    }

                    rs3.close();
                    stmt3.close();
                    connection3.close();
                    jPerson.setjCompany(entity);
                    jPerson.setWorks(works);
                    jPeople.add(jPerson);

                }

                rs2.close();
                stmt2.close();
                connection2.close();

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
                List<JPerson> jPeople = findByJCompanyId(rs.getLong("id"));
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
            }
            keySet.close();

            stmt.close();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(JCompany conpany) {
        try {

            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();

            String sqlCmd = "UPDATE jcompany SET name = '" + conpany.getName() + "', tel = '" + conpany.getTel() + "' where id = '" + conpany.getId() + "' ";
            stmt.executeUpdate(sqlCmd);
            final List<Long> dbJPersonIds = findByJCompanyId(conpany.getId()).stream().map(jPerson -> jPerson.getId()).collect(Collectors.toList());
            final Collection<Long> jPersonId = conpany.getjPersonList().stream().map(jPerson -> jPerson.getId()).collect(Collectors.toList());
            for (Long id: dbJPersonIds) {
                if (!jPersonId.contains(id)) {
                    deleteJPerson(id);
                }
            }

            for (JPerson person: conpany.getjPersonList()) {
                if (person.getId() == null) {
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
                } else {
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
            final List<JPerson> works = findByJCompanyId(id);
            works.forEach(person -> deleteJPerson(person.getId()));
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


    public List<JPerson> findByJCompanyId(Long id){
        JPerson entity = null;
        ArrayList<JPerson> jPeople = new ArrayList<>();
        try {
            Connection connection2 = dataSource.getConnection();
            Statement stmt2 = connection2.createStatement();


            String sqlCmd2 = "SELECT * FROM jperson where jcompanyid =" + id;

            ResultSet rs2 = stmt2.executeQuery(sqlCmd2);

            while(rs2.next()) {

                JPerson jPerson = new JPerson();
                jPerson.setId(rs2.getLong("ID"));
                jPerson.setName(rs2.getString("NAME"));
                jPerson.setIdno(rs2.getLong("IDNO"));
                Connection connection3 = dataSource.getConnection();
                Statement stmt3 = connection3.createStatement();


                String sqlCmd3 = "SELECT * FROM work where jpersonid =" + jPerson.getId();

                ResultSet rs3 = stmt3.executeQuery(sqlCmd3);
                ArrayList<Work> works = new ArrayList<>();
                while(rs3.next()) {
                    Work work = new Work();
                    work.setId(rs3.getLong("ID"));
                    work.setName(rs3.getString("NAME"));
                    work.setjPerson(jPerson);
                    works.add(work);
                }

                rs3.close();
                stmt3.close();
                connection3.close();

                jPerson.setWorks(works);
                jPeople.add(jPerson);

            }

            rs2.close();
            stmt2.close();
            connection2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jPeople;
    }

    private List<Work> findByJPersonId(Long id){
        ArrayList<Work> works = new ArrayList<>();
        try {
            Connection connection3 = dataSource.getConnection();
            Statement stmt3 = connection3.createStatement();


            String sqlCmd3 = "SELECT * FROM work where jpersonid =" + id;

            ResultSet rs3 = stmt3.executeQuery(sqlCmd3);

            while(rs3.next()) {

                Work work = new Work();
                work.setId(rs3.getLong("ID"));
                work.setName(rs3.getString("NAME"));
                works.add(work);

            }
            rs3.close();
            stmt3.close();
            connection3.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return works;
    }


    public void deleteJPerson(Long id) {
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
}
