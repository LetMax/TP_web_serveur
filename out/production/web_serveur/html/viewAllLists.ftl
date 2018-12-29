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
                    <td><a href="/viewAllLists/${liste.id}/edit">Editer</a> <a href="/viewAllLists/${liste.id}/remove">Supprimer</a></td>
                </tr>
            </#list>
        </tbody>
    </table>
    <a class="button" href="/viewAllLists/creaListe">Ajouter une liste</a>
    <a class="button" href="./">Retourner à l'accueil</a>
</body>
</html>