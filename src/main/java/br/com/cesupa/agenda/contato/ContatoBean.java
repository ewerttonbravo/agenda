package br.com.cesupa.agenda.contato;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.core.Conversation;
import org.jboss.seam.log.Log;


@Name("contatoBean")
@Scope(ScopeType.CONVERSATION)
public class ContatoBean {
	
	@In(create=true) @Out
	private Contato contato;
	
	@In(create=true)
	private ContatoDAO contatoDAO;
	
	@Logger
	private Log logger;
	
	@DataModel("contatos")
	private List<Contato> contatos;
	
	@DataModelSelection("contatos")
	private Contato contatoSelecionado;
	
	public ContatoBean() {}
	
	public String salvar() {
		logger.info("Salvando contato #0", contato.getNome());
		try {
			contatoDAO.save(contato);
			return "success";
		} catch (Exception e) {
			logger.error("Erro ao salvar contato", e);
			return null;
		}
	}
	
	@Begin(join=true)
	public String editar() {
		logger.info("Preparando para editar contato #0", contatoSelecionado);
		contato = contatoSelecionado;
		return "editar";
	}
	
	public String salvarAlteracoes() {
		try {
			contatoDAO.update(contato);
			Conversation.instance().end();
			return "success";
		} catch (Exception e) {
			logger.error("Erro ao atualizar contato #0", contato, e);
			return null;
		}
	}
	
	@Factory("contatos")
	public void initContatos() {
		contatos = contatoDAO.findAll();
	}
}
