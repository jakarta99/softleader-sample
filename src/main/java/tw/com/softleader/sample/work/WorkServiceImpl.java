package tw.com.softleader.sample.work;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * Created by Jerry.lin on 2017/7/9.
 */
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkDao workDao;

    public WorkServiceImpl (){}


//    public void setWorkDao(WorkDao workDao) {
//        this.workDao = workDao;
//    }



    @Override
    public Work getOne(Long id) {
        return workDao.findOne(id);
    }

    @Override
    public Collection<Work> getAll() {
        return workDao.findAll();
    }

    @Override
    public void insert(Work entity) {
        workDao.insert(entity);
    }

    @Override
    public void update(Work entity) {
        workDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        workDao.delete(id);
    }
}
