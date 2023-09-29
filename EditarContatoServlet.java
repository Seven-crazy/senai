@WebServlet("/editar")
public class EditarContatoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        // Interação com o banco de dados usando JDBC para obter o contato com o ID especificado
        Contato contato = null;
        Connection conn = null;
        try {
            // Estabelecer a conexão com o banco de dados
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/seubanco", "root", "index");

            // Preparar a instrução SQL para selecionar o contato pelo ID
            String sql = "SELECT * FROM contatos WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Se encontrar o contato, preencher os detalhes
            if (resultSet.next()) {
                contato = new Contato();
                contato.setId(resultSet.getInt("id"));
                contato.setNome(resultSet.getString("nome"));
                contato.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Armazenar o contato na solicitação para exibição na JSP de edição
        request.setAttribute("contato", contato);
        request.getRequestDispatcher("edicao.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String novoNome = request.getParameter("nome");
        String novoEmail = request.getParameter("email");
        
        // Interação com o banco de dados usando JDBC para atualizar o registro
        Connection conn = null;
        try {
            // Estabelecer a conexão com o banco de dados
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sua_basedados", "root", "index");

            // Preparar a instrução SQL para atualizar o contato pelo ID
            String sql = "UPDATE contatos SET nome=?, email=? WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, novoNome);
            preparedStatement.setString(2, novoEmail);
            preparedStatement.setInt(3, id);

            // Executar a atualização
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        // Redirecione para a página de lista de contatos após a edição
        response.sendRedirect(request.getContextPath() + "/listar");
    }
}
