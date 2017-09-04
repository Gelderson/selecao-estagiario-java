package com.planner.empresarial.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.planner.empresarial.model.Aumento;
import com.planner.empresarial.service.NegocioException;
import com.planner.empresarial.util.jpa.Transactional;

public class Aumentos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Aumento guardar(Aumento aumento){
		return manager.merge(aumento);
	}
	
	@Transactional
	public void remover(Aumento aumento) {
		try {
			aumento = porId(aumento.getId());
			manager.remove(aumento);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Aumento não pode ser excluído.");
		}
	}
	
	public Aumento porId(Long id) {
		return this.manager.find(Aumento.class, id);
	}
	
	public List<Aumento> porNome(String nome) {
		return this.manager.createQuery("from Aumento " +
				"where upper(nome) like :nome", Aumento.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}
	
	public Aumento porCodigo(String codigo) {
		try {
			return this.manager.createQuery("from Aumento where upper(codigo)"
					+ " like :codigo",Aumento.class)
					.setParameter("codigo", codigo.toUpperCase())
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}	

	public Aumento porDescricao(String descricao) {
		try {
			return this.manager.createQuery("from Aumento where upper(descricao)"
					+ " like :descricao",Aumento.class)
					.setParameter("descricao", descricao.toUpperCase())
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}	
	
}