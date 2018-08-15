package com.bolsadeideas.springboot.app.models.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.models.dao.IValidacionDetalleDao;
import com.bolsadeideas.springboot.app.models.entity.ValidacionDetalle;
import com.bolsadeideas.springboot.app.models.service.IValidacionDetalleService;

@Service
public class ValidacionDetalleServiceImpl implements IValidacionDetalleService {

	@Autowired
	private IValidacionDetalleDao validacionDetalleDao;

	@Override
	public List<ValidacionDetalle> findByDetalleEstado(Boolean estado , Integer id ) {
		return validacionDetalleDao.findByEstadoAndElemento(estado , id);   
	}

}
