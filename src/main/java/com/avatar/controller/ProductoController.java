package com.avatar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avatar.config.BadRequestException;
import com.avatar.entity.Producto;
import com.avatar.service.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {
	@Autowired
	private ProductoService productoService;
	
	@GetMapping
	public ResponseEntity<List<Producto>> lista(){
		return ResponseEntity.ok(productoService.listProducto());
	}
	
	@PostMapping
	public ResponseEntity<String> inserta(@RequestBody Producto obj){
		if(productoService.findByPartNumber(obj.getPartNumber()).isPresent()) {
			throw new BadRequestException("HTTP CODE 500\n{\"errorMessage\": \"El Partnumber "+obj.getPartNumber()+" ya está registrado\"}");
		}
		else if(obj.getTipoProducto().equals("Paquete")&&obj.getCantidadComponentes()==0) {
			throw new BadRequestException("HTTP CODE 400\n {\"errorMessage\": \"El Partnumber "+obj.getPartNumber()+
					" es tipo Paquete, la cantidad de componentes no puede ser 0\"}");
		}
		else if(obj.getTipoProducto().equals("Normal")&&obj.getCantidadComponentes()>0) {
			throw new BadRequestException("HTTP CODE 400\n {\"errorMessage\": \"El Partnumber "+obj.getPartNumber()+
					" es tipo Normal, la cantidad de componentes no puede ser mayor a 0\"}");
		}else {
			//return ResponseEntity.ok(productoService.insertaProducto(obj));
			productoService.insertaProducto(obj);
			return ResponseEntity.ok("HTTP CODE 201");	
		}	
		
	}

	@GetMapping("/{partNumber}")	
	public ResponseEntity<Optional<Producto>> buscaProductoPorNumber(@PathVariable String partNumber){
		Optional<Producto> product=productoService.buscaProductoByPartNumber(partNumber);
		return ResponseEntity.ok(product);
	}
}
