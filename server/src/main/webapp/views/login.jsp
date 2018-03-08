<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Bootstrap Admin</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/stylesheets/theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/lib/font-awesome/css/font-awesome.css">

    <script src="${pageContext.request.contextPath}/resources/lib/jquery-1.8.1.min.js" type="text/javascript"></script>

    <!-- Demo page code -->

    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

</head>

<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7"> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8"> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body>
<!--<![endif]-->

<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
            <ul class="nav pull-right">

            </ul>
            <a class="brand" href="${pageContext.request.contextPath}/"><span class="first">Your</span> <span class="second">Company</span></a>
        </div>
    </div>
</div>



<div class="row-fluid">
    <div class="dialog span4">
        <div class="block">
            <div class="block-heading">Sign In</div>
            <div class="block-body">
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <p class="text-error">${requestScope.message}</p>
                    <label>Username</label>
                    <input type="text" name="username" class="span12">
                    <label>Password</label>
                    <input type="password" name="password" class="span12">
                    <input type="submit" class="btn btn-primary pull-right">
                    <div class="clearfix"></div>
                </form>
            </div>
        </div>
    </div>
</div>








<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/resources/lib/bootstrap/js/bootstrap.js"></script>













</body>
</html>


