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
            <#if liste.id_liste_mere==0>
                <tr>
                    <td><a href="/viewAllLists/${liste.id}/liste">${liste.titre}</a></td>
                    <td>${liste.description}</td>
                    <td><a href="/viewAllLists/${liste.id}/edit">Editer</a> <a href="/viewAllLists/${liste.id}/remove">Supprimer</a></td>
                </tr>
            </#if>
            </#list>
        </tbody>
    </table>
    <form action="/viewAllLists/creaListe" method="post">
        <input type="text" name="titre" id="titre" placeholder="Titre"/>
        <input type="text" name="description" id="description" placeholder="Description"/>
        <input type="submit" value="Ajouter liste"/>
    </form>
    <a class="button" href="./">Retourner à l'accueil</a>
</body>
</html>