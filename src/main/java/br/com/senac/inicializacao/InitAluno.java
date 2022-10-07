package br.com.senac.inicializacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senac.entity.Aluno;
import br.com.senac.entity.Turma;
import br.com.senac.service.AlunoService;
import br.com.senac.service.TurmaService;

@Component
public class InitAluno implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private AlunoService alunoService;
	
	//@Autowired
	//AlunoRepository repo;
	
	@Autowired
	private TurmaService turmaService;
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		Turma turma1 = new Turma();
		turma1.setNome("2021.1N");
		Turma turma2 = new Turma();
		turma2.setNome("2021.2N");
		Turma turma3 = new Turma();
		turma3.setNome("2021.2N");
		
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
		
		List<Aluno> listaAlunos = alunoService.buscarTodosAlunos();
		
		//repo.saveAll(Arrays.asList(aluno1, aluno2, aluno3));
		
		for(Aluno aluno : listaAlunos) {
			System.out.println(aluno.getNome());
		}
		
		//Busca por id
		Aluno aluno4 = alunoService.buscarPorId(1);
		
		System.out.println(aluno4.getNome());
		
		//deleção por id
		//alunoService.deletarPorId(2);
	
		//Atualizar por id
		//aluno1.setNome("Carlos");
		//alunoService.atualizarPorId(1, aluno1);
		//
		//System.out.println("Teste Atualização: " + aluno1.getNome());
		
		//Aluno alunoAlterado = new Aluno();
		//alunoAlterado.setId(1);
		//alunoAlterado.setNome("Lucas Silva");
		
		//alunoService.salvarAlteracao(alunoAlterado);
		
		
	}

}
