package br.com.bonfim.capoeira.model.cadastro.tipoEnum;

public enum StatusFinanceiro {
    OK(1, "OK"),
    CANCELADO(2, "CANCELADO"),
    PENDENTE(3, "PENDENTE"),
    CORTESIA(4, "CORTESIA");

    private int cod;
    private String descricao;

    private StatusFinanceiro(int cod, String descricao){
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod(){
        return cod;
    }
    public String getDescricao(){
        return descricao;
    }

    public static StatusFinanceiro toEnum(Integer cod){
        if (cod == null){
            return null;
        }
        for (StatusFinanceiro x : StatusFinanceiro.values()){
            if (cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id invalido: " + cod);
    }
}
