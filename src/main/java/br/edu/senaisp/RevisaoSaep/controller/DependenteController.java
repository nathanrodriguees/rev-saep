package br.edu.senaisp.RevisaoSaep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.senaisp.RevisaoSaep.model.Dependente;
import br.edu.senaisp.RevisaoSaep.model.Funcionario;
import br.edu.senaisp.RevisaoSaep.repository.DependenteRepository;
import br.edu.senaisp.RevisaoSaep.repository.FuncionarioRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/dependente")
public class DependenteController {

	@Autowired
	DependenteRepository dependenteRepository;

	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@GetMapping("/listar")
	public String ListarDependentes(Model model) {
		List<Dependente> dependentes = (dependenteRepository.findAll());

		model.addAttribute("dependentes", dependentes);

		return "/DependenteLst";
	}

	@GetMapping("/novo")
	public String novo(Model model) {

		List<Funcionario> tmp = funcionarioRepository.findAll();

		model.addAttribute("funcionarios", tmp);
		model.addAttribute("dependente", new Dependente());

		return "/DependenteFrm";
	}
	
	@PostMapping("/gravar")
	public String gravar(@Valid Dependente dependente, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "/DependenteFrm";
		}
		
		dependenteRepository.save(dependente);
		
		return "redirect:/dependente/listar";
		
	}
}
