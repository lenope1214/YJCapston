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

export const MainWrap = styled.div`

    @font-face {
        font-family: "Wemakeprice-Bold";
        src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10-21@1.0/Wemakeprice-Bold.woff")
            format("woff");
        font-weight: normal;
        font-style: normal;
    }
    .App {
        text-align: center;
        background-color: #d1c1a3;
        margin: 0 auto;
    }

    .black-nav {
        width: 100%;
        color: white;
        display: flex;
        padding-top: 30px;
        padding-bottom: 30px;
        font-weight: 700;
        font-size: 33px;
        font-family: "Wemakeprice-Bold";
        position: fixed;
        z-index: 8;
        margin-top: 0px;
    }
    .adimg {
        position: relative;
        margin-top: 0px;
    }
    .topimg {
        position: absolute;
        width: 100%;
        height: 100vh;
        object-fit: fill;
        margin-top: 0px;
        
    }
    .topimg-img {
        object-fit: fill;
    }
    .toptext {
        position: absolute;
        font-family: "Wemakeprice-Bold";
        font-size: 80px;
        color: white;
        text-align: left;
        line-height: 60px;
        margin-top: 50vh;
        margin-left: 55vw;
    }
    .left-nav {
        display: flex;
        padding-left: 10%;
        flex-grow: 1;
    }

    .center-nav {
        display: flex;
        flex-grow: 2;
        visibility: false;
        display: none;
        height: 55px;
    }

    
    .right2-nav {
        width: 120px;
        background-color: #f2f2f2bb;
        border: none;
        color: #fff;
        padding: 15px 0;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px;
        margin-right: 30px;
        cursor: pointer;
        background: none;
        
        font-family: "Wemakeprice-Bold";
    }
    .right-nav {
        padding-right: 2%;
    }

    .but-item1 {
        position: absolute;
        margin-top: 100vh;
        font-family: "Wemakeprice-Bold";
        color: white;
        font-size: 70px;
        width: 100%;
        height: 100vh;
        text-align: left;
        line-height: 3px;
        background-color: #bead8e;
        z-index: 2;
    }

    .but-item1-text {
        position: relative;
        margin-top: 40vh;
        padding-left: 20%;
        display: inline-block;
        z-index: 2;
    }

    .but-item1-text2 {
        position: relative;
        
        padding-left: 20%;
        font-size: 14px;
        font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
        display: block;
        z-index: 2;
    }

    .but-item1-icon {
        position: relative;
        float: right;
        margin-right: 15%;
        margin-top: 30vh;
        display: inline-block;
        z-index: 2;
    }

    .but-item1-but {
        cursor: pointer;
    }

    .backimg2 {
        margin-top: 100vh;
        position: absolute;
        width: 100%;
        height: 100vh;
        object-fit: fill;
        z-index: 1;
    }

    .backimg3 {
        position: absolute;
        width: 100%;
        height: 100vh;
        z-index: 1;
    }
    .backimg-img {
        object-fit: fill;
    }

    .but-item2 {
        position: absolute;
        text-align: center;
        width: 100%;
        height: 100vh;
        margin-top: 100vh;
        font-family: "Wemakeprice-Bold";
        color: white;
        font-size: 70px;
        text-align: left;
        line-height: 3px;
        z-index: 3;
        object-fit: fill;
    }

    .but-item2-text {
        position: relative;
        margin-top: 140vh;
        padding-left: 20%;
        display: inline-block;
        z-index: 2;
    }

    .but-item2-text2 {
        position: relative;
        
        padding-left: 20%;
        font-size: 14px;
        font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
        display: block;
        z-index: 2;
       
    }

    .but-item2-icon {
        position: relative;
        float: right;
        margin-right: 15%;
        margin-top: 130vh;
        display: inline-block;
        z-index: 2;
    }

    .but-item2-but {
        margin-left: 13%;
        z-index: 2;
        cursor: pointer;
    }

    .but-item3 {
        position: absolute;
        width: 100%;
        height: 100vh;
        margin-top: 200vh;
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
        margin-top: 140vh;
        padding-left: 20%;
        display: inline-block;
        z-index: 2;
    }

    .but-item3-text2 {
        position: relative;
        
        padding-left: 20%;
        font-size: 14px;
        font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
        display: block;
        z-index: 2;
    }
    .scroll-nav {
        width: 10px;        
        margin-top: 20vh;
        position: fixed;
        z-index: 3;
        margin-left: 0;
    }
    .right1-nav {
        width: 70px;
        height: 50px;
        background-color: #AE905E;
        opacity: 0.2;
        border: none;
        color: #fff;
        padding: 15px 0;
        text-align: center;
        text-decoration: none;      
        font-size: 15px;
        margin-bottom: 16px;
        cursor: pointer;
        opacity: 0.9;
        border-radius: 0 20% 20% 0;
        box-shadow: 3px 3px 3px 3px rgba(0, 0, 0, 0.4);
    }
    .right1-nav-selected {
        width: 80px;
        height: 55px;
        background-color: #6F4F28;
        opacity: 0.2;
        border: none;
        color: #fff;
        padding: 15px 0;
        text-align: center;
        text-decoration: none;      
        font-size: 15px;
        margin-bottom: 16px;
        cursor: pointer;
        opacity: 0.9;
        border-radius: 0 20% 20% 0;
        box-shadow: 4px 4px 4px 4px rgba(0, 0, 0, 0.4);
    }

    .but-item3-icon {
        position: relative;
        float: right;
        margin-right: 15%;
        margin-top: 130vh;
        display: inline-block;
        z-index: 2;
    }

    .but-item3-but {
        margin-left: 5%;
        z-index: 3;
        cursor: pointer;
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
        min-height: 100vh;
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
    /* 그림자 이용
    /* .toptext { 
        text-shadow: -2px 0 #000, 0 2px #000, 2px 0 #000, 0 -2px #000;
    }
    .but-item1-text {
        text-shadow: -2px 0 #000, 0 2px #000, 2px 0 #000, 0 -2px #000;
    } */
`;
