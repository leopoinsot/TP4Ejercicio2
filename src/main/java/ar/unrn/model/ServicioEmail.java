package ar.unrn.model;

public interface ServicioEmail {
	public void enviarEmail(String destinatarioEmail, String asunto, String tema);
}
