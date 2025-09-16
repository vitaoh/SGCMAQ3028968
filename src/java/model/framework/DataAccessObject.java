package model.framework;

// importacao de classes
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import controller.AppConfig;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;

public abstract class DataAccessObject {

    private String tableEntity; // nome da tabela 
    private boolean novelEntity; // indica a existencia objeto no DB
    private boolean changedEntity; // indica se houve mudancas que precisam ser atualizadas
    private HashMap<String, Object> dirtyFields; // mapa que guarda os campos alterados e seus valores

    // inicializacao dos atributos 
    public DataAccessObject(String tableEntity) {
        setTableEntity(tableEntity);
        dirtyFields = new HashMap<>(); 

        setNovelEntity(true);
        setChangedEntity(false);
    }

    private String getTableEntity() {
        return tableEntity;
    }

    private boolean isNovelEntity() {
        return novelEntity;
    }

    private boolean isChangedEntity() {
        return changedEntity;
    }

    private void setTableEntity(String tableEntity) {
        if (tableEntity != null
                && !tableEntity.isEmpty() // validacoes para alteracao do DB
                && !tableEntity.isBlank()) {
            this.tableEntity = tableEntity;
        } else {
            throw new IllegalArgumentException("table must be valid");
        }   
    }

    // obriga a String ser valida
    protected void setNovelEntity(boolean novelEntity) {
        this.novelEntity = novelEntity;
    }

    // define se e novo o objeto
    protected void setChangedEntity(boolean changedEntity) {
        this.changedEntity = changedEntity;
        if (this.changedEntity == false) {
            dirtyFields.clear();
        }
    }

    // Unity Of Work
    protected void addChange(String field, Object value) {
        dirtyFields.put(field, value);
        setChangedEntity(true);
    }

    // gera query do Insert com placeholders
    private void insert() throws SQLException {

        String dml = "INSERT INTO " + getTableEntity();

        StringJoiner fields = new StringJoiner(",");
        StringJoiner values = new StringJoiner(",");

        for (String field : dirtyFields.keySet()) {
            fields.add(field);
            values.add("?");
        }

        dml += " (" + fields + ") VALUES (" + values + ")";

        if (AppConfig.getInstance().isVerbose()) {
            System.out.println(dml);
        }

        // cria o PreparedStatement
        Connection con = DataBaseConnections.getInstance().getConnection();
        PreparedStatement pst = con.prepareStatement(dml);

        // preenche pst com valores do dirtyFields
        int index = 1;
        for (String field : dirtyFields.keySet()) {
            pst.setObject(index, dirtyFields.get(field));
            index++;
        }

        if (AppConfig.getInstance().isVerbose()) {
            System.out.println(pst);
        }

        // executa e fecha conexao
        pst.execute(); 
        pst.close();
        DataBaseConnections.getInstance().closeConnection(con);

    }

    // cria SQL de UPDATE, com WHERE definido por um método abstrato (implementado pela subclasse)
    private void update() throws SQLException {

        String dml = "UPDATE " + getTableEntity() + " SET ";

        StringJoiner changes = new StringJoiner(",");

        for (String field : dirtyFields.keySet()) {
            changes.add(field + "=?");
        }

        dml += changes + " WHERE " + getWhereClauseForOneEntity();

        if (AppConfig.getInstance().isVerbose()) {
            System.out.println(dml);
        }

        // inicializa
        Connection con = DataBaseConnections.getInstance().getConnection();
        PreparedStatement pst = con.prepareStatement(dml);

        // monta
        int index = 1;
        for (String field : dirtyFields.keySet()) {
            pst.setObject(index, dirtyFields.get(field));
            index++;
        }

        if (AppConfig.getInstance().isVerbose()) {
            System.out.println(pst);
        }
        
        // executa 
        pst.execute();
        pst.close(); // fecha

        DataBaseConnections.getInstance().closeConnection(con);

    }


    // metodo principal: persiste no banco (faz insert se for novo, update se ja existir) e depois marca como atualizado
    public void save() throws SQLException {
        if (isChangedEntity()) {

            // salvo
            if (isNovelEntity()) {
                insert();
                setNovelEntity(false);
            } else {
                update();
            }

            setChangedEntity(false);
        }
    }

    // deleta a entidade com base no getWhereClauseForOneEntity()
    public void delete() throws SQLException {

        String dml = "DELETE FROM " + getTableEntity() + " WHERE " + getWhereClauseForOneEntity();

        if (AppConfig.getInstance().isVerbose()) {
            System.out.println(dml);
        }

        Connection con = DataBaseConnections.getInstance().getConnection();
        Statement st = con.createStatement();

        st.execute(dml);
        st.close();

        DataBaseConnections.getInstance().closeConnection(con);

    }

    // select / rs.next() cria lista de objetos e chama fill(data) (metodo abstrato da subclasse que popula os campos da entidade)
    public boolean load() throws SQLException {
        boolean result;

        String dql = "SELECT * FROM " + getTableEntity() + " WHERE " + getWhereClauseForOneEntity();

        if (AppConfig.getInstance().isVerbose()) {
            System.out.println(dql);
        }

        Connection con = DataBaseConnections.getInstance().getConnection();

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(dql);

        result = rs.next();

        if (result) {

            ArrayList<Object> data = new ArrayList();

            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                data.add(rs.getObject(i));
            }

            fill(data);
            setNovelEntity(false);
        }

        return result;
    }

    // para cada linha, preenche com fill e adiciona uma copia no resultado
    public <T extends DataAccessObject> ArrayList<T> getAllTableEntities() throws SQLException {

        ArrayList<T> result = new ArrayList<>();

        String dql = "SELECT * FROM " + getTableEntity();

        if (AppConfig.getInstance().isVerbose()) {
            System.out.println(dql);
        }

        Connection con = DataBaseConnections.getInstance().getConnection();

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(dql);

        while (rs.next()) {

            ArrayList<Object> data = new ArrayList<>();

            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                data.add(rs.getObject(i));
            }

            result.add(fill(data).copy());
        }

        st.close();

        // fecha a conexao com o DataBase
        DataBaseConnections.getInstance().closeConnection(con);

        return result;

    }

    // padrão Template Method
    protected abstract String getWhereClauseForOneEntity();

    protected abstract DataAccessObject fill(ArrayList<Object> data);

    protected abstract <T extends DataAccessObject> T copy();

}
