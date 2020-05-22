package br.com.bonfim.capoeira.service.complemento;

import br.com.bonfim.capoeira.Repository.EnderecoAlunoRepository;
import br.com.bonfim.capoeira.Repository.EstadoRepository;
import br.com.bonfim.capoeira.Repository.GraduacaoRepository;
import br.com.bonfim.capoeira.model.EventoModel;
import br.com.bonfim.capoeira.model.cadastro.*;
import br.com.bonfim.capoeira.model.cadastro.tipoEnum.*;
import br.com.bonfim.capoeira.model.config.ParametroConfiguracao;
import br.com.bonfim.capoeira.service.*;
import br.com.bonfim.capoeira.service.cadastro.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private EnderecoAlunoRepository enderecoAlunoRepository;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private AulaService aulaService;

    @Autowired
    private GraduacaoRepository graduacaoRepository;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ParametroConfiguracaoService parametroConfiguracaoService;

    @Autowired
    private FinanceiroService financeiroService;

    @Autowired
    private HistoricoFinanceiroService historicoFinanceiroService;

    @Autowired
    private HistoricoFrequenciaService historicoFrequenciaService;

    @Autowired
    private  HistoricoGraduacaoService historicoGraduacaoService;


    public void instantiateTestDatabase(){

        Estado est1 = new Estado(null, "Santa Catarina");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Joinville", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Jundiai", est2);

        estadoRepository.saveAll(Arrays.asList(est1, est2));

        cidadeService.inserir(c1);
        cidadeService.inserir(c2);
        cidadeService.inserir(c3);

        //(Long id, String razaoSocial, String nomeFantasia, String cnpj, String inscrEstadual, String inscrMuniciapl, String cnae)
        Empresa emp1 = new Empresa(null, "EMPRESA CAPOEIRA S/A", "Capoeira Tech", "88155183000165", "252708006027", "1236547", "6201-5/01");

        //(Integer id, String logradouro, String numero, String complemento, String bairro, String cep, Empresa empresa, Cidade cidade)
        EnderecoEmpresa empEnd = new EnderecoEmpresa(null, "Av JK", "S/N", "Esquina da Kubsheck", "Jd Main", "07920000", emp1, c1);
        emp1.getEnderecos().add(empEnd);

        empresaService.insert(emp1);

        //(Long id, String descricao, Integer dia, String hora, Long duracao)
        Aula aula1 = new Aula(null, "Treino Manhã", 3, "08:00", 90l);
        aulaService.insert(aula1);

        //(Long id, String categoria, String nome_corda, String desc_corda, String significado, String observacao)
        Graduacao grad1 = new Graduacao(null, "ADULTO", "CORDA CRUA", "CORDA CINZA", "NEUTRALIZADADE E ADAPTAÇÃO", "ESSA GRADUAÇÃO É USADA NAS CATEGORIAS INFANTIS DE 06 A 14 ANOS, E SERVE COMO CORDA DE ADAPTAÇÃO PARA OS CAPOEIRISTAS QUE CASO VENHA DE OUTRA ESCOLA (GRUPO), O MESMO FICARA NESTA GRADUAÇÃO POR UM PERIODO DE UM ANO PARA ADAPTAÇÃO, APÓS RECEBERÁ A GRADUAÇÃO EQUIVALENTE DENTRO DO NOSSO SISTEMA.");
        Graduacao grad2 = new Graduacao(null, "ADULTO", "CORDA AMARELA", "CORDA DA COR AMARELA", "NOBREZA, FORÇA, ENTUSIASMO E RIQUEZA", "O CAPOEIRISTA ESTÁ EM PLENO VIGOR PARA AS SUAS ATIVIDADES CAPOEIRISTICAS");
        Graduacao grad3 = new Graduacao(null, "ADULTO", "CORDA AZUL", "CORDA DA COR AZUL", "SERIEDADE E MATURIDADE (REPRESENTAM OS SONHOS)", "ONDE O CAPOEIRISTA SE PREPARA PARA AS TRANSFORMAÇÕES NO SEU TRAJETO CAPOEIRISTICO");

        graduacaoRepository.saveAll(Arrays.asList(grad1, grad2, grad3));
        //(Long id, LocalDate dt_ocorrencia, String hr_ocorrencia, String responsavel, String nome_evento, String foto, String endereco, String bairro, String cidade, String estado, String observacao)
        EventoModel eventoModel = new EventoModel(null, LocalDate.now(), "15:00", "Ricardo", "Batizado novo", null, "Rua 123", "Centro", c1.getNome(), c1.getEstado().getNome(), "Chegar cedo");

        eventoService.salvar(eventoModel);

        ParametroConfiguracao par1 = new ParametroConfiguracao(null, "HOST", "localhost");
        parametroConfiguracaoService.insert(par1);


        //(Long id, String nome, String email, String senha, String apelido, boolean ativo, String cpfOuCnpj,
        // TipoCliente tipo, EstadoCivil estadoCivil, OrientacaoSexual orientacaoSexual)
        Aluno alu1 = new Aluno(null, "Riardo Rodrigues Alves", "ricardo.rodrigues.alves@hotmail.com", pe.encode("123"), "Ricardo", true, "3695310987474", TipoCliente.PESSOAFISICA,
                EstadoCivil.SOLTEIRO, OrientacaoSexual.MASCULINO);
        alu1.getTelefones().addAll(Arrays.asList("2357156598", "96852522542"));

        Aluno alu2 = new Aluno(null, "Riardo Rodrigues Alves", "riktodopoderoso@gmail.com", pe.encode("123"), "Ricardo", true, "3695310987474", TipoCliente.PESSOAFISICA,
                EstadoCivil.SOLTEIRO, OrientacaoSexual.MASCULINO);

        alu2.addPerfil(Perfil.ADMIN);



        //Integer id, String logradouro, String numero, String complemento, String bairro, String cep,
        //                    Aluno aluno, Cidade cidade
        EnderecoAluno endAlu1 = new EnderecoAluno(null, "Rua da Pera", "300", "Apto 201", "Jd Novo", "13220000", alu1, c3);
        alu1.getEnderecos().add(endAlu1);
        alu1.addPerfil(Perfil.ALUNO);


        alu1 = alunoService.insert(alu1);
        alunoService.insert(alu2);


        //Aluno aluno, BigDecimal vlMensalidade, LocalDate dtVencimento
        Financeiro f1 = new Financeiro(alu1, BigDecimal.valueOf(120.50), LocalDate.now().plusDays(10l));
        alu1.setFinanceiro(f1);

        financeiroService.inserir(f1);

        alu1 = alunoService.update(alu1);

        HistoricoFinanceiro hisF1 = new HistoricoFinanceiro(f1, alu1, LocalDate.now(), BigDecimal.valueOf(120.50), StatusFinanceiro.OK);
        historicoFinanceiroService.insert(hisF1);

        //(Aluno aluno, Aula aula, LocalDate dtOcorrencia, boolean ativo)
        HistoricoFrequencia hf1 = new HistoricoFrequencia(alu1, aula1, LocalDate.now(), true);
        HistoricoFrequencia hf2 = new HistoricoFrequencia(alu1, aula1, LocalDate.now(), false);

        historicoFrequenciaService.insert(hf1);
        historicoFrequenciaService.insert(hf2);

        HistoricoGraduacao hg1 = new HistoricoGraduacao(alu1, grad1, false, LocalDate.now().minusYears(2l));
        HistoricoGraduacao hg2 = new HistoricoGraduacao(alu1, grad2, false, LocalDate.now().minusYears(1l));
        HistoricoGraduacao hg3 = new HistoricoGraduacao(alu1, grad3, true, LocalDate.now());

        historicoGraduacaoService.insert(hg1);
        historicoGraduacaoService.insert(hg2);
        historicoGraduacaoService.insert(hg3);

    }

}
