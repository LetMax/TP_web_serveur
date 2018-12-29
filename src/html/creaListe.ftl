<html>
<head>
    <title>Création</title>
</head>

<body>
<h1>Créez votre liste</h1>
<form action="../viewAllLists/creaListe" method="post">
    <label for="titre">Titre : </label>
    <input type="text" class="input" name="titre" id="titre" placeholder="Titre"/>

    <label for="description">Description : </label>
    <input type="text" class="input" name="description" id="description" style="width:400px" placeholder="Description"/>

    <input type="submit" value="Créer"/>
</form>
</body>
</html>
