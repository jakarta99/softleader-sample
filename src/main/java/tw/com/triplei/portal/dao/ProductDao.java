package tw.com.triplei.portal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.triplei.portal.entity.Product;

public interface ProductDao extends JpaRepository<Product, Long> {
}