package tw.com.triplei.protal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.triplei.protal.entity.Product;

public interface ProductDao extends JpaRepository<Product, Long> {
}