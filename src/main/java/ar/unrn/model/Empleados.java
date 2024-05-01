package ar.unrn.model;

import java.util.ArrayList;
import java.util.List;

public class Empleados {
	private List<Empleado> empleados = new ArrayList<>();

	private ServicioEmail email;

	public Empleados(CargarEmpleados cargarEmpleados, ServicioEmail email) {
		this.empleados = cargarEmpleados.empleados();
		this.email = email;
	}

	public void mandarEmailFelizCumpleaños(String asunto, String tema) {
		for (Empleado empleado : empleados) {
			if (empleado.cumpleAñosHoy()) {
				email.enviarEmail(empleado.obtenerEmail(), asunto, tema);
			}
		}
	}
}