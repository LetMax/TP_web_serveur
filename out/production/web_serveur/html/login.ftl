<html>
<head>
    <title><#if user="">Login<#else>Accueil</#if></title>
     <link rel="stylesheet" href="/css/style.css"/>
</head>

<body>
<#if user=="">
    <h1>Connexion / Création de compte</h1>
    <p>Entrez vos identifiants ou créez un compte.</p>
    <form action="login" method="post">
        <input type="text" name="username" id="username" placeholder="username"/><br/>
        <input type="password" name="password" id="username" placeholder="password"/><br/>
        <input type="submit" value="Login"/>
    </form>

<#else>
    <h1>Bienvenue, ${user}</h1>
    <a class="button" href="./viewAllLists">Mes listes</a>
    <a class="button" href="./logout">Déconnexion</a>
</#if>
</body>
</html>
