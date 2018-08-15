package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.ValidacionDetalle;

public interface IValidacionDetalleService {
	public List<ValidacionDetalle> findByDetalleEstado(Boolean estado, Integer id);
}
