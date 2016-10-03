<html>
<head>
    <title>Registration</title>
</head>

<body style="text-align:center;">

<form action="/dispatcher" method="post">
    <br>
    <label>Login: </label>
    <input type="text" name="login" placeholder="Login"/>
    <br>
    <label>Password: </label>
    <input type="password" name="password1" placeholder="Password"/>
    <br>
    <label>Password repeat: </label>
    <input type="password" name="password2" placeholder=""/>
    <br>
    <label>Name: </label>
    <input type="text" name="name" placeholder="Name"/>
    <br>
    <label>Last name: </label>
    <input type="text" name="lastName" placeholder="Last Name"/>
    <br>
    <label>Patronymic: </label>
    <input type="text" name="patronymic" placeholder="Patronymic"/>
    <br>
    <input type="hidden" name="command" value="registration"/>
    <input type="submit" value="Sign up"/>
</form>

</body>
</html>
