package br.fagoc.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thiago Sartori
 */
public class ConnectionFactory {

    private Connection cnx;

    public void GeraConexao() {
        this.cnx = null;
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            String URL = "jdbc:firebirdsql:localhost/3050:c:/db/banco.FDB?lc_ctype=WIN1252";
            //String URL = "jdbc:mysql://localhost/banco";
            String user = "sysdba";
            String password = "masterkey";

            this.cnx = DriverManager.getConnection(URL, user, password);

        } catch (ClassNotFoundException e) {
            System.out.println("Ocorreu um erro na SQL de conexão: " + e.getMessage());
            this.cnx = null;

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro na SQL de conexão: " + e.getMessage());
            this.cnx = null;
        }

    }

    // ***** Padrão Singleton INICIO http://wmagician.wordpress.com/2008/01/02/padrao-singleton-em-java/
    private static ConnectionFactory FabricaCnx;

    public static ConnectionFactory getInstance() {

        try {

            if (FabricaCnx == null) {
                FabricaCnx = new ConnectionFactory();
            }
            if (FabricaCnx.getConexao() == null) {
                FabricaCnx.GeraConexao();
            }
            if (FabricaCnx.cnx.isClosed()) {
                FabricaCnx.GeraConexao();
            }
        } catch (SQLException e) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
        }

        return FabricaCnx;
    }

    // ***** Padrão Singleton FIM
    public Connection getConexao() {
        return cnx;
    }
}
