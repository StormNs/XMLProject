<%-- 
    Document   : header
    Created on : Jun 22, 2017, 7:51:25 PM
    Author     : USER
--%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
    #menu{
        background-color: #24292e;
        overflow: hidden;
        /*opacity: 0.6;*/
        font-size: 20px;
        /*position: fixed;*/
        width: 100%;
        top: 0px;
        left:0px;
        height: 60px;
        -webkit-box-shadow:0px 9px 21px 0px #888888;
        -moz-box-shadow:0px 9px 21px 0px #888888;
        box-shadow:0px 9px 21px 0px #888888;
    }
    #menu-container a{
        float:left;
        font-size: 17px;
        display: block;
        text-align: center;
        color: lightgray;
        padding: 20px 40px;
    }
    #menu-account a{
        float: right;
        display: block;
        font-size: 17px;
        text-align: center;
        color: lightgray;
        padding: 20px 40px;
    }

    #menu a:hover{
        color: white;
        cursor: pointer;
    }
    #menu-container{
        width: 70%;
        margin-left: 10%;
        margin-right:auto;
    }
    #menu-account{
        margin-left: auto;
        margin-right:auto;
        padding-right: 10%;
    }
    a i{
        padding-right: 10px;
        font-size: 18px !important;
    }
    .brand-icon{
        font-size: 50px !important;
        margin-top: -15px;
    }
</style>
<header role="banner">
    <div id="menu">
        <div id="menu-container">
            <!--<img src="../XMLProject/asset/play.png" height="50px" width="50px" style="float: left;margin-top: 4px;margin-left: 15px;margin-right: 15px">-->
            <a><i class="fa fa-play-circle brand-icon" style="color:lightgray"></i></a>
            <a>MOVIES</a>
            <a>SERIES</a>
            <a>FORUM</a>
            <a>ABOUT</a>
        </div>
        <div id="menu-account">
            <a>Sign up</a>
            <a><i class="fa fa-user"></i> Login</a>

        </div>
    </div> 
</header>
