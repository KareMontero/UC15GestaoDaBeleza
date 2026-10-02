
package model;

import conexao.ConexaoJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Agenda;
import model.Cliente;
import model.Funcionario;
import model.Servico;
import model.Usuario;

public class AgendaDAO {

 

  public void inserir(Agenda agenda) {
    String sql = "INSERT INTO agenda (data_hora, cliente_id, funcionario_id, servico_id, usuario_id, " +
                 "status, precoServico, valorProduto, desconto, observacao) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

    try {
        conexaoJDBC.conectar();
        Connection conn = conexaoJDBC.getConexao();

        if (conn != null) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                // 1. VALIDAÇÃO DA DATA/HORA (Se for nula, salva NULL, senão salva o Timestamp) ✨
                if (agenda.getDataHora() == null) {
                    stmt.setNull(1, java.sql.Types.TIMESTAMP);
                } else {
                    stmt.setTimestamp(1, agenda.getDataHora());
                }
                
                stmt.setInt(2, agenda.getCliente().getIdCliente()); 
                stmt.setInt(3, agenda.getFuncionario().getIdFuncionario()); 
                stmt.setInt(4, agenda.getServico().getIdServico()); 
                stmt.setInt(5, agenda.getUsuario().getIdUsuario()); 
                stmt.setString(6, agenda.getStatus());
                stmt.setDouble(7, agenda.getPrecoServico());
                stmt.setDouble(8, agenda.getValorProduto());
                stmt.setDouble(9, agenda.getDesconto());
                
                // 2. VALIDAÇÃO DA OBSERVAÇÃO (Se estiver vazia ou nula, salva NULL) ✨
                if (agenda.getObservacao() == null || agenda.getObservacao().trim().isEmpty()) {
                    stmt.setNull(10, java.sql.Types.VARCHAR);
                } else {
                    stmt.setString(10, agenda.getObservacao());
                }

                stmt.executeUpdate();
                System.out.println("Agendamento gravado com sucesso no MySQL!");
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao salvar agendamento no banco: " + e.getMessage(), e);
    } finally {
        conexaoJDBC.desconectar();
    }
}

  
// 2. MÉTODO PARA LISTAR TODOS OS AGENDAMENTOS (Corrigido com as colunas certas do seu banco! ✨)
public List<Agenda> listarTodos() {
    // CORREÇÃO AQUI: Mudado para c.nomeCliente e f.nomeFuncionario para bater com suas tabelas! 🌟
    String sql = "SELECT a.*, c.nomeCliente AS nome_cliente, f.nomeFuncionario AS nome_funcionario, s.servico AS nome_servico " +
                 "FROM agenda a " +
                 "INNER JOIN cliente c ON a.cliente_id = c.idCliente " +
                 "INNER JOIN funcionario f ON a.funcionario_id = f.idFuncionario " +
                 "INNER JOIN servico s ON a.servico_id = s.idServico " +
                 "ORDER BY a.data_hora ASC";

    List<Agenda> lista = new ArrayList<>();
    ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

    try {
        conexaoJDBC.conectar();
        Connection conn = conexaoJDBC.getConexao();

        if (conn != null) {
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Agenda a = new Agenda();
                    a.setIdAgenda(rs.getInt("idAgenda"));
                    a.setDataHora(rs.getTimestamp("data_hora"));
                    a.setStatus(rs.getString("status"));
                    a.setPrecoServico(rs.getDouble("precoServico"));
                    a.setValorProduto(rs.getDouble("valorProduto"));
                    a.setDesconto(rs.getDouble("desconto"));
                    a.setValorTotal(rs.getDouble("valorTotal")); 
                    a.setObservacao(rs.getString("observacao"));

                    // Preenche o objeto Cliente usando o método correto da sua classe
                    Cliente c = new Cliente();
                    c.setIdCliente(rs.getInt("cliente_id"));
                    c.setNomeCliente(rs.getString("nome_cliente")); // Alinhado com a sua classe Cliente
                    a.setCliente(c);

                    // Preenche o objeto Funcionario usando o método correto da sua classe
                    Funcionario f = new Funcionario();
                    f.setIdFuncionario(rs.getInt("funcionario_id"));
                    f.setNomeFuncionario(rs.getString("nome_funcionario")); // Alinhado com a sua classe Funcionario
                    a.setFuncionario(f);

                    // Preenche o objeto Servico
                    Servico s = new Servico();
                    s.setIdServico(rs.getInt("servico_id"));
                    s.setServico(rs.getString("nome_servico"));
                    a.setServico(s);

                    lista.add(a);
                }
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao buscar agendamentos no banco: " + e.getMessage(), e);
    } finally {
        conexaoJDBC.desconectar();
    }
    return lista;
}


    // 3. MÉTODO PARA MUDAR O STATUS DA AGENDA (Confirmação ou Conclusão lógica)
    public void atualizarStatus(int idAgenda, String novoStatus) {
        String sql = "UPDATE agenda SET status = ? WHERE idAgenda = ?";
        ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

        try {
            conexaoJDBC.conectar();
            Connection conn = conexaoJDBC.getConexao();

            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, novoStatus);
                    stmt.setInt(2, idAgenda);
                    stmt.executeUpdate();
                    System.out.println("Status da agenda atualizado com sucesso!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar status da agenda: " + e.getMessage(), e);
        } finally {
            conexaoJDBC.desconectar();
        }
    }
    
    // MÉTODO PARA CANCELAR AGENDAMENTO (Mantém o histórico para relatórios)
public void desmarcarAgendamento(int idAgenda) {
    // Usa o status 'desmarcado' que você criou no seu ENUM do MySQL! ✨
    String sql = "UPDATE agenda SET status = 'desmarcado' WHERE idAgenda = ?";
    ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

    try {
        conexaoJDBC.conectar();
        Connection conn = conexaoJDBC.getConexao();

        if (conn != null) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idAgenda);
                stmt.executeUpdate();
                System.out.println("Agendamento marcado como desmarcado com sucesso!");
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao desmarcar agendamento: " + e.getMessage(), e);
    } finally {
        conexaoJDBC.desconectar();
    }
}

// MÉTODO PARA DELETAR AGENDAMENTO (Apaga o registro para sempre do MySQL)
public void excluirAgendamentoDefinitivo(int idAgenda) {
    String sql = "DELETE FROM agenda WHERE idAgenda = ?";
    ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

    try {
        conexaoJDBC.conectar();
        Connection conn = conexaoJDBC.getConexao();

        if (conn != null) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idAgenda);
                stmt.executeUpdate();
                System.out.println("Agendamento excluído para sempre do banco!");
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao excluir agendamento do banco: " + e.getMessage(), e);
    } finally {
        conexaoJDBC.desconectar();
    }
}


}
  

