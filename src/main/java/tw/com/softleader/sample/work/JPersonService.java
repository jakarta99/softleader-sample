package tw.com.softleader.sample.work;

import tw.com.softleader.sample.commons.GenericDao;

import java.util.Collection;

/**
 * Created by Jerry.lin on 2017/7/9.
 */
public class JPersonService implements GenericDao<JPerson> {


    private JPersonDao dao;

    public JPersonService() {
        dao = new JPersonDao();
    }

    @Override
    public JPerson findOne(Long id) {
        return dao.findOne(id);
    }

    @Override
    public Collection<JPerson> findAll() {
        return dao.findAll();
    }

    @Override
    public void insert(JPerson entity) {
        dao.insert(entity);
    }

    @Override
    public void update(JPerson entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }
}
