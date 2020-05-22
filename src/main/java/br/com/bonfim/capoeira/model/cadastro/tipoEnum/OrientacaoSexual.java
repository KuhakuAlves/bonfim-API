package br.com.bonfim.capoeira.model.cadastro.tipoEnum;

public enum OrientacaoSexual {
    MASCULINO(1, "Masculino"),
    FEMININO(2, "Feminino"),
    OUTROS(3, "Outros");

    private int cod;
    private String descricao;

    private OrientacaoSexual(int cod, String descricao){
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static OrientacaoSexual toEnum(Integer cod){
        if (cod == null){
            return null;
        }
        for(OrientacaoSexual x: OrientacaoSexual.values()){
            if (cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id invalido: " + cod);

    }
}
