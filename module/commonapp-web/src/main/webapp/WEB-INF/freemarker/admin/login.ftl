<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>登陆</title>

    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <link href="/resources/css/signin.css" rel="stylesheet">

</head>

<body>

<div class="container">

    <form class="form-signin" method="post" action="${rc.getContextPath()}/j_spring_security_check">
        <h2 class="form-signin-heading">欢迎进入管理后台</h2>
        <label for="inputUserName" class="sr-only">用户名</label>
        <input type="text" name="userName" id="inputUserName" class="form-control" placeholder="用户名" required autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="密码" required>
        <#if errorMsg??>
            <div class="error-mess">
                <span id="error-message">${errorMsg}</span>
            </div>
        </#if>

        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
    </form>

</div> <!-- /container -->


</body>
</html>
