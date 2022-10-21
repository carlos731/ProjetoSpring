package br.com.senac.inicializacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senac.entity.Aluno;
import br.com.senac.entity.AlunoCurso;
import br.com.senac.entity.Avaliacao;
import br.com.senac.entity.Curso;
import br.com.senac.entity.Professor;
import br.com.senac.entity.Turma;
import br.com.senac.repository.AlunoRepository;
import br.com.senac.repository.AvaliacaoRepository;
import br.com.senac.repository.CursoRepository;
import br.com.senac.repository.ProfessorRepository;
import br.com.senac.repository.TurmaRepository;
import br.com.senac.service.AlunoService;
import br.com.senac.service.AvaliacaoService;
import br.com.senac.service.CursoService;
import br.com.senac.service.ProfessorService;
import br.com.senac.service.TurmaService;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired 
	private AlunoService alunoService;
	@Autowired
	private TurmaService turmaService;
	@Autowired
	private CursoService cursoService;
	@Autowired 
	private ProfessorService professorService;
	@Autowired 
	private AvaliacaoService avaliacaoService;
	

	@Autowired 
	private ProfessorRepository professorRepository;
	@Autowired 
	private AlunoRepository alunoRepository;
	@Autowired
	private TurmaRepository turmaRepository;
	@Autowired
	private CursoRepository cursoRepository;
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	
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
				
				List<Curso> listaCursos1 = new ArrayList<>();
				listaCursos1.add(curso1);
				listaCursos1.add(curso2);
				
				List<Curso> listaCursos2 = new ArrayList<>();
				listaCursos2.add(curso2);
				listaCursos2.add(curso3);
				
				List<Curso> listaCursos3 = new ArrayList<>();
				listaCursos3.add(curso2);
				listaCursos3.add(curso3);
				
				//Turmas
				Turma turma1 = new Turma();
				turma1.setNome("3001");
				turma1.setCursos(listaCursos1);
				turmaService.salvar(turma1);
				
				Turma turma2 = new Turma();
				turma2.setNome("3002");
				turma2.setCursos(listaCursos2);
				turmaService.salvar(turma2);
				
				Turma turma3 = new Turma();
				turma3.setNome("3003");
				turma1.setCursos(listaCursos3);
				turmaService.salvar(turma2);
				
				//Teste salvar no banco
				turmaService.salvar(turma1);
				turmaService.salvar(turma2);
				turmaService.salvar(turma3);
		
				Aluno aluno1 = new Aluno();
				aluno1.setNome("Lucas");
				aluno1.setTurma(turma3);
				
				Aluno aluno2 = new Aluno();
				aluno2.setNome("Arthur");
				aluno2.setTurma(turma3);
				
				Aluno aluno3 = new Aluno();
				aluno3.setNome("Jose");
				aluno3.setTurma(turma1);
				
				alunoService.salvar(aluno1);
				alunoService.salvar(aluno2);
				alunoService.salvar(aluno3);
			
				
				Professor prof1 = new Professor();
				prof1.setNome("Johnny Tafur");
				
				Professor prof2 = new Professor();
				prof2.setNome("Gabriella Silveira");
				
				Professor prof3 = new Professor();
				prof3.setNome("Marcelo Struct");
				
				professorService.salvar(prof1);
				professorService.salvar(prof2);
				professorService.salvar(prof3);
				
				List<Professor> listaProfessores = professorService.buscarTodosProfessores();
				
				//repo.saveAll(Arrays.asList(aluno1, aluno2, aluno3));
				
				for(Professor professor : listaProfessores) {
					System.out.println(professor.getNome());
				}
				
				//Busca por id
				Professor prof4 = professorService.buscarPorId(1);
				
				System.out.println(prof4.getNome());
				
				//deleção por id
				//professorService.deletarPorId(2);
			
				//Atualizar por id
				//aluno1.setNome("Carlos");
				//alunoService.atualizarPorId(1, aluno1);
				//
				//System.out.println("Teste Atualização: " + aluno1.getNome());
				
				Professor professorAlterado = new Professor();
				professorAlterado.setId(1);
				professorAlterado.setNome("Marlom Gomes");
				
				professorService.salvarAlteracao(professorAlterado);
				
				//Avaliações
				Avaliacao avaliacao1 = new Avaliacao();
				AlunoCurso alunoCurso1 = new AlunoCurso();
				alunoCurso1.setAluno(aluno2);
				alunoCurso1.setCurso(curso3);
				avaliacao1.setAlunoCurso(alunoCurso1);
				avaliacao1.setConceito("I");
				
				avaliacaoService.save(avaliacao1);
	}
}
