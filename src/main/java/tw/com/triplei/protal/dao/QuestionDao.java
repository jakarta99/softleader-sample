package tw.com.triplei.protal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.triplei.protal.entity.Question;

public interface QuestionDao extends JpaRepository<Question,Long> {

}
