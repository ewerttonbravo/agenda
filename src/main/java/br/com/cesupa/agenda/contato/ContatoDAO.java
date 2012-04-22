package br.com.cesupa.agenda.contato;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;

import br.com.cesupa.agenda.persistence.GenericDAO;

/**
 * @author ewerttonbravo
 *
 */
@Name("contatoDAO")
public class ContatoDAO extends GenericDAO<Contato, Long> {
	public ContatoDAO() {
	}
	
	@Create
	public void init() {
		create();
	}
}
