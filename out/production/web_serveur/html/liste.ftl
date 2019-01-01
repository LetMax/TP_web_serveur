<html>
<head>
    <#if id_liste_mere==0><title>Liste</title><#else><title>Sous liste</title></#if>
    <link rel="stylesheet" href="/css/style.css"/>
</head>

<body>
    <#if id_liste_mere==0><h1>Votre liste ${titre} :</h1><#else><h1>Votre sous liste ${titre} :</h1></#if>
    <h2>Eléments :</h2>
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
                    <td><a class="button" href="/viewAllLists/${id}/statut/${elem.id}"><#if elem.statut==1>Fait<#else>A faire</#if></a></td>
                    <td><a class="button" href="/viewAllLists/${id}/editElement/${elem.id}">Editer</a> <a class="button" href="/viewAllLists/${id}/removeElement/${elem.id}">Supprimer</a></td>
                </tr>
            </#list>
        </tbody>
    </table>
    <form action="/viewAllLists/${id}/ajoutElement" method="post">
        <input type="text" name="titre" id="titre" placeholder="Titre"/><br/>
        <input type="text" name="description" id="description" placeholder="Description"/><br/>
        <input type="submit" value="Ajouter élément"/>
    </form>
    <h2>Sous listes :</h2>
        <table>
            <thead>
                <tr>
                    <td>Titre</td>
                    <td>Description</td>
                </tr>
            </thead>
            <tbody>
                <#list sous_listes as sous_liste>
                <#if sous_liste.id_liste_mere==idint>
                    <tr>
                        <td><a class="button" href="/viewAllLists/${sous_liste.id}/liste">${sous_liste.titre}</a></td>
                        <td>${sous_liste.description}</td>
                        <td><a class="button" href="/viewAllLists/${sous_liste.id}/edit">Editer</a> <a class="button" href="/viewAllLists/${sous_liste.id}/remove">Supprimer</a></td>
                    </tr>
                </#if>
                </#list>
            </tbody>
        </table>
        <form action="/viewAllLists/${id}/creaSousListe" method="post">
            <input type="text" name="titre" id="titre" placeholder="Titre"/><br/>
            <input type="text" name="description" id="description" placeholder="Description"/><br/>
            <input type="submit" value="Ajouter sous liste"/>
        </form>
    <#if id_liste_mere==0><#else><a class="button" href="../../viewAllLists/${id_liste_mere}/liste">Liste mère</a></#if>
    <a class="button" href="../../viewAllLists">Mes listes</a>
    <a class="button" href="../../">Retourner à l'accueil</a>
</body>
</html>