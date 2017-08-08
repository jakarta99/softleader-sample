package tw.com.softleader.sample.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.softleader.sample.commons.GenericService;

import java.util.Collection;

/**
 * Created by Jerry.lin on 2017/7/9.
 */
@Service
public class WorkService  implements GenericService<Work> {


    @Autowired
    private WorkDao workDao;


    @Override
    public Work getOne(Long id) {
        return workDao.getOne(id);
    }

    @Override
    public Collection<Work> getAll() {
        return workDao.findAll();
    }

    @Override
    public void insert(Work entity) {
        workDao.save(entity);
    }

    @Override
    public void update(Work entity) {
        workDao.save(entity);
    }

    @Override
    public void delete(Long id) {
        workDao.delete(id);
    }
}
