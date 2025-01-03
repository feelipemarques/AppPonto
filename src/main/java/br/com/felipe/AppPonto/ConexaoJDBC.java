package br.com.felipe.AppPonto;


import java.sql.*;

public class ConexaoJDBC {

    public class DatabaseHelper {
        private static final String URL = "jdbc:mysql://localhost:3306/pontos";
        private static final String USER = "root";
        private static final String PASSWORD = "";

        public static Connection connect() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }

    public void registra(String funcional, String data, String horaRegistrada, boolean entrada) {

        String sql = "INSERT INTO PONTO (funcional, dia, hora_entrada) VALUES (?, ?, ?)";

        if (!entrada) {
            sql = "UPDATE ponto SET hora_saida = ? WHERE funcional = ?";
            try (Connection conn = DatabaseHelper.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, horaRegistrada);
                stmt.setString(2, funcional);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Ponto registrado com sucesso!");
                } else {
                    System.out.println("Erro ao registrar ponto.");
                }


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try (Connection conn = DatabaseHelper.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, funcional);
                stmt.setString(2, data);
                stmt.setString(3, horaRegistrada);

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Ponto registrado com sucesso!");
                } else {
                    System.out.println("Erro ao registrar ponto.");
                }


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
    }
//

    /*public void consulta(){
        String sql = "SELECT * FROM PONTO";

        try(Connection conn = DatabaseHelper.connect()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                String funcional = rs.getString("funcional");
                String dia = rs.getString("dia");
                String horaEntrada = rs.getString("hora_entrada");

                // Exibindo os dados
                System.out.println("Funcional: " + funcional + ", Dia: " + dia +
                        ", Hora Entrada: " + horaEntrada);
            }
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage() + e.getCause());
        }
    }*/ // VOID DE CONSULTA


}
