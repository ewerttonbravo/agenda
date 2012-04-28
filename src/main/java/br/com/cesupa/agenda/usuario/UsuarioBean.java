package br.com.cesupa.agenda.usuario;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.log.Log;


@Name("usuarioBean")
@Scope(ScopeType.CONVERSATION)
public class UsuarioBean {
	
	@In(create=true)
	private Usuario usuario;
	
	@In(create=true)
	private UsuarioDAO usuarioDAO;
	
	@DataModel("usuarios")
	private List<Usuario> usuarios;
	
	@Logger
	private Log logger;
	
	public UsuarioBean() {}
	
	public String salvar() {
		try {
			usuario.setRole("normal");
			usuarioDAO.save(usuario);
			return "success";
		} catch (Exception e) {
			logger.error("Erro ao salvar usuario", e);
			return null;
		}
	}
	
	@Factory("usuarios")
	public void initUsuarios() {
		usuarios = usuarioDAO.findAll();
	}
}
