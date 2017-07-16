package tw.com.softleader.sample.work;

import tw.com.softleader.sample.commons.GenericDao;

import java.util.Collection;

/**
 * Created by Jerry.lin on 2017/7/9.
 */
public class JCompanyService implements GenericDao<JCompany> {


    private JCompanyDao dao;

    public JCompanyService() {
        dao = new JCompanyDao();
    }

    @Override
    public JCompany findOne(Long id) {
        return dao.findOne(id);
    }

    @Override
    public Collection<JCompany> findAll() {
        return dao.findAll();
    }

    @Override
    public void insert(JCompany entity) {
        dao.insert(entity);
    }

    @Override
    public void update(JCompany entity) {
        dao.update(entity);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }
}
