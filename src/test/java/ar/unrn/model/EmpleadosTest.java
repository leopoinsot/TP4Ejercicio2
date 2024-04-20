package ar.unrn.model;

import ar.unrn.database.ArchivoDatos;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmpleadosTest {

	@Test
	void registrarEmpleadosTest() {
		var regE = new ArchivoDatos("C:\\Users\\leonr\\OneDrive\\Escritorio\\ejercicio2TP4.txt") {
			private static int cantidadEmpleadosRegistrados = 0;

			@Override
			public void registrarEmpleado(String apellido, String nombre, String fechaDeNacimiento, String email) {
				cantidadEmpleadosRegistrados = cantidadEmpleadosRegistrados + 1;
			}

			public int obtenerCantidadEmpleadosRegistrados() {
				return cantidadEmpleadosRegistrados;
			}
		};
		regE.registrarEmpleado("Poinsot", "Leonel", LocalDate.now().toString(), "leonrojopoinsot@gmail.com");
		regE.registrarEmpleado("Nocito", "jose", LocalDate.now().toString(), "joseNocito@gmail.com");
		assertEquals(2, regE.obtenerCantidadEmpleadosRegistrados());
	}

	@Test
	public void mandarEmailFelizCumpleañosTest() {
		var regE = new ArchivoDatos("C:\\Users\\leonr\\OneDrive\\Escritorio\\ejercicio2TP4.txt") {
			private static int cantidadEmpleadosRegistrados = 0;

			@Override
			public void registrarEmpleado(String apellido, String nombre, String fechaDeNacimiento, String email) {
				cantidadEmpleadosRegistrados = cantidadEmpleadosRegistrados + 1;
			}

			public int obtenerCantidadEmpleadosRegistrados() {
				return cantidadEmpleadosRegistrados;
			}
		};
		regE.registrarEmpleado("Poinsot", "Leonel", LocalDate.now().toString(), "leonrojopoinsot@gmail.com");
		regE.registrarEmpleado("Nocito", "jose", LocalDate.now().toString(), "joseNocito@gmail.com");
		regE.registrarEmpleado("Ferarri", "octavio", LocalDate.now().minusDays(1).toString(), "octavioFerrari@gmail.com");

		var empleados = new Empleados(regE.obtenerEmpleados());

		var email = new Email("sandbox.smtp.mailtrap.io", "2525", "1d8884f5484749", "ccb6e88c2f65a1") {
			private int cantidadDeEmailEnviados = 0;

			@Override
			public void enviarEmail(String destinatarioEmail, String asunto, String tema) {
				cantidadDeEmailEnviados = cantidadDeEmailEnviados + 1;
			}

			public int cantidadDeEmailEnviadosEmpleados() {
				return cantidadDeEmailEnviados;
			}
		};
		empleados.mandarEmailFelizCumpleaños(email, "¡Feliz cumpleaños!", "¡Que tengas un exelente dia!");
		assertEquals(2, email.cantidadDeEmailEnviadosEmpleados());
	}
}