import styled from "styled-components";

export const LoginWrap = styled.div`
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 80%;
    margin: 1% auto 0;
    padding: 5% 1%;
    border-radius: 8px;
    background-color: black;
    color: white;
    z-index: 10;
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
        color: white;
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
        display: flex;
        flex-grow: 2;
        visibility: true;
    }

    .right1-nav {
        width: 100px;
        background-color: #f2f2f2bb;
        border: none;
        color: #fff;
        padding: 15px 0;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 15px;
        margin: 4px;
        cursor: pointer;
        opacity: 1;
    }
    .right2-nav {
        width: 100px;
        background-color: #f2f2f2bb;
        border: none;
        color: #fff;
        padding: 15px 0;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 15px;
        margin: 4px;
        cursor: pointer;
        opacity: 1;
    }
    .right-nav {
        padding-right: 2%;
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
