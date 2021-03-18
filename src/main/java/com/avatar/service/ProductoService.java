package com.avatar.service;

import java.util.List;
import java.util.Optional;

import com.avatar.entity.Producto;

public interface ProductoService {
	public abstract List<Producto> listProducto();
	public abstract Producto insertaProducto(Producto obj);
	public abstract Optional<Producto> findByPartNumber(String partNumber);
	public abstract Optional<Producto> buscaProductoByPartNumber(String partNumber);
}
