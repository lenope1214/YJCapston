import styled from "styled-components";

export const MypageWrap = styled.div`
background: #F2F2F2;
    @font-face {
        font-family: "Wemakeprice-Bold";
        src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10-21@1.0/Wemakeprice-Bold.woff")
            format("woff");
        font-weight: normal;
        font-style: normal;
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
        background: white;
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
    .orderlist {
        margin-top: 10px;
        background-color : white;
        height: 280px;
        width: 400px;
        overflow: auto;
        border: 1px solid gray;
        border-radius: 5px;
    }
    .orderlist-item {
        margin-left: 3px;
        margin-top: 3px;
        margin-bottom : 1px;
        width: 376px;
        border: 1px solid gray;
        border-radius: 5px;
    }
    .orderitem1 {
        padding-left: 5px;
        padding-top: 5px;
        width: 160px;
        height: 32px;
        font-size: 13px;
        color: gray;
        
    }
    .orderitem1-name {
        padding-left: 5px;
        padding-top: 5px;
        width: 160px;
        height: 32px;
        transform: translate(5px, -2px);
        font-size: 15px;
    }
    .orderitem2 {
        width: 80px;
        font-size: 15px;
        transform: translateY(2px);
    }
    .orderitem2-price {
        width: 80px;
        font-size: 16px;
        transform: translateX(-3px);
    }
    .orderitem3 {
        width: 60px;
        font-size: 15px;
        transform: translateY(2px);
    }
    .orderitem4 {
        transform: translateY(4px);
        font-size: 14px;
    }
    .won {
        font-size: 12px;
    }
    .delete-button {
        margin-left: 8px;
        color: white;
        background: #1785f2;
        border: 0px;
        width: 45px;
        height: 26px;
        border-radius: 3px;
    }
`;
