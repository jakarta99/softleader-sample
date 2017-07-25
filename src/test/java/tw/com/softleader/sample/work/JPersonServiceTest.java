package tw.com.softleader.sample.work;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by JerryLin on 2017/7/9.
 */

public class JPersonServiceTest {
    private Logger log = Logger.getLogger(this.getClass());


    @Test
    public void testCRUD() {
        JPersonService service = new JPersonService();

        WorkServiceImpl workServiceImpl = new WorkServiceImpl();
        WorkDao workDao = new WorkDao();
        JPerson jperson0 = new JPerson();
        Work work = new Work();
        final int workSize = workDao.findAll().size();
        List<Work> works = new ArrayList<>();
        log.info(String.format("total work is :%d" , workDao.findAll().size()));
        jperson0.setName("s001");
        jperson0.setIdno(10L);

        work = new Work();
        work.setName("1");
        works.add(work);
        work = new Work();
        work.setName("2");

        works.add(work);

        jperson0.setWorks(works);

        service.insert(jperson0);
        log.info("insert jPerson");
        Long generatedId = jperson0.getId();

        //檢查新增
        JPerson jPerson = service.findOne(generatedId);
        works  = jperson0.getWorks();
        assertEquals("s001", jPerson.getName());
        assertEquals(2, works.size());
        log.info(String.format("works Size:%d", works.size()));
        log.info(String.format("work1 Nane:%s", works.get(0).getName()));
        log.info(String.format("work2 Nane:%s", works.get(1).getName()));

        final Work updateWork = works.get(0);
        works.clear();
        updateWork.setName("001work");
        works.add(updateWork);
        log.info(String.format("Total work is : %d", workDao.findAll().size()));
        log.info("update jPerson");
        service.update(jperson0);

        final JPerson dbJperson = service.findOne(generatedId);
        //檢查修改
        final Work dbwork = workDao.findOne(updateWork.getId());

        assertEquals("001work", dbwork.getName());
        log.info(String.format("dbworkName: %s", dbwork.getName()));
        assertEquals(1, dbJperson.getWorks().size());
        log.info(String.format("dbJperson works size:%d", dbJperson.getWorks().size()));
        log.info(String.format("Total work is : %d", workDao.findAll().size()));
        log.info("delete jPerson");
        service.delete(generatedId);

        //檢查是否有刪除
        jPerson = service.findOne(generatedId);
        final int beforeSize = workDao.findAll().size();
        assertEquals(workSize, beforeSize);
        log.info(String.format("size:%d, %d" ,workSize, beforeSize));
        assertNull(jPerson);
        if(jPerson == null) {
            log.info("jPerson Is Null");
        }

    }
}
