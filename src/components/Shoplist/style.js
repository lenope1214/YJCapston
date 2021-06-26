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

export const ShoplistWrap = styled.div`

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

    .App {
        text-align: center;
        background-color: white;
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
        z-index: 8;
        background-color: white;
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
        width: 290px;
        background-color: white;
        height: 34px;
        opacity: 1;
        border: 0px;
        border-bottom: 1px solid #a7a7a7;
    }

    .center-nav button {
        width: 50px;
        height: 34px;
        border: 0px;
        background: none;
        opacity: 1;
        border: none;
        transform: translateX(-42px);
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
        padding-right: 4%;
    }
    .search-icon {
        margin-top: 10px;
    }

    .but-item1 {
        position: absolute;
        margin-top: 700px;
        font-family: "Wemakeprice-Bold";
        color: white;
        font-size: 70px;
        width: 100%;
        height: 700px;
        text-align: left;
        line-height: 3px;
        background-color: #bead8e;
        z-index: 2;
    }

    .but-item1-text {
        position: relative;
        padding-top: 160px;
        padding-left: 20%;
        display: inline-block;
        z-index: 2;
    }

    .but-item1-text2 {
        position: relative;
        padding-top: 1px;
        padding-left: 20%;
        font-size: 14px;
        font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
        display: block;
        z-index: 2;
    }

    .but-item1-icon {
        position: relative;
        padding-top: 160px;
        margin-left: 22%;
        display: inline-block;
        z-index: 2;
    }

    .but-item1-but {
    }

    .backimg2 {
        position: absolute;
        width: 100%;
        z-index: 1;
    }

    .backimg3 {
        position: absolute;
        width: 100%;
        z-index: 1;
    }

    .but-item2 {
        position: absolute;
        text-align: center;
        width: 100%;
        height: 700px;
        margin-top: 1400px;
        font-family: "Wemakeprice-Bold";
        color: white;
        font-size: 70px;
        width: 100%;
        height: 700px;
        text-align: left;
        line-height: 3px;
        z-index: 3;
    }

    .but-item2-text {
        position: relative;
        padding-top: 160px;
        padding-left: 20%;
        display: inline-block;
        z-index: 2;
    }

    .but-item2-text2 {
        position: relative;
        padding-top: 1px;
        padding-left: 20%;
        font-size: 14px;
        font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
        display: block;
        z-index: 2;
        margin-top: -30px;
    }

    .but-item2-icon {
        position: relative;
        padding-top: 160px;
        margin-left: 20%;
        display: inline-block;
        z-index: 2;
    }

    .but-item2-but {
        margin-left: 13%;
        z-index: 2;
    }

    .but-item3 {
        position: absolute;
        width: 100%;
        height: 700px;
        margin-top: 2100px;
        z-index: 1;
        font-family: "Wemakeprice-Bold";
        color: white;
        font-size: 70px;
        text-align: left;
        line-height: 3px;
        background-color: #bead8e;
        z-index: 2;
    }

    .but-item3-text {
        position: relative;
        padding-top: 140px;
        padding-left: 20%;
        display: inline-block;
        z-index: 2;
    }

    .but-item3-text2 {
        position: relative;
        margin-top: -30px;
        padding-left: 20%;
        font-size: 14px;
        font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
        display: block;
        z-index: 2;
    }

    .but-item3-icon {
        position: relative;
        padding-top: 160px;
        margin-left: 15%;
        display: inline-block;
        z-index: 2;
    }

    .but-item3-but {
        margin-left: 5%;
        z-index: 2;
    }

    .App-logo {
        height: 40vmin;
        pointer-events: none;
    }

    @media (prefers-reduced-motion: no-preference) {
        .App-logo {
            animation: App-logo-spin infinite 20s linear;
        }
    }

    .App-header {
        background-color: #282c34;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        font-size: calc(10px + 2vmin);
        color: white;
    }

    .App-link {
        color: #61dafb;
    }

    @keyframes App-logo-spin {
        from {
            transform: rotate(0deg);
        }
        to {
            transform: rotate(360deg);
        }
    }
    > header {
        padding: 12px 0;
        text-align: center;
    }
    .rightnav {
        position: fixed;
        top: 50%;
        left: 90%;
        transform: translate(-50%, -50%);
        margin: 1% auto 0;
        border-radius: 8px;
        line-height: 1px;
        height: 1px;
        color: white;
        z-index: 10;
        border: none;
    }
    body {
        padding-top: 100px;
        color: black;
        width:100%;
    }
    .mainimg {
        margin: 0 auto;
    }
    .mainimg img {
        width: 100%;
        height: 550px;
        margin: 0 auto;
        margin-bottom: 0;
    }
    .yangtimjang {
        margin: 0 auto;
        width: 220px;
        height: 240px;
        object-fit: cover;
        border: 0;
    }

    .topcatcat {
        width: 100%;
        height: 45px;
        background: white;
        z-index: 10;
        position: fixed;
        transform: translateY(-5px);
    }

    .topCategory {
        position: fixed;
        z-index: 10;
        display: block;
        transform: translateY(-5px);
        width: 100%;
    }

    .topCategory li {
        display: inline-block;
        font-size: 13px;
        margin: 0 auto;
        text-align: center;
        padding-top: 4px;
        padding-bottom: 4px;
    }
    .topCategory button {
        padding-top: 4px;
        padding-bottom: 4px;
        
    }

    .topCategory {
        margin: 0 auto;
    }
    .leftCategory {
        position: absolute;
        float: left;
        width: 300px;
        left: 8%;
        margin-top: 40px;
        transform: translate(-50%, -50%);
    }
    .selectcategory Button {
        width: 100px;
        background-color: #fff8f0;
        border: 1;
        color: black;
        padding: 5px 0;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 13px;
        margin: 10px;
        cursor: pointer;
        opacity: 1;
        border-style: solid;
    }
    .moneykind {
        width: 110px;
        height: 30px;
        background-color: #fff8f0;
        border: 2;
        color: black;
        padding: 5px 0;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 13px;
        margin: 5px;
        cursor: pointer;
        opacity: 1;
        border-style: solid;
        border-radius: 5px;
    }
    .leftCategory button {
        margin-top: 10px;
        height: 30px;
        transform: translateY(1px);
        color: white;
        background: black;
        border-radius: 5px;
        border: 0;
    }
    .list_shop {
        width: 80%;
        margin: 0 auto;
        padding-top: 10px;
    }
    .list_shop button {
        border: none;
        display: inline-block;
        margin: 0 auto;
        background-color: white;
    }
    .image {
        width: 100%;
        padding: 2% 0% 5% 0%;
        float: left;
    }
    .testname {
        width: 270px;
        padding: 10px;
        position: relative;
        color: black;
        cursor: pointer;
        margin-bottom: 20px;
    }

    .listname {
        padding: 10px 0px 0px 0px;
        font-size: 18px;
        font-weight: bold;
    }

    .topCategory button {
        outline: 0;
        border: 0;
        background-color: white;
        cursor: pointer;
        display: inline-block;
        font-size: 14px;
        margin: 0 auto;
        padding-left: 2%;
        padding-right: 2%;
        padding-top: 10px;
        padding-bottom: 10px;
        
    }
    .topCategory button:hover{
        color: brown;
    }

    .listcategory {
        display: block;
        float: right;
        padding-right: 13%;

        color: #dddddd;
    }
    .listAddress {
        display: block;
        padding-top: 2%;
        font-size: 13px;
        color: #555555;
        padding-bottom: 20px;
    }
    footer {
        padding-top: 80px;
    }
    .guidetext {
        margin-top: 60px;
        width: 100%;
        font-family: "Wemakeprice-Regular";
        font-size: 24px;
        font-weight: bold;
    }
    .hrstyle {
        margin-top: 16px;
        height:2px;
        width: 26px;
        margin-bottom: 40px;
        display: block;
        margin-left: 50%;
        transform: translateX(-13px);
    }
    .shoplist {
        font-family: Arial;
    }
    
    .all-category{
        // position: relative;
        font-weight:bold;
        font-size:30px;
        background:white;
        border:0px;
        margin-right:20px;
    }

    .mark-category{
        font-weight:bold;
        font-size:30px;
        background:white;
        border:0px;
        margin-left:20px;
    }
    .mark-category:hover{
        font-size:35px;
    }
    hr{
        background-color:black;
        height:30px;
    }
    .slide-container {
        height: 504px;
    }

    .each-slide {
        height: 504px;
    }

    .slide-img {
        position: relative;
    }
    
`;
