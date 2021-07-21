import styled from "styled-components";

export const LoginWrap = styled.div`
@font-face {
    font-family: "Wemakeprice-Bold";
    src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10-21@1.0/Wemakeprice-Bold.woff")
        format("woff");
    font-weight: normal;
    font-style: normal;
}

    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 300px;
    margin: 0 auto;
    margin-top: 0px;
    padding: 5%;
    padding-top: 2%;
    border-radius: 8px;
    background-color: white;
    color: black;
    z-index: 10;
    border-style: solid;
    border: 0;

    .login-title {
        font-family: "Wemakeprice-Bold";
        text-align: center;
        padding-bottom: 12px;
        margin-bottom: 30px;
        font-size: 24px;
        border-bottom: 1px solid black;
    }
    .login-text {
        text-align: center;
        margin-bottom: 10px;
    }
    .login-input {
        height: 30px;
        width: 250px;
        margin-bottom: 10px;
        margin-left: 20px;
    }
    .remeber {
        margin: 10px 0px 20px 20px;
        padding-left: 35px;
    }
    .login-but-box {
        width: 100%;
        text-align: center;
    }
    .login-but {
        width: 120px;
        margin: 3px;
        height: 40px;
        border: 0;
        background: gray;
        color: white;
    }
    a {
        text-decoration: none;
        color: white;
    }
    .google-btn {
        width: 245px;
        margin-left: 8px;
        height: 40px;
        color: white;
        background: #1785f2;
        border: none;
        margin-bottom: -10px;
    }
`;

export const MainWrap = styled.div`

@font-face {
    font-family: 'Wemakeprice-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10-21@1.0/Wemakeprice-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
body {
    background-color: gray;
}
.App {
    background-color: gray;
}

.header {
    position: fixed;
    z-index: 10;
    height: 100px;
    width: 100%;
    text-align: center;
}

.title {
    margin-left: 40px;
    font-family: 'Wemakeprice-Regular';
    font-size: 34px;
    margin-top: 24px;
    color: white;
    float: left;
}



.title1 {
    font-weight: bold;
}

.title2 {
    font-size: 27px;
}
.title3 {
    font-size: 33px;
}

.link {
    margin-left: 40px;
    float: left;
    margin-right: 15px;
    color: white;
    margin-top: 40px;
    font-size: 18px;
}

.log-box {   
    margin-right: 5vw;
}

.log-but {
    margin-top: 40px;
    background: none;
    float: right;
    font-size: 22px;
    border: 0px;
    margin-left: 30px;
    font-weight: bold;
    color: white;
    cursor: pointer;
}
.p {
    margin-top: 36px;
}
.adimg {
    position: absolute;
    margin-top: 0px;
    width: 100%;
    height: 100vh;
    object-fit: fill;
    margin-top: 0px;
}

.backvideo {
    position: absolute;
    right: 0;
    bottom: 0;
    width: 100%;
    height: 100vh;
    object-fit: fill;
}

.videotext {
    position: absolute;
    top: 30%;
    width: 700px;
    text-align: center;
    position: absolute;
    margin-left: 50%;
    transform: translateX(-350px);
}
.black {
    color: black;
}

.vt0 {
    color: white;
    text-align: center;
    font-size: 36px;
}

.vt1 {
    transform: translateY(-40px);
    font-size: 64px;
    color: white;
    font-weight: bold;
}

.vt2 {
    transform: translateY(-5vh);
    font-size: 28px;
    color: white;
}
.dia {
    transform: translateY(-98px);
}

.scroll-down {
    text-align: center;
    width: 100px;
    position: fixed;
    color: white;
    font-size: 32px;
    bottom: 0;
    margin-left: 50vw;
    margin-bottom: 1vw;
    transform: translateX(-50px);
    z-index: 1;
    opacity: 1;
    animation: fade-in 1s;
}
.but-box {
    z-index: 10;
}

.link-button1 {
    transform: translateY(-50px);
    margin-top: 10px;
    width: 410px;
    height: 68px;
    background: #FF5050;
    border: 2px solid #FF5050;
    color: white;
    font-weight: bold;
    cursor: pointer;
    z-index: 10;
    font-size: 16px;
}

.link-button2 {
    transform: translateY(-50px);
    margin-top: 20px;
    width: 410px;
    height: 68px;
    background: none;
    border: 2px solid white;
    color: white;
    font-weight: bold;
    cursor: pointer;
    z-index: 10;
    font-size: 16px;
}

.link-button3 {
    transform: translateY(-50px);
    margin-top: 10px;
    width: 360px;
    height: 48px;
    background: none;
    border: 2px solid white;
    color: white;
    font-weight: bold;
    cursor: pointer;
    z-index: 10;
    font-size: 16px;
}

.of {
    font-size: 46px;  
}

.secondbox {
    margin-top: 0;
    margin-bottom: 0;
    width: 100%;
    padding: 0;
}

.secondimg {
    width: 100%;

    height: 100vh;
    object-fit: fill;
}

.second-text {
    position: absolute;
    text-align: center;
    width: 60vw;
    color: white;
    margin-left: 50%;
    transform: translate(-30vw, -82vh);
    z-index: 3;
}

.second-title {
    font-size: 32px;
}

.hr {
    margin-left: 50%;
    transform: translateX(-35px);
}

.item-1 {
    margin-top: 8vh;
    margin-left: 5%;
    margin-right: 5%;
    display: inline-block;
}

.item-2 {
    margin-top: 8vh;
    margin-left: 5%;
    margin-right: 5%;
    display: inline-block;
}

.item-3 {
    margin-top: 8vh;
    margin-left: 5%;
    margin-right: 5%;
    display: inline-block;
}

.itemtitle {
    font-size: 23px;
    font-weight: bold;
}

.itemdesc {
    font-size: 19px;
}
ul{
    top: calc(100% / 2 - 60px);
    position: fixed;
    height: auto;
    right: 10px;
    z-index: 11;
  }
  
  li{
    list-style-type: none;
    width: 8px;
    height: 8px;
    background-color: white;
    border-radius: 50%;
    margin: 20px 15px;
    transition: 0.3s ease;
  }

  .black2 {
      background-color: black;
  }
  
  .active{
    transform: translateX(-3px);
    width: 14px;
    height: 14px;
  }
  @keyframes fade-in {
    from {
      opacity: 0;
    }
    to {
      opacity: 1;
    }
  }
  
  @keyframes fade-out {
    from {
      opacity: 1;
  
    }
    to {
      opacity: 0;
    }
  }

  .none {
      opacity: 0;
      animation: fade-out 1s;
  }

  .section {
      height: 100vh;
      width: 100%;
  }

  #sec3 {
      background-color: #ffffff;
  }

  .preparing {
      padding-top: 25vh;
      transform: translateY(-30px);
      color: black;
      
  }

  .left {   
      margin-left: 20%;
  }

  .left-1 {
    display: block;
    font-family: 'Wemakeprice-Regular';
    font-size: 30px;
    font-weight: bold;
    margin-left: 20px;
    margin-bottom: 4vh;
  }
  .left11 {
      margin-left: 50px;
      margin-bottom: 30px;
      transform: translateY(30px);
  }
  .left22 {
      margin-left: 40px;
  }
  .left-3 {
      margin-left: 20px;
      font-size: 18px;
  }

  .button-1 {
      width: 480px;
      height: 80px;
      background: none;
      border: 2px solid black;
      margin-bottom: 20px;
      font-size: 18px;
      cursor: pointer;
  }
  .right {
      transform: translateY(-28vh);
      display: block;
      float:right;
      margin-right: 20%;
  }
  .google-btn {
      width: 600px;
  }

`;

