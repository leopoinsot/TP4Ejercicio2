package org.example;

import ar.unrn.database.ArchivoDatos;
import ar.unrn.model.Email;
import ar.unrn.model.Empleados;

import java.time.LocalDate;

public class Main {
	public static void main(String[] args) {
		var regE = new ArchivoDatos("C:\\Users\\leonr\\OneDrive\\Escritorio\\ejercicio2TP4.txt");
		regE.registrarEmpleado("Poinsot", "Leonel", LocalDate.now().toString(), "leonrojopoinsot@gmail.com");
		regE.registrarEmpleado("Nocito", "jose", LocalDate.now().toString(), "joseNocito@gmail.com");
		regE.registrarEmpleado("Ferarri", "octavio", LocalDate.now().minusDays(1).toString(), "octavioFerrari@gmail.com");
		var empleados = new Empleados(regE.obtenerEmpleados());
		var email = new Email("sandbox.smtp.mailtrap.io", "2525", "1d8884f5484749", "ccb6e88c2f65a1");
		empleados.mandarEmailFelizCumpleaños(email, "Feliz cumpleaños", "Que tengas un exelente dia");

	}
}