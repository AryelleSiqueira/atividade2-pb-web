<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>Products</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

    <div class="col-6 mx-auto">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Instant</th>
                <th scope="col">Employee</th>
                <th scope="col">Mood</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="register" items="${registers}">
                <fmt:parseDate value="${register.instant}" pattern="y-M-dd'T'H:m:s" var="parsedDateTime" />
                <tr>
                    <th scope="row">${register.id}</th>
                    <td><fmt:formatDate value="${parsedDateTime}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                    <td>${register.name}</td>
                    <td>${register.mood}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>
