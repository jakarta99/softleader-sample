package tw.com.triplei.portal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.triplei.portal.entity.Gift;

public interface GiftDao extends JpaRepository<Gift, Long> {

}
