package com.gestion.paula.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;

/**
 * Empleados de la empresa
 * Cada empleado tiene un DNI, nombre, sexo, categoría, años de service y un indicador de eliminación.
 */

@Entity
@Table (name = "Empleados")
public class Empleados {

	@Id
    private String dni;
	
	 /**
     * Nombre del empleado.
     */
	@NotBlank(message = "El nombre no puede estar en blanco")
	private String nombre;
	
	/**
     * Sexo del empleado, debe ser 'M' o 'F'.
     */
	@Pattern(regexp = "[MF]", message = "El sexo debe ser 'M' o 'F'")
	private String sexo;
	
	 /**
     * Categoría del empleado, debe estar en el rango de 1 a 10.
     */
	@Min(value = 1, message = "La categoría debe ser al menos 1")
    @Max(value = 10, message = "La categoría no puede ser mayor que 10")
	private Integer categoria;
	
	/**
     * Años de service del empleado, debe ser al menos 0.
     */
	@Min(value = 0, message = "Los años deben ser al menos 0")
	private Integer anyosTrabajados;
	
	 /**
     * Indica si el empleado ha sido eliminado o no.
     */
	private Boolean deleted = false;

	public Empleados() {
	}

    /**
     * Constructor con todos los parámetros excepto el campo 'deleted'.
     * 
     * @param dni      Identificación única del empleado.
     * @param nombre   Nombre del empleado.
     * @param sexo     Sexo del empleado ('M' o 'F').
     * @param categoria Categoría del empleado (de 1 a 10).
     * @param anyosTrabajados    Años de service del empleado.
     * @throws DatosNoCorrectosException Si la categoría proporcionada no está en el rango de 1 a 10.
     */
	
	public Empleados(String dni, String nombre, String sexo, Integer categoria, Integer anyosTrabajados) throws DatosNoCorrectosException {
		
		if (categoria >= 1 && categoria <= 10) {
			this.categoria = categoria;
		} else {
			throw new DatosNoCorrectosException("Categoria incorrecta");
		}
		
		this.dni = dni;
		this.nombre = nombre;
		this.sexo = sexo;
		this.anyosTrabajados = anyosTrabajados;
		this.deleted = false; // Por defecto, no eliminado = 0
	}

	// Getters y setters

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public Integer getAnyosTrabajados() {
		return anyosTrabajados;
	}

	public void setAnyosTrabajados(Integer anyosTrabajados) {
		this.anyosTrabajados = anyosTrabajados;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
