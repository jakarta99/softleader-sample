package tw.com.triplei.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.triplei.portal.dao.UserDao;
import tw.com.triplei.portal.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public List<User>getAll(){
		return userDao.findAll();
	}
	
	public User getOne(Long id){
		return userDao.findOne(id);
	}
	
	public User insert(User user){
		return userDao.save(user);
	}
	
	public User update(User user){
		return userDao.save(user);
	}
	
	public boolean delete(Long id){
		if(userDao.findOne(id)!=null){
		userDao.delete(id);
		return true;
		}
		return false;
	}
}
