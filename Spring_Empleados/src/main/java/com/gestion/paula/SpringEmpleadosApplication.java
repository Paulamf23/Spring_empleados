package com.gestion.paula;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

//import model.com.gestion.paula.Empleados;
import com.gestion.paula.repository.EmpleadoRepo;

/**
 * Clase principal para la aplicación Spring Boot CRUD.
 *
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see org.springframework.context.annotation.ComponentScan
 * @see org.springframework.boot.CommandLineRunner
 * @see org.springframework.beans.factory.annotation.Autowired
 * @see EmpleadoRepo
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.gestion.paula")
public class SpringEmpleadosApplication implements CommandLineRunner {
	
	/**
     * Método principal que inicia la aplicación Spring Boot.
     * 
     * @param args Argumentos de línea de comandos pasados a la aplicación.
     */

	public static void main(String[] args) {
		SpringApplication.run(SpringEmpleadosApplication.class, args);
	}
	
	/**
     * Este método no tiene ninguna implementación específica en este momento.
     * Se utiliza para cumplir con la interfaz CommandLineRunner.
     * 
     * @param args Argumentos de línea de comandos pasados a la aplicación.
     * @throws Exception Si ocurre alguna excepción durante la ejecución.
     */
	
	@Override
	public void run(String... args) throws Exception { }
}
