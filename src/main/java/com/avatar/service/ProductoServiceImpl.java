package com.avatar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.avatar.config.BadRequestException;
import com.avatar.entity.Producto;
import com.avatar.repository.ProductoRepository;


@Service
public class ProductoServiceImpl implements ProductoService {
	@Autowired
	private ProductoRepository repository;

	@Override
	public List<Producto> listProducto() {
		try {
			 List<Producto> lista=repository.findAll();
			if(lista.isEmpty()) {
				throw new BadRequestException("HTTP CODE 404");
			}else {				
				return lista;
			}
		} 
		catch(BadRequestException e) {
			throw new BadRequestException("HTTP CODE 404");
		}
		catch (Exception e) {
			throw new BadRequestException("HTTP CODE 500");
		}
	}

	@Override
	public Producto insertaProducto(Producto obj) {
		try {
			return repository.save(obj);	
		}
		catch (DataIntegrityViolationException e) {
			//Error para cuando solo se envien algunos datos
			throw new BadRequestException("HTTP CODE 400");
		}
		catch (Exception e) {
			//Mensaje para cualquier otro tipo de error
			throw new BadRequestException("HTTP CODE 500");
		}
	}

	@Override
	public Optional<Producto> buscaProductoByPartNumber(String partNumber) {
		try {
			if(repository.buscaPorPartNumber(partNumber).isPresent()==false) {
				throw new BadRequestException("HTTP CODE 404");
			}
			else {
				return repository.buscaPorPartNumber(partNumber);	
			}
		} 
		catch(BadRequestException e) {
			throw new BadRequestException("HTTP CODE 404");
		}
		catch (Exception e) {
			throw new BadRequestException("HTTP CODE 500");
		}
	}

	@Override
	public Optional<Producto> findByPartNumber(String partNumber) {
		return repository.buscaPorPartNumber(partNumber);	
	}
	
}
