package tw.com.softleader.sample.notebook;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Frank on 2017/7/18.
 */
public class WCompanyServiceTest {

    private Logger log = LoggerFactory.getLogger(WCompanyServiceTest.class);

    private WCompanyService companyService = new WCompanyService();

    @Test
    public void test() {
        Collection<WCompany> companies = companyService.getAll();
        final int originalSize = companies.size();
        log.info("originalSize = " + originalSize);
        WCompany entity =  new WCompany();
        entity.setIdno("80230237");
        entity.setName("SoftLeader");
        entity.setPersonList(new ArrayList<>());

        WPerson person1 = new WPerson();
        person1.setIdno("A123456789");
        person1.setName("Frank");
        person1.setNotebooks(new ArrayList<>());
        entity.getPersonList().add(person1);

        Notebook nb1 = new Notebook();
        nb1.setBrand("HP");
        nb1.setSpecification("Intel i5");
        nb1.setQuotation(new BigDecimal("30000"));
        person1.getNotebooks().add(nb1);
        Notebook nb2 = new Notebook();
        nb2.setBrand("APPLE");
        nb2.setSpecification("MAC");
        nb2.setQuotation(new BigDecimal("43000"));
        person1.getNotebooks().add(nb2);

        WPerson person2 = new WPerson();
        person2.setIdno("B123456780");
        person2.setName("ABC");
        person2.setNotebooks(new ArrayList<>());
        entity.getPersonList().add(person2);

        companyService.insert(entity);
        WCompany dbEntity = companyService.getOne(entity.getId());
        assertNotNull(dbEntity);
        assertEquals(1L, dbEntity.getPersonList().stream().filter(psn -> "A123456789".equals(psn.getIdno())).count());

        person1.setIdno("C123456781");
        Notebook nb3 = new Notebook();
        nb3.setBrand("ACER");
        nb3.setSpecification("Intel i7");
        nb3.setQuotation(new BigDecimal("45000"));
        person1.getNotebooks().add(nb3);
        person1.getNotebooks().remove(nb1);

        nb2.setQuotation(new BigDecimal("41500"));

        companyService.update(entity);

        dbEntity = companyService.getOne(entity.getId());
        assertEquals(2L, dbEntity.getPersonList().stream()
                .filter(psn -> "C123456781".equals(psn.getIdno()))
                .findFirst()
                .get()
                .getNotebooks()
                .stream()
                .count());

        assertEquals(1L, dbEntity.getPersonList().stream()
                .filter(psn -> "C123456781".equals(psn.getIdno()))
                .findFirst()
                .get()
                .getNotebooks()
                .stream()
                .filter(notebook -> "ACER".equals(notebook.getBrand()))
                .count());

        companyService.delete(entity.getId());

        companies = companyService.getAll();
        final int finalSize = companies.size();
        assertEquals(originalSize, finalSize);

    }

    @Test
    public void testFindOne() {
        WCompany entity = companyService.getOne(1L);
        for(WPerson person : entity.getPersonList()) {
            log.info(person.getIdno());
        }
    }

    @Test
    public void testUpdate() {
        WCompany entity = companyService.getOne(1L);
        for(WPerson person : entity.getPersonList()) {
            log.info(person.getIdno());
        }
        companyService.update(entity);

    }

    public static void main(String[] args) {
        Collection<Notebook> notebooks = new ArrayList<>();
        Notebook nb1 = new Notebook();
        nb1.setBrand("1");
        Notebook nb2 = new Notebook();
        nb2.setBrand("2");
        notebooks.add(nb1);
        notebooks.add(nb2);
        notebooks.forEach(notebook -> System.out.println(notebook.getBrand()));
        notebooks.remove(nb1);
        notebooks.forEach(notebook -> System.out.println(notebook.getBrand()));

    }

}