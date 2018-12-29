<html>
<head>
    <title><#if user="">Connexion / Création de compte<#else>Accueil</#if></title>
</head>

<body>
<#if user=="">
    <h1>Connexion / Création de compte</h1>
    <p>Entrez vos identifiants ou créez un compte.</p>
    <form action="login" method="post">
        <input type="text" name="username" id="username" placeholder="username"/>
        <input type="password" name="password" id="username" placeholder="password"/>
        <input type="submit" value="Login"/>
    </form>

<#else>
    <h1>Bienvenue, ${user}</h1>
    <a class="button" href="./viewAllLists">Mes listes</a>
    <a class="button" href="./logout">Déconnexion</a>
</#if>
</body>
</html>
