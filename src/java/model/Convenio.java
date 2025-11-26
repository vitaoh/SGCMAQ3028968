package model;

import java.util.ArrayList;
import model.framework.DataAccessObject;

public class Convenio extends DataAccessObject {

    private int id;
    private String nome;
    private String cnpj;
    private String telefone;
    private float valor;

    public Convenio() {
        super("convenios");
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public Float getValor() {
        return valor;
    }

    public void setId(int id) {
        this.id = id;
        addChange("id", this.id);
    }

    public void setNome(String nome) {
        this.nome = nome;
        addChange("nome", this.nome);
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
        addChange("cnpj", this.cnpj);
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
        addChange("telefone", this.telefone);
    }

    public void setValor(Float valor) {
        this.valor = valor;
        addChange("valor", this.valor);
    }

    @Override
    protected String getWhereClauseForOneEntity() {
        return " id = " + getId();
    }

    @Override
    protected DataAccessObject fill(ArrayList<Object> data) {
        // segue a ordem das colunas da tabela no banco de dados
        id = (int) data.get(0);
        nome = (String) data.get(1);
        cnpj = (String) data.get(2);
        telefone = (String) data.get(3);
        valor = (float) data.get(4);
        return this;
    }

    @Override
    protected Convenio copy() {
        Convenio cp = new Convenio();

        cp.setId(getId());
        cp.setNome(getNome());
        cp.setCnpj(getCnpj());
        cp.setTelefone(getTelefone());
        cp.setValor(getValor());

        cp.setNovelEntity(false);

        return cp;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Convenio) {
            Convenio aux = (Convenio) obj;
            if (getId() == aux.getId()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "(" + getId() + ", " + getNome() + ", " + getCnpj() + ", " + getTelefone() + ", " + getValor() + ")";
    }

}
