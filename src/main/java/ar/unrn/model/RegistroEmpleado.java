package ar.unrn.model;

import java.util.List;

public interface RegistroEmpleado {
	void registrarEmpleado(String apellido, String nombre, String fechaDeNacimiento, String email);

	List<Empleado> obtenerEmpleados();
}

