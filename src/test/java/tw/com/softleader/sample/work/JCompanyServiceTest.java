//package tw.com.softleader.sample.work;
//
//import org.apache.log4j.Logger;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Created by faye on 2017/7/16.
// */
//public class JCompanyServiceTest {
//
//
//    private Logger log = Logger.getLogger(this.getClass());
//    @Test
//
//    public void test () {
//        JPersonService jPersonService = new JPersonService();
//        JCompanyService service = new JCompanyService();
//        WorkServiceImpl workServiceImpl = new WorkServiceImpl();
//
//        List<JPerson> jPersonList = new ArrayList<>();
//        List<Work> workList = new ArrayList<>();
//        int originWorkSize = workServiceImpl.getAll().size();
//        int originPersonSize = jPersonService.findAll().size();
//        int originCompanySize = service.findAll().size();
//        log.info(String.format("size:%d,%d,%d", originWorkSize, originPersonSize ,originCompanySize));
//
//        Work work = new Work();
//        work.setName("company work");
//
//        JPerson person = new JPerson();
//        person.setIdno(Long.valueOf("99"));
//        person.setName("company person");
//        work.setjPerson(person);
//        workList.add(work);
//        person.setWorks(workList);
//
//
//        JCompany jCompany = new JCompany();
//        jCompany.setTel("123456789");
//        jCompany.setName("new company");
//
//        person.setjCompany(jCompany);
//        jPersonList.add(person);
//
//        jCompany.setjPersonList(jPersonList);
//        service.insert(jCompany);
//        Long generatedId = jCompany.getId();
//
//        JCompany dbCompany = service.findOne(generatedId);
//        log.info(String.format("insert company=%s",dbCompany));
//        assertEquals(1,dbCompany.getjPersonList().size());
//        assertEquals(1,dbCompany.getjPersonList().get(0).getWorks().size());
//        assertEquals("new company", dbCompany.getName());
//
//
//
//        person = dbCompany.getjPersonList().get(0);
//        work = person.getWorks().get(0);
//        work.setName("update work name");
//
//        workList.clear();
//        workList.add(work);
//
//
//        person.setName("update perosn name");
//        person.setWorks(workList);
//        jPersonList.clear();
//        jPersonList.add(person);
//
//        dbCompany.setjPersonList(jPersonList);
//
//        dbCompany.setName("update company name");
//
//        service.update(dbCompany);
//
//        dbCompany = service.findOne(generatedId);
//
//        person = dbCompany.getjPersonList().get(0);
//        work = person.getWorks().get(0);
//
//        log.info(String.format("update company=%s",dbCompany));
//
//        assertEquals("update perosn name",person.getName());
//        assertEquals("update work name",work.getName());
//        assertEquals("update company name", dbCompany.getName());
//
//        service.delete(generatedId);
//
//        int workSize = workServiceImpl.getAll().size();
//        int personSize = jPersonService.findAll().size();
//        int companySize = service.findAll().size();
//
//        log.info(String.format("work:%d,%d",workSize,originWorkSize));
//        assertEquals(workSize,originWorkSize);
//        log.info(String.format("person:%d,%d",personSize,originPersonSize));
//
//        assertEquals(personSize,originPersonSize);
//        log.info(String.format("company:%d,%d",companySize,originCompanySize));
//        assertEquals(companySize,originCompanySize);
//
//    }
//}
