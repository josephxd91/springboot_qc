package com.bolsadeideas.springboot.app.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.SubElemento;
import com.bolsadeideas.springboot.app.models.entity.Usuario;
import com.bolsadeideas.springboot.app.models.entity.Validacion;
import com.bolsadeideas.springboot.app.models.entity.ValidacionDetalle;
import com.bolsadeideas.springboot.app.models.service.implement.ElementoServiceImpl;
import com.bolsadeideas.springboot.app.models.service.implement.SubElementoServiceImpl;
import com.bolsadeideas.springboot.app.models.service.implement.ValidacionDetalleServiceImpl;
import com.bolsadeideas.springboot.app.models.service.implement.ValidacionServiceImpl;

@Controller
public class IndexController {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private SubElementoServiceImpl subElementoService;

	@Autowired
	private ElementoServiceImpl elementoService;

	@Autowired
	private ValidacionDetalleServiceImpl validacionDetalleService;

	@Autowired
	private ValidacionServiceImpl validacionService;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = { "/content_sections" }, method = RequestMethod.GET)
	public String content_sections(Model model) {
		model.addAttribute("elementos", elementoService.findAll());
		return "/validacion/content_sections";
	}

	@RequestMapping(value = { "/content_sections/section/{level}/{id}" }, method = RequestMethod.GET)
	public String section(@PathVariable(value = "level") Integer level, @PathVariable(value = "id") Integer id,
			Model model) {

		SubElemento subElemento = subElementoService.findOne(id);
		List<ValidacionDetalle> validacionDetalle = validacionDetalleService.findByDetalleEstado(Boolean.TRUE,
				subElemento.getIdsubelemento());
		logger.info("VALIDACIONES # : " + validacionDetalle.size());

		model.addAttribute("subElemento", subElemento);
		model.addAttribute("detalleValidacion", validacionDetalle);

		return "/validacion/section";
	}

	@RequestMapping(value = "/content_sections/section_load/{id}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> load_section(@PathVariable(value = "id") Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Validacion validacion = validacionService.findOne(id);
		map.put("revision", validacion.getRevision());
		map.put("detalle", validacion.getDetalle());
		map.put("ayuda", validacion.getAyuda());
		return map;

	}

	@RequestMapping(value = "/content_sections/aplicar_cambios", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> aplicarCambios(Validacion validacion) {

		Map<String, Object> map = new HashMap<String, Object>();

		if (validacion == null) {
			return map;
		}
		Date fecha_actual = new Date();
		String revision = validacion.getRevision();
		String titulo = validacion.getRevision();
		String ayuda = validacion.getAyuda();
		String detalle = validacion.getDetalle();
		String descripcion = validacion.getDescripcion().replace("\n", " ").replace("\t", " ");

		if (validacion.getIdrevisionValidacion() != 0) {
			validacion = validacionService.findOne(validacion.getIdrevisionValidacion());
		} else {
			validacion.setFechaCreacion(fecha_actual);
			validacion.setFechaDesactivacion(fecha_actual);
			validacion.setEstado(Boolean.TRUE);
		}
		validacion.setRevision(revision);
		validacion.setTitulo(titulo);
		validacion.setDescripcion(descripcion);
		validacion.setDetalle(detalle);
		validacion.setAyuda(ayuda);

		if (!validacion.getValidacionDetalleList().isEmpty()) {
			for (int index = 0; index < validacion.getValidacionDetalleList().size(); index++) {
				ValidacionDetalle vd = validacion.getValidacionDetalleList().get(index);
				vd.setUltimoCambio(Boolean.FALSE);
				validacion.getValidacionDetalleList().set(index, vd);
			}

		}

		ValidacionDetalle validacionDetalle = new ValidacionDetalle();
		validacionDetalle.setFechaModificacion(fecha_actual);
		validacionDetalle.setFechaMoficacionDate(fecha_actual);
		validacionDetalle.setIdusuario(new Usuario(3));
		validacionDetalle.setUltimoCambio(Boolean.TRUE);
		validacionDetalle.setIdvalidacion(validacion);
		validacion.getValidacionDetalleList().add(validacionDetalle);
		validacionService.save(validacion);
		map.put("hola", "hola");

		return map;

	}

	@RequestMapping(value = { "/content_sections/listar_detalle/{id}" }, method = RequestMethod.GET)
	public String listar_detalle(@PathVariable(value = "id") Integer id, Model model) {

		List<ValidacionDetalle> validacionDetalle = validacionDetalleService.findByDetalleEstado(Boolean.TRUE, id);
		model.addAttribute("detalleValidacion", validacionDetalle);
		return "/validacion/section_detalle";

	}

	// /contents/image_uploads
	@RequestMapping(value = { "/contents/image_uploads" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> image_upload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		Map<String, Object> map = new HashMap<>();
		System.out.println("f1............." + file.getContentType());
		System.out.println("f2............." + file.getSize());
		System.out.println("f3............." + file.getOriginalFilename());

		// redirectAttributes.addFlashAttribute("message",
		// "You successfully uploaded " + file.getOriginalFilename() + "!");

		return map;

	}
}
