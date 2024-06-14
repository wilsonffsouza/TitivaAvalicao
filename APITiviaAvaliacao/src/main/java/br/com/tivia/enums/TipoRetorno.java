package br.com.tivia.enums;

/**
 *
 * @author Wilson.Souza
 */
public enum TipoRetorno {
    
    SOCESSO(200, "Operacao realizada com sucesso"),
    FALHA_CONEXAO_BANCO (-10, "Falha na conexão com o Banco de Dados!"),
    FALHA_PERSISTENCIA (-11, "Falha ao inserir no Banco de Dados"),
    FALHA_DESCONHECIDA (-255, "Falha Desconecida"),
    PARAMETRO_INVALIDO( -204, "Parâmetro Inválido!."),
    SEM_DADOS_RETORNO ( -293, "Não existe dados para os parâmetros informados.");
    
    private final int codigo;
    private final String mensagem;

    public int getCodigo() {
        return codigo;
    }

    public String getMensagem() {
        return mensagem;
    }
    
    private TipoRetorno(int codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }
    
    public static TipoRetorno getForId(int id) {
        for (TipoRetorno elem : TipoRetorno.values()) {
            if (elem.getCodigo() == id) {
                return elem;
            }
        }
        return FALHA_DESCONHECIDA;
    }
    
}
