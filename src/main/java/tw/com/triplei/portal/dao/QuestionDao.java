package tw.com.triplei.portal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.triplei.portal.entity.Question;

public interface QuestionDao extends JpaRepository<Question,Long> {

}
