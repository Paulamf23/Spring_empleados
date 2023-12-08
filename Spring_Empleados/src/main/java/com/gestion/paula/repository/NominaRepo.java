//package com.gestion.paula.repository;
//
//import java.util.UUID;
//
//import com.gestion.paula.model.Nomina;
//import org.springframework.data.jpa.repository.JpaRepository;
//
///**
// * Ddefine las operaciones de acceso a datos
// *
// * @see org.springframework.data.jpa.repository.JpaRepository
// * @see java.util.UUID
// * @see Nomina
// */
//
//public interface NominaRepo extends JpaRepository<Nomina, UUID> {
//
//	 /**
//     * Busca y devuelve una nómina por el DNI de su empleado asociado.
//     *
//     * @param dni El DNI del empleado asociado a la nómina.
//     * @return La nómina encontrada, o null si no se encuentra ninguna.
//     */
//
//	Nomina findByEmpleado_Dni(String dni);
//}
