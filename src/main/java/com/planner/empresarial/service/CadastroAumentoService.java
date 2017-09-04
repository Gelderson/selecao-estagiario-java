package com.planner.empresarial.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.planner.empresarial.model.Aumento;
import com.planner.empresarial.repository.Aumentos;
import com.planner.empresarial.util.jpa.Transactional;

public class CadastroAumentoService  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Aumentos aumentos;
	
	@Transactional
	public Aumento salvar(Aumento aumento) {

		return aumentos.guardar(aumento);
	}

}
