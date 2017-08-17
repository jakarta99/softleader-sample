package tw.com.triplei.portal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.triplei.portal.entity.User;

public interface UserDao  extends JpaRepository<User,Long>{

}
