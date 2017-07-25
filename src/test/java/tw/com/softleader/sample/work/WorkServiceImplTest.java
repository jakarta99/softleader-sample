package tw.com.softleader.sample.work;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.com.softleader.sample.drink.DrinkServiceTest;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jerry.lin on 2017/7/9.
 */
public class WorkServiceImplTest {

    private Logger log = LoggerFactory.getLogger(WorkServiceImplTest.class);

    private WorkServiceImpl service = new WorkServiceImpl();

    @Test
    public void crud () {
        Collection<Work> works = service.getAll();
        int originalSize = works.size();

        final Work work1 = new Work();
        work1.setName("first work");

        service.insert(work1);
        Long generatedId = work1.getId();

        work1.setName("sec work");

        service.update(work1);

        final Work dbEntity = service.getOne(generatedId);

        assertEquals("sec work", dbEntity.getName());

        service.delete(generatedId);

        works = service.getAll();

        int finalSize = works.size();

        assertEquals(originalSize, finalSize);
    }

}
