package model;

import java.util.ArrayList;
import model.framework.DataAccessObject;

public class Usuario extends DataAccessObject {

    // Identificador único do usuário (chave primária da tabela)
    private int id;

    // Nome completo do usuário
    private String name;

    // Cpf do usuário
    private String cpf;

    // Senha do usuário
    private String password;

    // Chave estrangeira para a tabela tipo_usuario
    private int typeUserId;

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

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
        addChange("nome", this.name);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
        addChange("cpf", this.cpf);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        addChange("senha", this.password);
    }

    public int getTypeUserId() {
        return typeUserId;
    }

    public void setTypeUserId(int typeUserId) {
        this.typeUserId = typeUserId;
        addChange("tipo_usuario_id", this.typeUserId);
    }

    // Retorna cláusula WHERE para identificar registro único
    @Override
    protected String getWhereClauseForOneEntity() {
        return "id = " + this.id;
    }

    // Preenche campos do objeto a partir dos dados do banco, na ordem das colunas
    // Permite reuso em fill(data) e cópia em copy()
    @Override
    protected DataAccessObject fill(ArrayList<Object> data) {
        // Atenção à ordem das colunas!
        this.id = (int) data.get(0);
        this.name = (String) data.get(1);
        this.cpf = (String) data.get(2);
        this.password = (String) data.get(3);
        this.typeUserId = (int) data.get(4);
        return this;
    }

    // Cria nova instância de Usuario com os mesmos dados, marcando-a como já existente
    // Utilizado em getAllTableEntities e para garantir integridade nos objetos retornados
    @Override
    protected Usuario copy() {
        Usuario cp = new Usuario();
        cp.setId(getId());
        cp.setName(getName());
        cp.setCpf(getCpf());
        cp.setPassword(getPassword());
        cp.setTypeUserId(getTypeUserId());
        cp.setNovelEntity(false);
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

    // Representação textual do objeto Usuario, útil para depuração e logs
    @Override
    public String toString() {
        return "Usuario(" + "Id: " + id + ", Nome: " + name + ", CPF: " + cpf + ", Senha: " + password + ", Tipo Usuario: " + typeUserId + ")";
    }

}
