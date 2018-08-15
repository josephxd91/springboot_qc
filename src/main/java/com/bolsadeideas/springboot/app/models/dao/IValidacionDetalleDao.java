package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.ValidacionDetalle;


public interface IValidacionDetalleDao 
extends CrudRepository<ValidacionDetalle, Integer> 
{
	
	@Query("SELECT vd FROM ValidacionDetalle vd WHERE  vd.ultimoCambio=?1 AND "
			+ "vd.idvalidacion.idsubelemento.idsubelemento=?2")  
	public List<ValidacionDetalle> findByEstadoAndElemento(Boolean cambio , Integer id);  
	
    
}
  