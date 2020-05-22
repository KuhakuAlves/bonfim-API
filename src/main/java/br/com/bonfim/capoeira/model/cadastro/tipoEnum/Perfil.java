package br.com.bonfim.capoeira.model.cadastro.tipoEnum;

public enum Perfil {
    ADMIN(1, "ROLE_ADMIN"),
    ALUNO(2, "ROLE_ALUNO"),
    PROFESSOR(2, "ROLE_PROFESSOR");

    private int cod;
    private String descricao;

    private Perfil(int cod, String descricao){
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod(){
        return cod;
    }
    public String getDescricao(){
        return descricao;
    }

    public static Perfil toEnum(Integer cod){
        if (cod == null){
            return null;
        }
        for (Perfil x : Perfil.values()){
            if (cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id invalido: " + cod);
    }
}
