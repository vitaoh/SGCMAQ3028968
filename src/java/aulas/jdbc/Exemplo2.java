package aulas.jdbc;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Exemplo2 {

    public static void main(String[] args) throws SQLException {

        final String URL = "jdbc:mysql://localhost:3307/sgcm_bd"; // ou 3306 para porta usada em CASA
        final String USER = "root";
        final String PASSWORD = "root";

        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

        System.out.println(con);

        Statement st = con.createStatement();

        String dql = "SELECT * FROM tipo_usuario";

        ResultSet rs = st.executeQuery(dql);
        ResultSetMetaData rsm = rs.getMetaData();

        // 1 to <=
        for (int i = 1; i <= rsm.getColumnCount(); i++) {
            System.out.print(rsm.getColumnLabel(i) + " ");
        }
        System.out.println("\n");

        while (rs.next()) {
            for (int i = 1; i <= rsm.getColumnCount(); i++) {
                System.out.print(rs.getObject(i) + " ");
            }
            System.out.print("\n");
        }

        rs.close();

        st.close();

        con.close();
    }
}
