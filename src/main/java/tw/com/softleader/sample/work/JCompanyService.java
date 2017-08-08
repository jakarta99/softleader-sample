package tw.com.softleader.sample.work;

import org.springframework.beans.factory.annotation.Autowired;
import tw.com.softleader.sample.commons.GenericDao;
import tw.com.softleader.sample.commons.GenericService;

import java.util.Collection;

/**
 * Created by Jerry.lin on 2017/7/9.
 */
public class JCompanyService implements GenericService<JCompany> {

    @Autowired
    private JCompanyDao dao;

    @Override
    public JCompany getOne(Long id) {
        return dao.getOne(id);
    }

    @Override
    public Collection<JCompany> getAll() {
        return dao.findAll();
    }

    @Override
    public void insert(JCompany entity) {
        dao.save(entity);
    }

    @Override
    public void update(JCompany entity) {
        dao.save(entity);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }
}
