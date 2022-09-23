package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.entity.Turma;
import br.com.senac.service.TurmaService;

@Controller
@RequestMapping("turma")//http://localhost:8080/turma
public class TurmaController {
	
	@Autowired
	private TurmaService turmaService;
	
	@GetMapping("/listarTurmas")//http://localhost:8080/turma/listarTurmas
	public ModelAndView listarTodasTurmas() {
		ModelAndView mv = new ModelAndView("turma/paginaListaTurmas");
		mv.addObject("turmas", turmaService.buscarTodasTurmas());
		return mv;
	}
	
	@GetMapping("/cadastrarTurma")
	public ModelAndView cadastrarTurma() {
		ModelAndView mv = new ModelAndView("turma/cadastrarTurma");
		mv.addObject("turma", new Turma());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvarCurso(Turma turma) {
		turmaService.salvar(turma);
		return listarTodasTurmas();
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluirTurma(@PathVariable("id") Integer id) {
		turmaService.deletarPorId(id);
		return listarTodasTurmas();
	}
	
	@GetMapping("/paginaAlterar/{id}")
	public ModelAndView alterarTurma(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView("turma/alterarTurma");
		mv.addObject("turma", turmaService.buscarPorId(id));
		return mv;
	}
	
	@PostMapping("/salvarAlteracao")
	public ModelAndView alterar(Turma turmaAlterado) {
		turmaService.salvarAlteracao(turmaAlterado);
		return listarTodasTurmas();
	}
	
	
	//Meu metodo de excluir
	//@GetMapping("/excluir/{id}")
	//public String excluirCurso(@PathVariable("id") Integer id) {
		//turmaService.deletarPorId(id);
		//return "redirect:/turma/listarTurmas";
	//}
	
	
	
}
