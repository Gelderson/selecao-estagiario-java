package com.planner.empresarial.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.planner.empresarial.model.Aumento;
import com.planner.empresarial.model.Funcionario;
import com.planner.empresarial.service.CadastroAumentoService;
import com.planner.empresarial.service.CadastroFuncionarioService;
import com.planner.empresarial.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAumentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroAumentoService cadastroAumentoService;
	
	@Inject
	private CadastroFuncionarioService cadastroFuncionarioService;

	private Aumento aumento;
	
	public CadastroAumentoBean() {
		limpar();
	}

	private void limpar() {
		aumento = new Aumento();
	}

	public void salvar() {
		this.aumento = cadastroAumentoService.salvar(this.aumento);
		limpar();
	}
	
	public void caucular() {
		
		for (Funcionario f : aumento.getCargo().getFuncionarios()) {
			  aplicarAumento(f);	  
		}
		FacesUtil.addInfoMessage("Aumento Salarial feito com sucesso!");
	}
	
	public void aplicarAumento(Funcionario funcionario){
		double resultado ;
		resultado = funcionario.getSalario()+ (funcionario.getSalario()*aumento.getQuantidade())/100;
		funcionario.setSalario(resultado);
		
		cadastroFuncionarioService.salvar(funcionario);
	}
	public boolean isEditando() {
		return aumento.getId() != null;
	}
	
	public void setAumento(Aumento aumento) {
		this.aumento = aumento;
	}

	public Aumento getAumento() {
		return aumento;
	}

}
