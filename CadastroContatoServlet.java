@WebServlet("/cadastrar")
public class CadastroContatoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        
        Contato novoContato = new Contato();
        novoContato.setNome(nome);
        novoContato.setEmail(email);
        
        // Interação com o banco de dados usando JDBC
        Connection conn = null;
        try {
            // Estabelecer a conexão com o banco de dados
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sua_basedados", "root", "index");

            // Preparar a instrução SQL para inserção
            String sql = "INSERT INTO contatos (nome, email) VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, novoContato.getNome());
            preparedStatement.setString(2, novoContato.getEmail());

            // Executar a inserção
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
        
        // Redirecione para a página de lista de contatos
        response.sendRedirect(request.getContextPath() + "/listar");
    }
}
