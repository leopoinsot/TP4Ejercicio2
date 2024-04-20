package ar.unrn.database;

import ar.unrn.model.Empleado;
import ar.unrn.model.RegistroEmpleado;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ArchivoDatos implements RegistroEmpleado {
	private String path;

	public ArchivoDatos(String path) {
		this.path = path;
	}

	@Override
	public void registrarEmpleado(String apellido, String nombre, String fechaDeNacimiento, String email) throws RuntimeException {
		String cadena = apellido + ", " + nombre + ", " + fechaDeNacimiento + ", " + email + System.lineSeparator();
		try {
			Files.write(
					Paths.get(path),
					cadena.getBytes(),
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new RuntimeException("No se pudo agregar el empleado al archivo");
		}
	}

	public List<Empleado> obtenerEmpleados() {
		List<Empleado> empleados = new ArrayList<>();
		try {
			List<String> lineas = Files.readAllLines(Paths.get(path));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			for (String linea : lineas) {
				String[] datos = linea.split(", ");
				String apellido = datos[0].trim();
				String nombre = datos[1].trim();
				LocalDate fechaDeNacimiento = LocalDate.parse(datos[2].trim(), formatter);
				String email = datos[3].trim();
				empleados.add(new Empleado(apellido, nombre, fechaDeNacimiento, email));
			}
		} catch (IOException e) {
			throw new RuntimeException("No se puede leer el archivo");
		}
		return empleados;
	}
}