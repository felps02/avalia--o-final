import java.sql.*;
import java.util.Scanner;

public class Main {

    // Configurações do banco de dados
    final static String URL = "jdbc:mysql://localhost:3306/eventos_db";
    final static String USER = "root";
    final static String PASSWORD = "";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        int menu = 0;
        do {
            System.out.println("Digite a opção desejada: ");
            System.out.println("1. Listar Locais");
            System.out.println("2. Listar Eventos");
            System.out.println("3. Criar Local");
            System.out.println("4. Criar Evento");
            System.out.println("5. Atualizar Local");
            System.out.println("6. Atualizar Evento");
            System.out.println("7. Excluir Local");
            System.out.println("8. Excluir Evento");
            System.out.println("9. Listar Pessoas");
            System.out.println("10. Criar Pessoa");
            System.out.println("11. Listar Organizadores");
            System.out.println("12. Criar Organizador");
            System.out.println("13. Listar Participantes");
            System.out.println("14. Criar Participante");
            System.out.println("0. Sair");

            try {
                menu = scanner.nextInt();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            switch (menu) {
                case 1:
                    listarLocais();
                    break;
                case 2:
                    listarEventos();
                    break;
                case 3:
                    criarLocal();
                    break;
                case 4:
                    criarEvento();
                    break;
                case 5:
                    atualizarLocal();
                    break;
                case 6:
                    atualizarEvento();
                    break;
                case 7:
                    excluirLocal();
                    break;
                case 8:
                    excluirEvento();
                    break;
                case 9:
                    listarPessoas();
                    break;
                case 10:
                    criarPessoa();
                    break;
                case 11:
                    listarOrganizadores();
                    break;
                case 12:
                    criarOrganizador();
                    break;
                case 13:
                    listarParticipantes();
                    break;
                case 14:
                    criarParticipante();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (menu != 0);
        scanner.close();
    }

    // Listar Locais
    private static void listarLocais() {
        String query = "SELECT * FROM local";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stm = con.prepareStatement(query);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                int vagas = rs.getInt("vagas");

                System.out.println("ID: " + id + ", Descrição: " + descricao + ", Vagas: " + vagas);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Listar Eventos
    private static void listarEventos() {
        String query = "SELECT * FROM evento";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stm = con.prepareStatement(query);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int idLocal = rs.getInt("idLocal");
                String descricao = rs.getString("descricao");
                Timestamp data = rs.getTimestamp("data");
                int vagas = rs.getInt("vagas");

                System.out.println("ID: " + id + ", ID Local: " + idLocal + ", Descrição: " + descricao + ", Data: " + data + ", Vagas: " + vagas);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Criar Local
    private static void criarLocal() {
        System.out.println("Informe a descrição do local: ");
        String descricao = scanner.next();
        System.out.println("Informe o número de vagas: ");
        int vagas = scanner.nextInt();

        String query = "INSERT INTO local (descricao, vagas) VALUES (?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stm = con.prepareStatement(query)) {

            stm.setString(1, descricao);
            stm.setInt(2, vagas);

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Local criado com sucesso!");
            } else {
                System.out.println("Falha ao criar local.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Criar Evento
    private static void criarEvento() {
        System.out.println("Informe o ID do local: ");
        int idLocal = scanner.nextInt();
        System.out.println("Informe a descrição do evento: ");
        String descricao = scanner.next();
        System.out.println("Informe a data do evento (yyyy-mm-dd): ");
        String data = scanner.next();
        System.out.println("Informe o número de vagas: ");
        int vagas = scanner.nextInt();

        String query = "INSERT INTO evento (id_local, descricao, data, vagas) VALUES (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stm = con.prepareStatement(query)) {

            stm.setInt(1, idLocal);
            stm.setString(2, descricao);
            stm.setDate(3, Date.valueOf(data));
            stm.setInt(4, vagas);

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Evento criado com sucesso!");
            } else {
                System.out.println("Falha ao criar evento.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Atualizar Local
    private static void atualizarLocal() {
        System.out.println("Informe o ID do local a ser atualizado: ");
        int id = scanner.nextInt();
        System.out.println("Informe a nova descrição do local: ");
        String descricao = scanner.next();
        System.out.println("Informe o novo número de vagas: ");
        int vagas = scanner.nextInt();

        String query = "UPDATE local SET descricao = ?, vagas = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stm = con.prepareStatement(query)) {

            stm.setString(1, descricao);
            stm.setInt(2, vagas);
            stm.setInt(3, id);

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Local atualizado com sucesso!");
            } else {
                System.out.println("Falha ao atualizar local.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Atualizar Evento
    private static void atualizarEvento() {
        System.out.println("Informe o ID do evento a ser atualizado: ");
        int id = scanner.nextInt();
        System.out.println("Informe a nova descrição do evento: ");
        String descricao = scanner.next();
        System.out.println("Informe a nova data do evento (yyyy-mm-dd hh:mm:ss): ");
        String data = scanner.next();
        System.out.println("Informe o novo número de vagas: ");
        int vagas = scanner.nextInt();

        String query = "UPDATE evento SET descricao = ?, data = ?, vagas = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stm = con.prepareStatement(query)) {

            stm.setString(1, descricao);
            stm.setTimestamp(2, Timestamp.valueOf(data));
            stm.setInt(3, vagas);
            stm.setInt(4, id);

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Evento atualizado com sucesso!");
            } else {
                System.out.println("Falha ao atualizar evento.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Excluir Local
    private static void excluirLocal() {
        System.out.println("Informe o ID do local a ser excluído: ");
        int id = scanner.nextInt();

        String query = "DELETE FROM local WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stm = con.prepareStatement(query)) {

            stm.setInt(1, id);
            int rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Local excluído com sucesso!");
            } else {
                System.out.println("Falha ao excluir local.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Excluir Evento
    private static void excluirEvento() {
        System.out.println("Informe o ID do evento a ser excluído: ");
        int id = scanner.nextInt();

        String query = "DELETE FROM evento WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stm = con.prepareStatement(query)) {

            stm.setInt(1, id);
            int rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Evento excluído com sucesso!");
            } else {
                System.out.println("Falha ao excluir evento.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Listar Pessoas
    private static void listarPessoas() {
        String query = "SELECT * FROM pessoa";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stm = con.prepareStatement(query);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");

                System.out.println("ID: " + id + ", Nome: " + nome);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Criar Pessoa
    private static void criarPessoa() {
        System.out.println("Informe o nome da pessoa: ");
        String nome = scanner.next();

        String query = "INSERT INTO pessoa (nome) VALUES (?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stm = con.prepareStatement(query)) {

            stm.setString(1, nome);

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pessoa criada com sucesso!");
            } else {
                System.out.println("Falha ao criar pessoa.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Listar Organizadores
    private static void listarOrganizadores() {
        String query = "SELECT o.idEvento, p.nome FROM organizador o JOIN pessoa p ON o.idPessoa = p.id";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stm = con.prepareStatement(query);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                int idEvento = rs.getInt("idEvento");
                String nome = rs.getString("nome");

                System.out.println("Evento ID: " + idEvento + ", Nome do Organizador: " + nome);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Criar Organizador
    private static void criarOrganizador() {
        System.out.println("Informe o ID da pessoa: ");
        int idPessoa = scanner.nextInt();
        System.out.println("Informe o ID do evento: ");
        int idEvento = scanner.nextInt();

        String query = "INSERT INTO organizador (idPessoa, idEvento) VALUES (?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stm = con.prepareStatement(query)) {

            stm.setInt(1, idPessoa);
            stm.setInt(2, idEvento);

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Organizador criado com sucesso!");
            } else {
                System.out.println("Falha ao criar organizador.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Listar Participantes
    private static void listarParticipantes() {
        String query = "SELECT p.idEvento, pe.nome FROM participante p JOIN pessoa pe ON p.idPessoa = pe.id";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stm = con.prepareStatement(query);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                int idEvento = rs.getInt("idEvento");
                String nome = rs.getString("nome");

                System.out.println("Evento ID: " + idEvento + ", Nome do Participante: " + nome);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Criar Participante
    private static void criarParticipante() {
        System.out.println("Informe o ID da pessoa: ");
        int idPessoa = scanner.nextInt();
        System.out.println("Informe o ID do evento: ");
        int idEvento = scanner.nextInt();

        String query = "INSERT INTO participante (idPessoa, idEvento) VALUES (?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stm = con.prepareStatement(query)) {

            stm.setInt(1, idPessoa);
            stm.setInt(2, idEvento);

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Participante criado com sucesso!");
            } else {
                System.out.println("Falha ao criar participante.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
