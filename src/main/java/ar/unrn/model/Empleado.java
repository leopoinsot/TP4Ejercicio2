package ar.unrn.model;

import java.time.LocalDate;

public class Empleado {
	private String apellido;
	private String nombre;
	private LocalDate fechaDeNacimiento;
	private String email;

	public Empleado(String apellido, String nombre, LocalDate fechaDeNacimiento, String email) {
		this.apellido = apellido;
		this.nombre = nombre;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.email = email;
	}

	public String obtenerEmail() {
		return email;
	}

	public boolean cumpleAñosHoy() {
		return fechaDeNacimiento.equals(LocalDate.now());
	}
}
