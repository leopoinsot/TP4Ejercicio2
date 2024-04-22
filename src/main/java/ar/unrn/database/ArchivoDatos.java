package ar.unrn.database;

import ar.unrn.model.Empleado;
import ar.unrn.model.RegistroEmpleado;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArchivoDatos implements RegistroEmpleado {
	private File fileInscripcion;

	public ArchivoDatos(File fileInscripcion) {
		this.fileInscripcion = fileInscripcion;
	}

	@Override
	public void registrarEmpleado(String apellido, String nombre, String fechaDeNacimiento, String email) throws RuntimeException {
		String cadena = apellido + "," + nombre + "," + fechaDeNacimiento + "," + email + System.lineSeparator();
		try {
			Files.write(
					Paths.get(fileInscripcion.getPath()),
					cadena.getBytes(),
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new RuntimeException("No se pudo agregar el empleado al archivo");
		}
	}

	public List<Empleado> obtenerEmpleados() {
		List<Empleado> empleados = new ArrayList<>();
		try {
			var scanner = new Scanner(fileInscripcion);
			while (scanner.hasNextLine()) {
				String linea = scanner.nextLine();
				// Dividimos la línea en sus componentes usando la coma como delimitador
				String[] partes = linea.split(",");
				// Parseamos la fechaNacimiento
				var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				var fechaNacimiento = LocalDate.parse(partes[2], formatter);

				// Creamos un objeto Empleado con la información de la línea y la fecha parseada
				var empleado = new Empleado(partes[0], partes[1], fechaNacimiento, partes[3]);

				// Agregamos el empelado a la lista
				empleados.add(empleado);
			}
			scanner.close(); // Cerramos el scanner después de usarlo
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return empleados;
	}
}