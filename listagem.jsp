<table>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Email</th>
    </tr>
    <c:forEach items="${contatos}" var="contato">
        <tr>
            <td>${contato.id}</td>
            <td>${contato.nome}</td>
            <td>${contato.email}</td>
        </tr>
    </c:forEach>
</table>
