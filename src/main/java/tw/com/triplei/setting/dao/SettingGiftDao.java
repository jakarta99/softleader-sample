package tw.com.triplei.setting.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.triplei.protal.entity.Gift;

public interface SettingGiftDao extends JpaRepository<Gift, Long> {

}
