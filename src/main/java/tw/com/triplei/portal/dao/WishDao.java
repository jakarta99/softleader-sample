package tw.com.triplei.portal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.triplei.portal.entity.Wish;

public interface WishDao extends JpaRepository<Wish,Long> {

}
