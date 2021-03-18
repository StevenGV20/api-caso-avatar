package com.avatar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.avatar.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
	@Query(value="{call sp_findByPartNumber(:partNumber)}", nativeQuery = true)
	public Optional<Producto> buscaPorPartNumber(@Param("partNumber") String partNumber);
	
}
