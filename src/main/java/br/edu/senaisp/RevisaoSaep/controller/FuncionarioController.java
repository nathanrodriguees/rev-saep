package br.edu.senaisp.RevisaoSaep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.senaisp.RevisaoSaep.model.Funcionario;
import br.edu.senaisp.RevisaoSaep.repository.FuncionarioRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	FuncionarioRepository funcionarioRepository;

	@GetMapping("/listar")
	public String ListarFuncionarios(Model model) {
		List<Funcionario> funcionarios = (funcionarioRepository.findAll());

		model.addAttribute("funcionarios", funcionarios);

		return "/RevSaepLst";
	}

	@GetMapping("/novo")
	public String novo(Funcionario funcionario) {

		return "/RevSaepFrm";
	}
	
	@PostMapping("/gravar")
	public String gravar(@Valid Funcionario funcionario, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "/RevSaepFrm";
		}
		
		funcionarioRepository.save(funcionario);
		
		return "redirect:/funcionario/listar";
		
	}
}
