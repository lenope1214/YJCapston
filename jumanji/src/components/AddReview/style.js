import styled from "styled-components";
export const AddReviewWrap = styled.div`
background: #F2F2F2;
padding: 0;
margin: 0;
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
        z-index: 10;
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
        margin-top: 85px;
        height: 240px;
        width: 100%;
        margin-bottom: 50px;
        position: relative;
    }

    .topimg-text {
        font-family: "Wemakeprice-Bold";
        width: 100%;
        position: absolute;
        text-align: center;
        font-size: 60px;
        color: white;
        transform: translateY(-200px);
    }

    .total-body {      
        position: relative;
        margin-left: 50%;
        transform: translateX(-200px);
        z-index: 2;
    }
    .reimgbox {
        transform: translateX(-150px);
    }

    .label {
        margin-top: 20px;
        margin-bottom: 2px;
        font-size: 18px;
        font-weight: bold;
    }
    .label-desc {
        margin-top: 50px;
        margin-bottom: 2px;
        font-size: 18px;
        font-weight: bold;
        transform: translateX(-150px);
    }

    .input-box {
        width: 400px;
        height: 36px;
        border-radius: 5px;
        border: 1px solid gray;
        background: white;
    }

    .scorebox {
        height: 36px;
        border-radius: 5px;
        border: 1px solid gray;
        background: white;
        font-size: 20px;
    }

    .score {
        margin-top: 20px;
        transform: translateX(-150px);
    }

    .input-box-area {
        margin-top: 10px;
        width: 700px;
        height: 280px;
        border-radius: 5px;
        border: 1px solid gray;
        transform: translateX(-150px);
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
    .button-box {
        width: 480px;
        margin-top: 60px;
        text-align: center;
        transform: translateX(-36px);
        padding-bottom: 70px;
        margin-top: 60px;
        background: #F2F2F2;
    }
`;