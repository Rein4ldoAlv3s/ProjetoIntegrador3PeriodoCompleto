package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    private static Connection conexao = null;
    
    public static Connection getConexao(){

        try
        {
               /*
                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost:3306/usuario";
                String user = "root";
                String password = "";
                */
                String driver = "org.postgresql.Driver";
                String url = "jdbc:postgresql://localhost:5432/projeto_integrador";
                String user = "postgres";
                String password = "admin";
                  
                Class.forName(driver);
                conexao = DriverManager.getConnection(url, user, password);
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        return conexao;
        
    }
}
