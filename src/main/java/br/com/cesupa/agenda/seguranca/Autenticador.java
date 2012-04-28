package br.com.cesupa.agenda.seguranca;

import javax.persistence.NoResultException;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import br.com.cesupa.agenda.usuario.Usuario;
import br.com.cesupa.agenda.usuario.UsuarioDAO;

@Name("autenticador")
public class Autenticador {
	
	@In
	private Credentials credentials;
	
	@In
	private Identity identity;
	
	@Logger
	private Log logger;
	
	@In(create=true)
	private UsuarioDAO usuarioDAO;
	
	public boolean login() {
		String email = credentials.getUsername();
		String senha = credentials.getPassword();
		
		logger.info("Tentantiva de autenticacao do usuario #0", email);
		
		try {
			Usuario u = usuarioDAO.findByEmailESenha(email, senha);
			logger.info("Usuario [#0] autenticacao com sucesso", email);
			return true;
		} catch (NoResultException ne) {
			logger.error("Email/senha invalidos", ne);
		} catch (Exception e2) {
			logger.error("Usuario nao autenticado", e2);
		}
		return false;
	}
}
