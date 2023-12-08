//package com.gestion.paula.model;
//
//import jakarta.persistence.*;
//import java.util.UUID;
//
///**
// * Nómina de un empleado
// */
//
//@Entity
//@Table(name = "Nomina")
//public class Nomina {
//
//	/**
//	 * Identificador único de la nómina.
//	 */
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private UUID id;
//
//	/**
//	 * Empleado asociado a la nómina.
//	 */
//
//	@ManyToOne
//	@JoinColumn(name = "dni_empleado", referencedColumnName = "dni")
//	private Empleados empleado;
//
//	private int sueldo;
//
//	public Nomina() {
//	}
//
//	public Nomina(Empleados empleado) {
//		this.empleado = empleado;
//		this.sueldo = calcularSueldo(empleado);
//	}
//
//	public UUID getId() {
//		return id;
//	}
//
//	public void setId(UUID id) {
//		this.id = id;
//	}
//
//	public Empleados getEmpleado() {
//		return empleado;
//	}
//
//	public void setEmpleado(Empleados empleado) {
//		this.empleado = empleado;
//		this.sueldo = calcularSueldo(empleado);
//	}
//
//	public int getSueldo() {
//		return sueldo;
//	}
//
//	public void setSueldo(int sueldo) {
//		this.sueldo = sueldo;
//	}
//
//	public int calcularSueldo(Empleados e) {
//		int[] SUELDO_BASE = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000, 230000 };
//		return SUELDO_BASE[e.getCategoria() - 1] + 5000 * e.getAnyosTrabajados();
//	}
//
//}
