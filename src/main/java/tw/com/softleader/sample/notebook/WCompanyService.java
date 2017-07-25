package tw.com.softleader.sample.notebook;

import tw.com.softleader.sample.commons.GenericService;

import java.util.Collection;

/**
 * Created by Frank on 2017/7/18.
 */
public class WCompanyService implements GenericService<WCompany> {

    private WCompanyDao dao = null;

    private WCompanyDao getDao() {
        if(dao == null) {
            return new WCompanyDao();
        }
        return dao;
    }

    @Override
    public WCompany getOne(Long id) {
        return getDao().findOne(id);
    }

    @Override
    public Collection<WCompany> getAll() {
        return getDao().findAll();
    }

    @Override
    public void insert(WCompany entity) {
        getDao().insert(entity);
    }

    @Override
    public void update(WCompany entity) {
        getDao().update(entity);
    }

    @Override
    public void delete(Long id) {
        getDao().delete(id);
    }
}
