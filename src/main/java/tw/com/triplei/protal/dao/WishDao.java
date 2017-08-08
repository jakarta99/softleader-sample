package tw.com.triplei.protal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.triplei.protal.entity.Wish;

public interface WishDao extends JpaRepository<Wish,Long> {

}
