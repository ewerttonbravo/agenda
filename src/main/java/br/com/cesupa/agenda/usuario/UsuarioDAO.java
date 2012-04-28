package br.com.cesupa.agenda.usuario;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

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
	
	public Usuario findByEmailESenha(String email, String senha) 
			throws NoResultException, NonUniqueResultException {
		Query query = entityManager.createNamedQuery("findUsuarioByEmailAndSenha");
		query.setParameter("email", email);
		query.setParameter("senha", senha);
		return (Usuario) query.getSingleResult();
	}
}
