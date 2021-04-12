import styled from "styled-components";

export const searchWrap = styled.div`
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 500px;
    margin: 24px auto 0;
    padding: 5% 1%;
    border-radius: 8px;
    background-color: white;
    color: white;
    z-index: 10;
`;

export const postCodeStyle = styled.div`
    display: block;
    position: absolute;
    top: 50%;
    width: 400px;
    height: 500px;
    padding: 7px;
    .close {
        display: "block",
        position: "fixed",
        top: "40%",
        width: "400px",
        height: "550px",
        padding: "7px",
        left: "50%",
        transform: "translate(-50%, -50%)",
        width: 100%;
        height: 50px;
        z-index: 3;
    }
`;

export const RegisterWrap = styled.div`
background: #F2F2F2;
    @font-face {
        font-family: "Wemakeprice-Bold";
        src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10-21@1.0/Wemakeprice-Bold.woff")
            format("woff");
        font-weight: normal;
        font-style: normal;
    }
    .Modalclosebutton {
        position: fixed;
        top: 18%;
        left: 67%;
        transform: translate(-50%, -50%);
        z-index: 300;
        border: 0;
        cursor: pointer;
        background-color: white;
    }
    > header {
        padding-left: 5%;
        width: 100%;
        color: black;
        display: flex;
        padding-top: 30px;
        padding-bottom: 30px;
        font-weight: 700;
        font-size: 33px;
        font-family: "Wemakeprice-Bold";
        position: relative;
        background-color: white;
        > .movemainpage {
            border: none;
            color: black;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            cursor: pointer;
            opacity: 1;
        }
    }
    .topimg {
        height: 300px;
        width: 100%;
        margin-bottom: 50px;
        position: absolute;
    }
    .topimg-text {
        font-family: "Wemakeprice-Bold";
        margin-top: 120px;
        width: 100%;
        position: absolute;
        text-align: center;
        font-size: 60px;
        color: white;
    }
    .total-body {
        
        margin-top: 380px;
        margin-left: 50%;
        transform: translateX(-200px);    
        
    }
    .label {
        margin-top: 20px;
        margin-bottom: 2px;
        font-size: 18px;
        font-weight: bold;
    }
    .input-box {
        width: 400px;
        height: 36px;
        border-radius: 5px;
        border: 1px solid gray;
    }
    .phone-box {
        width: 80px;
        height: 30px;
        border-radius: 5px;
        border: 1px solid gray;
    }
    .phone-box1 {
        width: 80px;
        height: 34px;
        border-radius: 5px;
        border: 1px solid gray;
    }
    .button3 {
        height: 50px;
        width: 200px;
        margin-left: 5px;
        margin-right: 5px;
        background: #1785f2;
        color: white;
        border: 1px solid gray;
        font-size: 16px;
    }
    .button4 {
        height: 50px;
        width: 200px;
        margin-left: 5px;
        margin-right: 5px;
        background: white;
        color: black;
        border: 1px solid black;
        font-size: 16px;
    }
    .button1 {
        height: 40px;
        width: 60px;
        margin-right: 5px;
        transform: translateY(2px);
        transform: translateX(-60px);
        border-radius: 0 5px 5px 0;
        border: 1px solid gray;
        background: #1785f2;
        color: white;
    }
    .button2 {
        height: 40px;
        width: 60px;
        margin-right: 5px;
        transform: translateY(2px);
        transform: translateX(-60px);
        border-radius: 0 5px 5px 0;
        border: 1px solid gray;
        background: #1785f2;
        color: white;
    }
    .button-box {
        width: 480px;
        margin-top: 60px;
        text-align: center;
        transform: translateX(-36px);
        padding-bottom: 70px;
    }
    .check-box {
        margin-left: 10px;
        width: 25px;
        height: 25px;
        transform: translateY(5px);
    }
`;
