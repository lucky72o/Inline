<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><html><html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <%--<link rel="icon" href="../../favicon.ico">--%>

    <title>Inline</title>

    <!-- Bootstrap core CSS -->
    <link href="res/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="res/css/jumbotron.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <%--<link href="res/css/bootstrap.css" rel="stylesheet" />--%>
    <link href="res/css/font-awesome.min.css" rel="stylesheet" />
    <!-- CUSTOM STYLE CSS -->
    <link href="res/css/style.css" rel="stylesheet" />
    <!-- GOOGLE FONT -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="main.htm">Inline</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <form class="navbar-form navbar-right">
                <div class="form-group">
                    <input type="text" placeholder="Email" class="form-control">
                </div>
                <div class="form-group">
                    <input type="password" placeholder="Password" class="form-control">
                </div>
                <button type="submit" class="btn btn-success">Sign in</button>
                <button type="submit" class="btn btn-primary">Sign up</button>
            </form>
        </div><!--/.navbar-collapse -->
    </div>
</nav>

<div class="alert alert-danger" style="display: none" role="alert" id="registrationPageGlobalError">
</div>

<div class="container">
    <div class="row text-center pad-top ">
        <div class="col-md-12">
            <h2>Registration Page</h2>
        </div>
    </div>

    <div class="row  pad-top">

        <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong>   Register Yourself </strong>
                </div>
                <div class="panel-body">
                    <form:form commandName="registrationForm" id="registrationForm" action="registration.htm" method='POST'>
                        <br/>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="fa fa-circle-o-notch"  ></i></span>
                            <form:input path="name" id="name" class="form-control" placeholder="Your Name" />
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="fa fa-tag"  ></i></span>
                            <form:input path="username" id="username" class="form-control" placeholder="Desired Username" />
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon">@</span>
                            <form:input path="email" id="email" class="form-control" placeholder="Your Email" />
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
                            <form:password path="password" id="password" class="form-control" placeholder="Enter Password" />
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
                            <form:password path="passwordConfirmation" id="passwordConfirmation" class="form-control" placeholder="Retype Password" />
                        </div>

                        <a href="#" id="submitRegistrationFormBtn" class="btn btn-success ">Register Me</a>
                        <hr />
                        Already Registered ?  <a href="#" >Login here</a>
                    </form:form>
                </div>

            </div>
        </div>


    </div>

    <hr>

    <footer>
        <p>&copy; Company 2014</p>
    </footer>
</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="res/scripts/bootstrap.min.js"></script>
<script src="res/scripts/jquery-1.10.2.js"></script>
<script src="res/scripts/custom.js"></script>
</body>
</html>
