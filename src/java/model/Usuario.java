package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import model.framework.DataAccessObject;

public class Usuario extends DataAccessObject {

    // Atributos que fazem relação das colunas da tabela 'usuarios' no banco
    private int id; // primary key - id do usuario
    private String nome;
    private String nascimento;
    private String cpf;
    private String senha;
    private String endereco;
    private int tipoUsuarioId; // chave estrangeira para tipo_usuario
    private int convenioId; // chave estrangeira para convenios

    public Usuario() {
        super("usuarios"); // nome da tabela
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getSenha() {
        return senha;
    }

    public int getTipoUsuarioId() {
        return tipoUsuarioId;
    }

    public int getConvenioId() {
        return convenioId;
    }

    // Setters, para alterar os atributos e com o addChange() ja muda no dirtyFields
    public void setId(int id) {
        this.id = id;
        addChange("id", this.id);
    }

    public void setNome(String nome) {
        this.nome = nome;
        addChange("nome", this.nome);
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
        addChange("nascimento", this.nascimento);
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
        addChange("cpf", this.cpf);
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
        addChange("endereco", this.endereco);
    }

    public void setSenha(String senha) throws Exception {
        if (senha == null) {
            if (this.senha != null) {
                this.senha = senha;
                addChange("senha", this.senha);
            }
        } else {
            if (senha.equals(this.senha) == false) {
                String senhaSal = getId() + senha + getId() / 2; // para "criptocrafar"
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                String hash = new BigInteger(1, md.digest(senhaSal.getBytes("UTF-8"))).toString(16);

                this.senha = hash;
                addChange("senha", this.senha);
            }
        }
    }

    public void setTipoUsuarioId(int tipoUsuarioId) {
        if (this.tipoUsuarioId != tipoUsuarioId) {
            this.tipoUsuarioId = tipoUsuarioId;
            addChange("tipo_usuario_id", this.tipoUsuarioId);
        }
    }

    public void setConvenioId(int convenioId) {
        this.convenioId = convenioId;
        if( this.convenioId == 0 ) { 
            addChange("convenios_id", null);
        } else {
            addChange("convenios_id", this.convenioId);
        }
    }


    @Override
    protected String getWhereClauseForOneEntity() {
        return " id = " + getId();
    }

    // preenche o objeto com os dados do banco
    @Override
    protected DataAccessObject fill(ArrayList<Object> data) {
        // segue a ordem das colunas da tabela usuarios (tem que ver certinho se esta desta forma)
        id = (int) data.get(0); // coluna 1: ID
        nome = (String) data.get(1); // coluna 2: Nome
        if (data.get(2) != null) {
            nascimento = data.get(2).toString(); // coluna 3: Nascimento
        } else {
            nascimento = null;
        }
        cpf = (String) data.get(3); // coluna 4: CPF
        senha = (String) data.get(4); // coluna 5: Senha
        endereco = (String) data.get(5); // coluna 6: Endereço
        tipoUsuarioId = (int) data.get(6); // coluna 7: TipoUsuario
        if (data.get(7) != null) {
            convenioId = (int) data.get(7); // coluna 8: Convenios
        } else {
            convenioId = 0;
        }

        return this;
    }

    @Override
    protected Usuario copy() {
        Usuario cp = new Usuario();

        cp.setId(getId());
        cp.setNome(getNome());
        cp.setNascimento(getNascimento());
        cp.senha = getSenha();
        cp.setCpf(getCpf());
        cp.setEndereco(getEndereco());
        cp.setTipoUsuarioId(getTipoUsuarioId());
        cp.setNascimento(getNascimento());
        cp.setConvenioId(getConvenioId());

        cp.setNovelEntity(false); // copiou um existente

        return cp;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Usuario) {
            Usuario aux = (Usuario) obj;
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
        return "(" + getId() + ", " + getNome() + ", " + getNascimento() + ", " + getCpf() + ", " + getSenha() + getEndereco() + ", tipoUsuarioId=" + getTipoUsuarioId() + ", ConvenioId=" + getConvenioId() + ")";
    }
}
