package br.com.cesupa.agenda.contato;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;


@Name("contatoBean")
@Scope(ScopeType.SESSION)
public class ContatoBean {
	
	@In(create=true)
	private Contato contato;
	
//	@In
//	private EntityManager agendaEntityManager;
	
	@In(create=true)
	private ContatoDAO contatoDAO;
	
	@Logger
	private Log logger;
	
	public ContatoBean() {}
	
	public String salvar() {
		logger.info("Salvando contato #0", contato.getNome());
		try {
			contatoDAO.save(contato);
		} catch (Exception e) {
			logger.error("Erro ao salvar contato", e);
		}
		return "success";
	}
}
