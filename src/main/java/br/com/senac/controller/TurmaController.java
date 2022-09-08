package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	@GetMapping("/excluir/{id}")
	public String excluirCurso(@PathVariable("id") Integer id) {
		turmaService.deletarPorId(id);
		return "redirect:/turma/listarTurmas";
	}
}
