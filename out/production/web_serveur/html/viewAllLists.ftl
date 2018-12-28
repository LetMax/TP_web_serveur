<html>
<head>
    <title>Listes</title>
</head>

<body>
    <h1>Vos listes, ${user} :</h1>
    <table>
        <thead>
            <tr>
                <td>Titre</td>
                <td>Description</td>
            </tr>
        </thead>
        <tbody>
            <#list listes as liste>
                <tr>
                    <td>${liste.titre}</td>
                    <td>${liste.description}</td>
                </tr>
            </#list>
        </tbody>
    </table>
    <a class="button" href="./logout">Déconnexion</a>
</body>
</html>