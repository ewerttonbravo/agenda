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
	
//	@In("agendaSession")
//	private Session session;
	
	@In
	private EntityManager agendaEntityManager;
	
	@Logger
	private Log logger;
	
	public ContatoBean() {}
	
	public String salvar() {
		logger.info("Salvando contato #0", contato.getNome());
		session.save(contato);
		return "success";
	}
	
}
