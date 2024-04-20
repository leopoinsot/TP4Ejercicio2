package ar.unrn.model;

import java.util.ArrayList;
import java.util.List;

public class Empleados {
	List<Empleado> empleados = new ArrayList<>();

	public Empleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public void mandarEmailFelizCumpleaños(ServicioEmail email, String asunto, String tema) {
		for (Empleado empleado : empleados) {
			if (empleado.cumpleAñosHoy()) {
				email.enviarEmail(empleado.obtenerEmail(), asunto, tema);
			}
		}
	}
}