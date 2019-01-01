<html>
<head>
    <title>Modification</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>

<body>
<h1>Modifiez votre liste</h1>
<form action="./edit" method="post">
    <label for="titre">Titre : </label>
    <input type="text" name="titre" id="titre" value="${titre}" placeholder="Titre"/><br/>

    <label for="description">Description : </label>
    <input type="text" name="description" id="description" value="${description}" placeholder="Description"/><br/>

    <input type="submit" value="Modifier"/>
</form>
</body>
</html>
