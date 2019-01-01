<html>
<head>
    <title>Listes</title>
    <link rel="stylesheet" href="css/style.css"/>
</head>

<body>
    <h1>Vos listes, ${user} :</h1>
    <div style="text-align: center">
    <table>
        <tr>
            <td>Titre</td>
            <td>Description</td>
        </tr>
        <#list listes as liste>
        <#if liste.id_liste_mere==0>
            <tr>
                <td><a class="button" href="/viewAllLists/${liste.id}/liste">${liste.titre}</a></td>
                <td>${liste.description}</td>
                <td><a class="button" href="/viewAllLists/${liste.id}/edit">Editer</a> <a class="button" href="/viewAllLists/${liste.id}/remove">Supprimer</a></td>
            </tr>
        </#if>
        </#list>
    </table>
    </div>
    <form action="/viewAllLists/creaListe" method="post">
        <input type="text" name="titre" id="titre" placeholder="Titre"/><br/>
        <input type="text" name="description" id="description" placeholder="Description"/><br/>
        <input type="submit" value="Ajouter liste"/>
    </form>
    <a class="button" href="./">Retourner à l'accueil</a>
</body>
</html>