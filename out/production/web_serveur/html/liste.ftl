<html>
<head>
    <title>Liste</title>
</head>

<body>
    <h1>Votre liste ${titre} :</h1>
    <table>
        <thead>
            <tr>
                <td>Element</td>
                <td>Description</td>
                <td>Dernière modification</td>
            </tr>
        </thead>
        <tbody>
            <#list elements as elem>
                <tr>
                    <td>${elem.titre}</td>
                    <td>${elem.description}</td>
                    <td>${elem.dateModif?date}</td>
                    <td><a href="/viewAllLists/${id}/editElement/${elem.id}">Editer</a> <a href="/viewAllLists/${id}/removeElement/${elem.id}">Supprimer</a></td>
                </tr>
            </#list>
        </tbody>
    </table>
    <form action="/viewAllLists/${id}/ajoutElement" method="post">
        <input type="text" name="titre" id="titre" placeholder="Titre"/>
        <input type="text" name="description" id="description" style="width"400px" placeholder="Description"/>
        <input type="submit" value="Ajouter"/>
    </form>
    <a class="button" href="../../viewAllLists">Mes listes</a>
    <a class="button" href="../../">Retourner à l'accueil</a>
</body>
</html>