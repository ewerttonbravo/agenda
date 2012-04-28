package br.com.cesupa.agenda.usuario;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;

import br.com.cesupa.agenda.persistence.GenericDAO;

@Name("usuarioDAO")
public class UsuarioDAO extends GenericDAO<Usuario, Long> {
	public UsuarioDAO() {}
	
	@Create
	public void init() {
		create();
	}
}
