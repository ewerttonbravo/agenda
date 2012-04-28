package br.com.cesupa.agenda.usuario;

import static org.jboss.seam.ScopeType.SESSION;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import org.hibernate.validator.Email;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Role;

@Name("usuario")
@Entity
@NamedQueries({
	@NamedQuery(name="findUsuarioByEmail", 
				query="select u from Usuario u where u.email = :email"),
	@NamedQuery(name="findAllUsuario", query="from Usuario"),
	@NamedQuery(name="findUsuarioByEmailAndSenha",
				query="select u from Usuario u where u.email = :email" +
						" and u.senha = :senha")
	
})
@Role(name="currentUser", scope=SESSION)
public class Usuario {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	@NotEmpty
	@Length(min=6, max=8)
	private String senha;
	
	/**
	 * admin - usuario administrador
	 * nomal - demais usuarios/clientes
	 */
	private String role;
	
	@Transient
	private String confSenha;
	
	public Usuario() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getConfSenha() { return confSenha; }
	public void setConfSenha(String confSenha) {
		this.confSenha = confSenha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + "]";
	}
}
