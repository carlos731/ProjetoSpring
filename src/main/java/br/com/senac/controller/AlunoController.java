package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.service.AlunoService;

@Controller
@RequestMapping("aluno")//http://localhost:8080/aluno
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@GetMapping("/listarAlunos")//http://localhost:8080/aluno/listarAlunos
	public ModelAndView listaTodosAlunos() {
		ModelAndView mv = new ModelAndView("aluno/paginaListaAlunos");
		mv.addObject("alunos", alunoService.buscarTodosAlunos());
		return mv;
	}
	
	@GetMapping("/excluir/{id}")
	public String excluirAluno(@PathVariable("id") Integer id) {
		alunoService.deletarPorId(id);
		return "redirect:/aluno/listarAlunos";
	}
}	
