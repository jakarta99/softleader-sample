package tw.com.triplei.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.triplei.portal.dao.QuestionDao;
import tw.com.triplei.portal.entity.Question;

@Service
public class QuestionService {

	@Autowired
	private QuestionDao questionDao;
	
	public Question getOne(Long id){
		return questionDao.findOne(id);
	}
	
	
	public List<Question> getAll(){
		return questionDao.findAll();
	}
	
	public Question insert(Question question){
		return questionDao.save(question);
		
	}
	
	public Question update(Question question){
		return questionDao.save(question);
		
	}
	
	public boolean delete(Long id){
		questionDao.delete(id);
		if(questionDao.findOne(id)!=null){
			return false;
		}
		return true;
	}
	
}
