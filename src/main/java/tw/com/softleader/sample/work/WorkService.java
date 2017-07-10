package tw.com.softleader.sample.work;

import tw.com.softleader.sample.commons.GenericDao;

import java.util.Collection;

/**
 * Created by Jerry.lin on 2017/7/9.
 */
public class WorkService implements GenericDao<Work> {


    private WorkDao dao;

    public WorkService () {
        dao = new WorkDao();
    }

    @Override
    public Work findOne(Long id) {
        return dao.findOne(id);
    }

    @Override
    public Collection<Work> findAll() {
        return dao.findAll();
    }

    @Override
    public void insert(Work entity) {
        dao.insert(entity);
    }

    @Override
    public void update(Work entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }
}
