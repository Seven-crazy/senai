<form action="editar" method="post">
    <input type="hidden" name="id" value="${contato.id}">
    Nome: <input type="text" name="nome" value="${contato.nome}"><br>
    Email: <input type="text" name="email" value="${contato.email}"><br>
    <input type="submit" value="Editar">
</form>
