package br.com.cesupa.agenda.seguranca;

import javax.persistence.NoResultException;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import br.com.cesupa.agenda.usuario.Usuario;
import br.com.cesupa.agenda.usuario.UsuarioDAO;

@Name("autenticador")
public class Autenticador {
	
	@In
	private Credentials credentials;
	
	@In("org.jboss.seam.security.identity")
	private Identity identity;
	
	@Logger
	private Log logger;
	
	@In(create=true)
	private UsuarioDAO usuarioDAO;
	
	@In(create=false, required=false) @Out
	private Usuario currentUser;
	
	@In("org.jboss.seam.international.statusMessages")
	private StatusMessages statusMessages;
	
	public boolean login() {
		String email = credentials.getUsername();
		String senha = credentials.getPassword();
		
		logger.info("Tentantiva de autenticacao do usuario #0", email);
		
		try {
			Usuario u = usuarioDAO.findByEmailESenha(email, senha);
			logger.info("Usuario [#0] autenticacao com sucesso", email);
			identity.addRole(u.getRole());
			logger.info("Papel do usuario: [#0]", u.getRole());
			currentUser = u;
			
			return true;
		} catch (NoResultException ne) {
			logger.error("Email/senha invalidos", ne);
		} catch (Exception e2) {
			logger.error("Usuario nao autenticado", e2);
		}
		return false;
	}
}
