package br.com.senac.inicializacao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senac.entity.Curso;
import br.com.senac.entity.Professor;
import br.com.senac.repository.ProfessorRepository;
import br.com.senac.service.CursoService;
import br.com.senac.service.ProfessorService;


@Component
public class InitCurso implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private CursoService cursoService;
	
	@Autowired 
	private ProfessorService professorService;
	
	@Autowired 
	private ProfessorRepository professorRepository;
	
	//@Autowired
	//AlunoRepository repo;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		//Cursos
		Curso curso1 = new Curso();
		curso1.setNome("Matemática");
		
		Curso curso2 = new Curso();
		curso2.setNome("Fisica");
		
		Curso curso3 = new Curso();
		curso3.setNome("Quimica");
	
		//Professores
		Professor p1 = new Professor();
		p1.setNome("Lucas");
		
		Professor p2 = new Professor();
		p2.setNome("Joao");
		
		Professor p3 = new Professor();
		p3.setNome("Claudio");
		
		//opção 2 para salvar
		//professorRepository.saveAll(Arrays.asList(p1, p2 , p3));
		
		//Salvando professor
		professorService.salvar(p1);
		professorService.salvar(p2);
		professorService.salvar(p3);
		
		//referenciando id do professor no curso
		curso1.setProfessor(p1);
		curso2.setProfessor(p3);
		curso3.setProfessor(p2);
		
		//Teste salvar no banco
		cursoService.salvar(curso1);
		cursoService.salvar(curso2);
		cursoService.salvar(curso3);
		
		List<Curso> listaCursos = cursoService.buscarTodos();
		
		//repo.saveAll(Arrays.asList(aluno1, aluno2, aluno3));
		
		//Exibir nomes das turmas
		for(Curso curso : listaCursos) {
			System.out.println(curso.getNome());
		}
		
		//Buscar por id
		Curso busca = cursoService.buscarPorId(2);
		
		System.out.println(busca.getNome());
		
		//deleção por id
		//cursoService.deletarPorId(3);
		
		//Atualização por id
		//Curso cursoAlterado = new Curso();
		//cursoAlterado.setId(2);
		//cursoAlterado.setNome("Portugues");
		
		//cursoService.salvar(cursoAlterado);
		
	}
}
