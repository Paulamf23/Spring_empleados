package com.gestion.paula.controller;

import com.gestion.paula.model.Empleados;
import com.gestion.paula.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador para la gestión de empleados
 */

@Controller
public class ControllerGestion {

	@Autowired
	private EmpleadoService servicioEmpleado;

	 /**
     * Muestra la página principal
     *
     * @return La vista del menú inicial.
     */

	@GetMapping({ "/", "/principal" })
	public String mostrarPrincipal() {
		return "principal";
	}
	
	 /**
     * Muestra la lista de empleados.
     *
     * @param modelo El modelo utilizado para pasar datos a la vista.
     * @return La vista de la lista de empleados.
     */

	@GetMapping({ "/empleados/listar" })
	public String listarEmpleados(Model modelo) {
		modelo.addAttribute("listarEmpleados", servicioEmpleado.listarEmpleados());
		return "listarEmpleados";
	}
	
	 /**
     * Muestra formulario crear empleado.
     *
     * @param modelo El modelo utilizado para pasar datos a la vista.
     * @return La vista del formulario de creación de empleados.
     */

	@GetMapping({ "/empleados/crear" })
	public String mostrarCrear(Model modelo) {
	    Empleados empleado = new Empleados();
	    modelo.addAttribute("empleado", empleado);
	    return "crearEmpleado";
	}
	
	/**
     * Guardar
     *
     * @param empleado El empleado a ser guardado.
     * @param modelo   El modelo utilizado para pasar datos a la vista.
     * @return La vista del formulario de creación de empleados o la vista del menú inicial en caso de éxito.
     */
	
	@PostMapping({ "/empleados" })
	public String guardar(@ModelAttribute("empleado") Empleados empleado, Model modelo) {
	    // Si el empleado ya existe:
	    if (servicioEmpleado.existeEmpleadoConDni(empleado.getDni())) {
	        modelo.addAttribute("error", "El empleado con DNI " + empleado.getDni() + " ya existe en la base de datos");
	        return "crearEmpleado";
	    }

	    // Si no existe:
	    servicioEmpleado.guardar(empleado);
	    // Agregar mensaje de éxito al modelo
	    modelo.addAttribute("exito", "El empleado ha sido dado de alta exitosamente");
	    //return "redirect:/menuInicial";
	    return "redirect:/empleados/listar";
	}
	
	/**
     * Muestra formulario editar
     *
     * @param dni     El DNI del empleado a ser editado.
     * @param modelo  El modelo utilizado para pasar datos a la vista.
     * @return La vista del formulario de edición de empleados o la vista del menú inicial si el empleado no existe.
     */
	
	@GetMapping({ "/empleados/editar/{dni}" })
	public String mostrarEditar(@PathVariable String dni, Model modelo) {
	    boolean empleadoExistente = servicioEmpleado.existeEmpleadoConDni(dni);
	    if (empleadoExistente) {
	        Empleados empleado = servicioEmpleado.obtenerEmpleadoConDni(dni);
	        modelo.addAttribute("empleado", empleado);
	        return "modificar";
	    } else {
	        modelo.addAttribute("error", "El empleado con DNI " + dni + " no existe.");
	        return "redirect:/principal";
	    }
	}
	
	   /**
     * Actualizar
     *
     * @param dni                 El DNI del empleado a ser actualizado.
     * @param empleado           El empleado con la información actualizada.
     * @param modelo             El modelo utilizado para pasar datos a la vista.
     * @param redirectAttributes Atributos de redirección para mensajes flash.
     * @return La redirección a la lista de empleados.
     */
	
	@PostMapping("/empleados/{dni}")
	public String actualizarEmpleado(@PathVariable String dni, @ModelAttribute("empleado") Empleados empleado,
	        Model modelo, RedirectAttributes redirectAttributes) {
	    boolean empleadoExistente = servicioEmpleado.existeEmpleadoConDni(dni);

	    if (empleadoExistente) {
	        // Actualizar
	        servicioEmpleado.actualizarEmpleado(empleado);
	        redirectAttributes.addFlashAttribute("exito", "El empleado con DNI " + dni + " ha sido editado exitosamente.");
	    } else {
	        // Mensaje de error
	        redirectAttributes.addFlashAttribute("error", "El empleado con DNI " + dni + " no existe.");
	        return "redirect:/empleados/listar";
	    }

	    return "redirect:/empleados/listar";
	}
	
	 /**
     * Eliminar
     *
     * @param dni                 El DNI del empleado a ser eliminado.
     * @param redirectAttributes Atributos de redirección para mensajes flash.
     * @return La redirección a la lista de empleados.
     */
	
	@GetMapping("/empleados/{dni}")
	public String eliminarEmpleado(@PathVariable String dni, RedirectAttributes redirectAttributes) {
		servicioEmpleado.eliminarEmpleado(dni);
		redirectAttributes.addFlashAttribute("exito", "El empleado con DNI " + dni + " ha sido eliminado exitosamente.");
		return "redirect:/empleados/listar";

	}
	
	/**
     * Buscar salario.
     *
     * @return La vista del formulario de búsqueda de salarios.
     */

	@GetMapping("/buscar/salarios")
	public String mostrarBuscarSalario() {
		return "buscarSalarios";
	}
	
	/**
     * Muestra salario de un empleado.
     *
     * @param dni    El DNI del empleado para buscar el salario.
     * @param modelo El modelo utilizado para pasar datos a la vista.
     * @return La vista del formulario de búsqueda de salarios con los resultados.
     */

	@PostMapping("/buscar/salarios")
	public String buscarSalario(@RequestParam String dni, Model modelo) {
		Empleados empleado = servicioEmpleado.obtenerEmpleadoConDni(dni);

		if (empleado != null) {
			double sueldo = servicioEmpleado.calcularSueldo(empleado);

			if (sueldo > 0) {
				modelo.addAttribute("empleado", empleado);
				modelo.addAttribute("sueldo", sueldo);
			} else {
				modelo.addAttribute("error", "No se pudo calcular el sueldo para el empleado con DNI " + dni);
			}
		} else {
			modelo.addAttribute("error", "No se encontró ningún empleado con DNI " + dni);
		}

		return "buscarSalarios";
	}

}
