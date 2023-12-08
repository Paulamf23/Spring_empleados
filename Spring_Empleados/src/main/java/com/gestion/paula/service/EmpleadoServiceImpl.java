package com.gestion.paula.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gestion.paula.model.Empleados;
import com.gestion.paula.repository.EmpleadoRepo;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

/**
 * Implementación de la interfaz EmpleadoService
 */

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoRepo repositorioEmpleado;

	@Autowired
	private Validator validator;

	/**
	 * Obtiene una lista de todos los empleados en el sistema.
	 *
	 * @return Lista de empleados.
	 */

	@Override
	public List<Empleados> listarEmpleados() {
		return repositorioEmpleado.findAll();
	}

	/**
	 * Guarda un nuevo empleado en el sistema.
	 *
	 * @param empleado El empleado a ser guardado.
	 * @return El empleado guardado.
	 * @throws RuntimeException Si el empleado ya está registrado o si hay
	 *                          violaciones de validación.
	 */

	@Override
	public Empleados guardar(Empleados empleado) {
		if (existeEmpleadoConDni(empleado.getDni())) {
			throw new RuntimeException("¡Error! El empleado con DNI " + empleado.getDni() + " ya está registrado.");
		}

		Set<ConstraintViolation<Empleados>> violations = validator.validate(empleado);

		if (violations.isEmpty()) {
			// Guardo el empleado en el repository
			return repositorioEmpleado.save(empleado);
		} else {
			throw new RuntimeException("Error de validación: " + violations.toString());
		}
	}


	/**
	 * Verifica la existencia de un empleado por su DNI.
	 *
	 * @param dni El DNI del empleado a verificar.
	 * @return true si existe un empleado con el DNI proporcionado, false en caso
	 *         contrario.
	 */

	@Override
	// Compurebo que exista el empleado por ese DNI
	public boolean existeEmpleadoConDni(String dni) {
		return repositorioEmpleado.existsByDni(dni);
	}

	/**
	 * Obtiene un empleado por su DNI.
	 *
	 * @param dni El DNI del empleado a obtener.
	 * @return El empleado encontrado o null si no se encuentra.
	 */

	@Override
	public Empleados obtenerEmpleadoConDni(String dni) {
		Optional<Empleados> optionalEmpleado = repositorioEmpleado.findByDni(dni);
		// Si el empleado existe, devuelvo el objeto Empleados, de lo contrario devuelvo null
		return optionalEmpleado.orElse(null);
	}

	/**
	 * Actualiza la información de un empleado en el sistema.
	 *
	 * @param empleado El empleado con la información actualizada.
	 * @return El empleado actualizado.
	 * @throws RuntimeException Si el empleado no está registrado, si hay
	 *                          violaciones de validación o si no se encuentra la
	 *                          nómina asociada al empleado.
	 */

	@Override
	public Empleados actualizarEmpleado(Empleados empleado) {
		// Verifico si el empleado existe en la base de datos
		if (!repositorioEmpleado.existsByDni(empleado.getDni())) {
			throw new RuntimeException("¡Error! El empleado con DNI " + empleado.getDni() + " no está registrado.");
		}

		Set<ConstraintViolation<Empleados>> violations = validator.validate(empleado);

		if (violations.isEmpty()) {
			// Actualizo el empleado en el repository
			return repositorioEmpleado.save(empleado);
		} else {
			throw new RuntimeException("Error de validación: " + violations.toString());
		}
	}


	/**
	 * Elimina un empleado del sistema por su DNI.
	 *
	 * @param dni El DNI del empleado a ser eliminado.
	 * @throws RuntimeException Si el empleado no está registrado o si no se
	 *                          encuentra la nómina asociada al empleado.
	 */

	@Override
	@Transactional // Para que deje eliminar
	public void eliminarEmpleado(String dni) {
		Optional<Empleados> empleado = repositorioEmpleado.findByDni(dni);

		if (empleado.isPresent()) {
			// Elimino el empleado
			repositorioEmpleado.deleteByDni(dni);
		} else {
			throw new RuntimeException("¡Error! El empleado con DNI " + dni + " no está registrado.");
		}
	}

	/**
	 * Obtiene las nóminas asociadas a un empleado.
	 *
	 * @param empleado El empleado del cual se desean obtener las nóminas.
	 * @return Lista de nóminas asociadas al empleado.
	 */
	@Override
	public double calcularSueldo(Empleados empleado) {
		if (empleado != null) {
			int[] SUELDO_BASE = {50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000, 230000};
			return SUELDO_BASE[empleado.getCategoria() - 1] + 5000 * empleado.getAnyosTrabajados();
		} else {
			return 0;
		}
	}
}
