package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.entity.Aluno;
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
	
	@GetMapping("/cadastrarAluno")
	public ModelAndView cadastrarAluno() {
		ModelAndView mv = new ModelAndView("aluno/cadastraAluno");
		mv.addObject("aluno", new Aluno());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvarAluno(Aluno aluno) {
		alunoService.salvar(aluno);
		return listaTodosAlunos();
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluirAluno(@PathVariable("id") Integer id) {
		alunoService.deletarPorId(id);
		return listaTodosAlunos();
	}
	
	@GetMapping("/paginaAlterar/{id}")
	public ModelAndView alterarAluno(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView("aluno/alterarAluno");
		mv.addObject("aluno", alunoService.buscarPorId(id));
		return mv;
	}
	
	@PostMapping("/salvarAlteracao")
	public ModelAndView alterar(Aluno alunoAlterado) {
		alunoService.salvarAlteracao(alunoAlterado);
		return listaTodosAlunos();
	}
	
	//Meu metodo de excluir
	//@GetMapping("/excluir/{id}")
	//public String excluirAluno(@PathVariable("id") Integer id) {
		//alunoService.deletarPorId(id);
		//return "redirect:/aluno/listarAlunos";
	//}
}	
