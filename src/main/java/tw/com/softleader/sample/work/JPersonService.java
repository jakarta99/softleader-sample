package tw.com.softleader.sample.work;

import org.springframework.beans.factory.annotation.Autowired;
import tw.com.softleader.sample.commons.GenericDao;
import tw.com.softleader.sample.commons.GenericService;

import java.util.Collection;

/**
 * Created by Jerry.lin on 2017/7/9.
 */
public class JPersonService implements GenericService<JPerson> {

    @Autowired
    private JPersonDao dao;


    @Override
    public JPerson getOne(Long id) {
        return dao.getOne(id);
    }

    @Override
    public Collection<JPerson> getAll() {
        return dao.findAll();
    }

    @Override
    public void insert(JPerson entity) {
        dao.save(entity);
    }

    @Override
    public void update(JPerson entity) {
        dao.save(entity);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }
}
