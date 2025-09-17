package model;

import java.util.ArrayList;
import model.framework.DataAccessObject;

public class Usuario extends DataAccessObject {

    // Identificador único do usuário (chave primária da tabela)
    private int id;

    // Nome completo do usuário
    private String nome;

    // Endereço de email do usuário
    private String email;

    // Senha do usuário
    private String senha;

    // Chave estrangeira para a tabela tipo_usuario
    private int tipoUsuarioId;

    // Construtor padrão: inicializa DAO informando a tabela mapeada
    public Usuario() {
        super("sgcm_bd.usuarios");
    }

    // Getters e setters, cada setter registra mudança no DAO (addChange)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        addChange("id", this.id); // registra alteração para persistência automatizada
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        addChange("nome", this.nome);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        addChange("email", this.email);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
        addChange("senha", this.senha);
    }

    public int getTipoUsuarioId() {
        return tipoUsuarioId;
    }

    public void setTipoUsuarioId(int tipoUsuarioId) {
        this.tipoUsuarioId = tipoUsuarioId;
        addChange("tipo_usuario_id", this.tipoUsuarioId);
    }

    // Retorna cláusula WHERE para identificar registro único
    @Override
    protected String getWhereClauseForOneEntity() {
        return "id = " + this.id;
    }

    // Preenche campos do objeto a partir dos dados do banco, na ordem das colunas
    // Permite reuso em fill(data) e cópia em copy()
    @Override
    protected DataAccessObject fill(ArrayList data) {
        // Atenção à ordem das colunas!
        this.id = (int) data.get(0);
        this.nome = (String) data.get(1);
        this.email = (String) data.get(2);
        this.senha = (String) data.get(3);
        this.tipoUsuarioId = (int) data.get(4);
        return this;
    }

    // Cria nova instância de Usuario com os mesmos dados, marcando-a como já existente
    // Utilizado em getAllTableEntities e para garantir integridade nos objetos retornados
    @Override
    protected Usuario copy() {
        Usuario novo = new Usuario();
        novo.setId(this.id);
        novo.setNome(this.nome);
        novo.setEmail(this.email);
        novo.setSenha(this.senha);
        novo.setTipoUsuarioId(this.tipoUsuarioId);
        novo.setNovelEntity(false);
        return novo;
    }

    // Representação textual do objeto Usuario, útil para depuração e logs
    @Override
    public String toString() {
        return "(" + id + ", " + nome + ", " + email + ", " + senha + ", " + tipoUsuarioId + ")";
    }

}
