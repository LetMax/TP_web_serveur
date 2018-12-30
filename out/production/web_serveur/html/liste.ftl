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
                <td>Date création</td>
                <td>Dernière modification</td>
                <td>Marquer comme</td>
            </tr>
        </thead>
        <tbody>
            <#list elements as elem>
                <tr>
                    <td><#if elem.statut==1>${elem.titre}<#else><s>${elem.titre}</s></#if></td>
                    <td><#if elem.statut==1>${elem.description}<#else><s>${elem.description}</s></#if></td>
                    <td><#if elem.statut==1>${elem.dateCrea?date}<#else><s>${elem.dateCrea?date}</s></#if></td>
                    <td><#if elem.statut==1>${elem.dateModif?date}<#else><s>${elem.dateModif?date}</s></#if></td>
                    <td><a href="/viewAllLists/${id}/statut/${elem.id}"><#if elem.statut==1>Fait<#else>A faire</#if></a></td>
                    <td><a href="/viewAllLists/${id}/editElement/${elem.id}">Editer</a> <a href="/viewAllLists/${id}/removeElement/${elem.id}">Supprimer</a></td>
                </tr>
            </#list>
        </tbody>
    </table>
    <form action="/viewAllLists/${id}/ajoutElement" method="post">
        <input type="text" name="titre" id="titre" placeholder="Titre"/>
        <input type="text" name="description" id="description" placeholder="Description"/>
        <input type="submit" value="Ajouter"/>
    </form>
    <a class="button" href="../../viewAllLists">Mes listes</a>
    <a class="button" href="../../">Retourner à l'accueil</a>
</body>
</html>