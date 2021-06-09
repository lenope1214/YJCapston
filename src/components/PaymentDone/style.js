import styled from "styled-components";

export const PaymentDoneWrap = styled.div`
    @font-face {
        font-family: "Wemakeprice-Bold";
        src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10-21@1.0/Wemakeprice-Bold.woff")
            format("woff");
        font-weight: normal;
        font-style: normal;
    }
    @font-face {
        font-family: "Wemakeprice-SemiBold";
        src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10-21@1.0/Wemakeprice-SemiBold.woff")
            format("woff");
        font-weight: normal;
        font-style: normal;
    }
    @font-face {
        font-family: "Wemakeprice-Regular";
        src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10-21@1.0/Wemakeprice-Regular.woff")
            format("woff");
        font-weight: normal;
        font-style: normal;
    }
    .jmmjlink {
        color: black;
        text-decoration: none;
    }

    .black-nav {
        width: 100%;
        color: black;
        display: flex;
        padding-top: 30px;
        padding-bottom: 10px;
        font-weight: 700;
        font-size: 33px;
        font-family: "Wemakeprice-Bold";
        position: fixed;
        z-index: 20;
        background-color: white;
        min-width: 80%;
    }
    .adimg {
        position: relative;
    }
    .topimg {
        position: absolute;
        width: 100%;
    }
    .toptext {
        position: absolute;
        font-family: "Wemakeprice-Bold";
        font-size: 80px;
        color: black;
        text-align: left;
        line-height: 60px;
        margin-top: 300px;
        margin-left: 55%;
    }
    .left-nav {
        display: flex;
        padding-left: 10%;
        flex-grow: 1;
    }

    .center-nav {
        border: 0;
        display: flex;
        flex-grow: 1;
        visibility: true;
        height: 40px;
        opacity: 1;
    }
    .center-nav input {
        width: 356px;
        border-radius: 5px 0 0 5px;
        height: 40px;
        opacity: 1;
        border: 1px solid #a7a7a7;
    }

    .center-nav button {
        border-color: #a7a7a7;
        width: 50px;
        border-left: 0;
        border-radius: 0 5px 5px 0;
        background-color: black;
        color: white;
        height: 44px;
        opacity: 1;
        border: none;
        transform: translateX(-44px);
    }

    .right1-nav {
        width: 100px;
        border: none;
        color: black;
        padding: 15px 0;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px;
        cursor: pointer;
        opacity: 1;
        font-weight: bold;
        background: none;
    }
    .right2-nav {
        width: 100px;
        border: none;
        padding: 15px 0;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px;
        cursor: pointer;
        opacity: 1;
        background: none;
        font-weight: bold;
    }
    .right-nav {
        padding-right: 2%;
    }
    .topimg {
        position: relative;
        height: 240px;
        width: 100%;
        margin-top: 100px;
    }
    .topimg-text {
        font-family: "Wemakeprice-Bold";
        width: 100%;
        position: absolute;
        text-align: center;
        font-size: 63px;
        color: white;
        transform: translateY(-180px);
    }
    .subtext {
        font-size: 26px;
        transform: translateY(-50px);
    }
    
    .total-body {
        width: 100%;
        
    }
    .body-item {
        text-align: left;
        padding-left: 50%;
        transform: translateX(-350px);
        width: 700px;
    }
    .body-title {
        margin-top: 30px;
        font-size: 20px;
        margin-bottom: 40px;
    }
    .table {
        width: 700px;
        border: 1px solid gray;
        text-align: center;
        padding-top: 10px;
        border-collapse : collapse;
    }
    th {
        border-bottom: 1px solid gray;
        border-collapse : collapse;
        padding-top: 10px;
    }
    td {
        padding-top: 20px;
        margin-top: 1px;
        border-bottom: 1px solid gray;
        padding-bottom: 10px;
    }
    .button-div {
        margin-top: 30px;
    }
    .button {
        width: 340px;
        height: 60px;
        margin-left: 5px;
        margin-right: 5px;
        color:white;
        background: gray;
        border: 1px solid gray;
    }
    .price {
        font-size: 18px;
        font-weight: bold;
    }
`;

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
        margin: 10px 0 20px 20px;
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
`;
