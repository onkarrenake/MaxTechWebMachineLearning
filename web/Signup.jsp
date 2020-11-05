<%-- 
    Document   : Signup
    Created on : Jul 13, 2020, 10:04:15 PM
    Author     : ar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <!--<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>-->
        <!--<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>-->
        <!------ Include the above in your HEAD tag ---------->
        <link href="css/bootstrap.css" type="text/css" rel="stylesheet" media="all">

    </head>

    <body>

        <div class="register setp">
            <div class="row">
                <div class="col-md-3 register-left">
                    <img src="https://image.ibb.co/n7oTvU/logo_white.png" alt=""/>
                    <h3>Welcome</h3>


                </div>
                <div class="col-md-9 register-right">

                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                            <form action="Registration" method="Post">
                                <h3 class="register-heading">Register Here</h3>
                                <div class="row register-form">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="text" name="name" class="form-control" placeholder="Your Name *" value="" />
                                        </div>
                                        <div class="form-group">
                                            <input type="text" name="email" class="form-control" placeholder="Your Email *" value="" />
                                        </div>
                                        <div class="form-group">
                                            <input type="text" name="contact" class="form-control" placeholder="Your Contact Number *" value="" />
                                        </div>
                                        <div class="form-group">
                                            <input type="text" name="companyname" class="form-control" placeholder="Company Name" value="" />
                                        </div>

                                    </div>
                                    <div class="col-md-6">

                                        <div class="form-group">
                                            <input type="password" name="password" class="form-control" placeholder="Password" value="" />
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="cp" class="form-control" placeholder="Conform Password*" value="" />
                                        </div>

                                        <input type="submit" class="btnRegister"  value="Register"/>
                                    </div>
                                </div>
                            </form>
                        </div>


                    </div>
                </div>
            </div>

        </div>
    </body>
</html>
