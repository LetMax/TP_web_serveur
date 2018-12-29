<html>
<head>
    <title>Modification</title>
</head>

<body>
<h1>Modifiez votre liste</h1>
<form action="./edit" method="post">
    <label for="titre">Titre : </label>
    <input type="text" name="titre" id="titre" value="${titre}" placeholder="Titre"/>

    <label for="description">Description : </label>
    <input type="text" name="description" id="description" value="${description}" style="width:400px" placeholder="Description"/>

    <input type="submit" value="Modifier"/>
</form>
</body>
</html>
