@WebServlet("/excluir")
public class ExcluirContatoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        // Interação com o banco de dados usando JDBC para excluir o registro
        Connection conn = null;
        try {
            // Estabelecer a conexão com o banco de dados
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sua_basedados", "root", "index");

            // Preparar a instrução SQL para excluir o contato pelo ID
            String sql = "DELETE FROM contatos WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            // Executar a exclusão
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
        
        // Redirecione para a página de lista de contatos após a exclusão
        response.sendRedirect(request.getContextPath() + "/listar");
    }
}
