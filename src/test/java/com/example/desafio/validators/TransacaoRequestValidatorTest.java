package com.example.desafio.validators;

import com.example.desafio.dto.request.DescricaoRequestDTO;
import com.example.desafio.dto.request.FormaPagamentoRequestDTO;
import com.example.desafio.dto.request.TransacaoDTO;
import com.example.desafio.dto.request.TransacaoRequestDTO;
import com.example.desafio.model.TipoFormaPagamento;
import com.example.desafio.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Dado um validador de requisição de transação")
class TransacaoRequestValidatorTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    private TransacaoRepository transacaoRepository;

    private TransacaoRequestValidator criaValidator(){
        return new TransacaoRequestValidator(
                transacaoRepository
        );
    }

    @Test
    @DisplayName("Quando os dados são válidos, então a validação é bem sucedida")
    void quandoDadosValidos_entaoValidacaoSucesso() {
        TransacaoRequestDTO dto = new TransacaoRequestDTO();

        TransacaoDTO transacao = new TransacaoDTO();
        transacao.setId("123");
        transacao.setCartao("1234");

        DescricaoRequestDTO descricao = new DescricaoRequestDTO();
        descricao.setValor("100.00");
        descricao.setDataHora("01/05/2021 18:30:00");
        descricao.setEstabelecimento("Estabelecimento");
        transacao.setDescricao(descricao);

        FormaPagamentoRequestDTO formaPagamento = new FormaPagamentoRequestDTO();
        formaPagamento.setTipo(TipoFormaPagamento.AVISTA);
        formaPagamento.setParcelas("1");
        transacao.setFormaPagamento(formaPagamento);

        dto.setTransacao(transacao);

        TransacaoRequestValidator validator = criaValidator();
        validator.setTransacao(dto);

        String validacao = validator.validate();

        assertTrue(validacao.isEmpty());
    }

    @Test
    @DisplayName("Quando os dados são inválidos, então a validação falha")
    void quandoDadosInvalidos_entaoValidacaoErro() {
        TransacaoRequestValidator validator = criaValidator();

        TransacaoRequestDTO dto = new TransacaoRequestDTO();
        validator.setTransacao(dto);
        assertEquals("Transação não pode ser nula",validator.validate());

        TransacaoDTO transacao = new TransacaoDTO();
        dto.setTransacao(transacao);
        assertEquals("Verificar id da transação",validator.validate());

        transacao.setId("123");
        dto.setTransacao(transacao);
        assertEquals("Verificar cartão da transação",validator.validate());

        transacao.setCartao("1234");
        dto.setTransacao(transacao);
        assertEquals("Verificar descrição da transação",validator.validate());

        DescricaoRequestDTO descricao = new DescricaoRequestDTO();
        transacao.setDescricao(descricao);
        dto.setTransacao(transacao);
        assertEquals("Verificar valor da transação",validator.validate());

        descricao.setValor("100.00");

        transacao.setDescricao(descricao);
        dto.setTransacao(transacao);
        assertEquals("Verificar data e hora da transação",validator.validate());

        descricao.setDataHora("01/05/2021 18:30:00");
        transacao.setDescricao(descricao);
        dto.setTransacao(transacao);
        assertEquals("Verificar estabelecimento da transação",validator.validate());

        descricao.setEstabelecimento("Estabelecimento");
        transacao.setDescricao(descricao);
        dto.setTransacao(transacao);
        assertEquals("Verificar forma de pagamento da transação",validator.validate());

        FormaPagamentoRequestDTO formaPagamento = new FormaPagamentoRequestDTO();
        transacao.setFormaPagamento(formaPagamento);
        transacao.setDescricao(descricao);
        dto.setTransacao(transacao);
        assertEquals("Verificar parcelas da transação",validator.validate());

        formaPagamento.setParcelas("1");
        transacao.setFormaPagamento(formaPagamento);
        transacao.setDescricao(descricao);
        dto.setTransacao(transacao);
        assertEquals("Verificar tipo da forma de pagamento",validator.validate());

        formaPagamento.setTipo(TipoFormaPagamento.AVISTA);
        transacao.setFormaPagamento(formaPagamento);
        transacao.setDescricao(descricao);
        dto.setTransacao(transacao);

        assertTrue(validator.validate().isEmpty());
    }
    private TransacaoRequestDTO criaDTOComDescricao(DescricaoRequestDTO descricao){
        TransacaoRequestDTO dto = new TransacaoRequestDTO();

        TransacaoDTO transacao = new TransacaoDTO();
        transacao.setId("123");
        transacao.setCartao("1234");

        descricao.setValor("100.00");
        descricao.setDataHora("01/05/2021 18:30:00");
        descricao.setEstabelecimento("Estabelecimento");
        transacao.setDescricao(descricao);

        FormaPagamentoRequestDTO formaPagamento = new FormaPagamentoRequestDTO();
        formaPagamento.setTipo(TipoFormaPagamento.AVISTA);
        formaPagamento.setParcelas("1");
        transacao.setFormaPagamento(formaPagamento);

        dto.setTransacao(transacao);

        return dto;
    }

    @Test
    @DisplayName("Quando a dataHora é invalida, então a validação falha")
    void quandoDataHoraInvalida_entaoValidacaoErro() {
        TransacaoRequestValidator validator = criaValidator();
        DescricaoRequestDTO descricao = new DescricaoRequestDTO();
        TransacaoRequestDTO dto = criaDTOComDescricao(descricao);

        descricao.setDataHora("18:30:00");

        validator.setTransacao(dto);

        assertEquals("Data e hora inválidas", validator.validate());

    }
    @Test
    @DisplayName("Quando o valor é invalido, então a validação falha")
    void quandoValorMenorIgualAZero_entaoValidacaoErro() {
        TransacaoRequestValidator validator = criaValidator();
        DescricaoRequestDTO descricao = new DescricaoRequestDTO();
        TransacaoRequestDTO dto = criaDTOComDescricao(descricao);

        descricao.setValor("-100.00");

        validator.setTransacao(dto);

        assertEquals("Valor inválido", validator.validate());

        descricao.setValor("0.00");

        validator.setTransacao(dto);

        assertEquals("Valor inválido", validator.validate());

        descricao.setValor("ABC");

        validator.setTransacao(dto);

        assertEquals("Valor inválido", validator.validate());
        descricao.setValor("0.01");

        validator.setTransacao(dto);

        assertTrue(validator.validate().isEmpty());
    }
    @Test
    @DisplayName("Quando o estabelecimento é invalido, então a validação falha")
    void quandoEstabelecimentoInvalido_entaoValidacaoErro() {
        TransacaoRequestValidator validator = criaValidator();
        DescricaoRequestDTO descricao = new DescricaoRequestDTO();
        TransacaoRequestDTO dto = criaDTOComDescricao(descricao);

        descricao.setEstabelecimento("");

        validator.setTransacao(dto);

        assertEquals("Verificar estabelecimento da transação", validator.validate());

        descricao.setEstabelecimento(null);

        validator.setTransacao(dto);

        assertEquals("Verificar estabelecimento da transação", validator.validate());

        descricao.setEstabelecimento("ABC");

        validator.setTransacao(dto);

        assertTrue(validator.validate().isEmpty());
    }

    private TransacaoRequestDTO criaDTOComFormaPagamento(FormaPagamentoRequestDTO formaPagamento){
        TransacaoRequestDTO dto = new TransacaoRequestDTO();

        TransacaoDTO transacao = new TransacaoDTO();
        transacao.setId("123");
        transacao.setCartao("1234");

        DescricaoRequestDTO descricao = new DescricaoRequestDTO();
        descricao.setValor("100.00");
        descricao.setDataHora("01/05/2021 18:30:00");
        descricao.setEstabelecimento("Estabelecimento");
        transacao.setDescricao(descricao);

        formaPagamento.setTipo(TipoFormaPagamento.AVISTA);
        formaPagamento.setParcelas("1");
        transacao.setFormaPagamento(formaPagamento);

        dto.setTransacao(transacao);

        return dto;
    }

    @Test
    @DisplayName("Quando a parcela é invalida, então a validação falha")
    void quandoParcelaMenorIgualAZero_entaoValidacaoErro() {
        TransacaoRequestValidator validator = criaValidator();
        FormaPagamentoRequestDTO formaPagamento = new FormaPagamentoRequestDTO();
        TransacaoRequestDTO dto = criaDTOComFormaPagamento(formaPagamento);

        formaPagamento.setParcelas("-1");

        validator.setTransacao(dto);

        assertEquals("Parcelas inválidas", validator.validate());

        formaPagamento.setParcelas("0");

        validator.setTransacao(dto);

        assertEquals("Parcelas inválidas", validator.validate());

        formaPagamento.setParcelas("ABC");

        validator.setTransacao(dto);

        assertEquals("Parcelas inválidas", validator.validate());
        formaPagamento.setParcelas("1");

        validator.setTransacao(dto);

        assertTrue(validator.validate().isEmpty());
    }

    @Test
    @DisplayName("Quando o tipo de forma de pagamento é invalido, então a validação falha")
    void quandoTipoFormaPagamentoInvalido_entaoValidacaoErro() {
        TransacaoRequestValidator validator = criaValidator();
        FormaPagamentoRequestDTO formaPagamento = new FormaPagamentoRequestDTO();
        TransacaoRequestDTO dto = criaDTOComFormaPagamento(formaPagamento);

        formaPagamento.setTipo(null);

        validator.setTransacao(dto);

        assertEquals("Verificar tipo da forma de pagamento", validator.validate());

        formaPagamento.setTipo(TipoFormaPagamento.AVISTA);

        validator.setTransacao(dto);

        assertTrue(validator.validate().isEmpty());

        formaPagamento.setTipo(TipoFormaPagamento.PARCELADO_EMISSOR);

        validator.setTransacao(dto);

        assertTrue(validator.validate().isEmpty());

        formaPagamento.setTipo(TipoFormaPagamento.PARCELADO_LOJA);

        validator.setTransacao(dto);

        assertTrue(validator.validate().isEmpty());
    }

}