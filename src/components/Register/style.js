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
    background-color: black;
    color: white;
    z-index: 10;
`;

export const RegisterWrap = styled.div`
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
        color: white;
        display: flex;
        padding-top: 30px;
        padding-bottom: 30px;
        font-weight: 700;
        font-size: 33px;
        font-family: "Wemakeprice-Bold";
        position: relative;
        background-color: black;
        > .movemainpage {
            border: none;
            color: #fff;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            cursor: pointer;
            opacity: 1;
        }
    }
    > body {
        background-color: black;
        color: white;
        font-family: "Wemakeprice-Bold";
        padding: 5% 20% 0%;
        > p {
            font-size: 40px;
        }
        > span {
            font-size: 30px;
        }
        > select {
            width: 30%;
            margin: 12px 0;
            padding: 10px 8px;
            border: 1px solid #bababa;
            box-sizing: border-box;
            font-family: "Wemakeprice-Bold";
            font-size: 18px;
        }
        > input {
            width: 100%;
            margin: 12px 0;
            padding: 10px 8px;
            border: 1px solid #bababa;
            box-sizing: border-box;
            font-family: "Wemakeprice-Bold";
            font-size: 18px;
        }
        > .phone {
            width: 32%;
        }
        > .named {
            padding-right: 75%;
        }
        > .checkbox {
            display: inline-block;
            width: 50px; /* 체크박스의 너비를 지정 */
            height: 50px; /* 체크박스의 높이를 지정 */
            line-height: 21px; /* 세로정렬을 위해 높이값과 일치 */
            margin: -2px 8px 0 0;
            text-align: center;
            vertical-align: middle;
            background: #fafafa;
            border: 1px solid #cacece;
            border-radius: 3px;
            box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.05),
                inset 0px -15px 10px -12px rgba(0, 0, 0, 0.05);
        }
        > .check {
            text-align: center;
        }
    }
    > footer {
        background-color: black;
        padding: 5%;
        text-align: center;
        > button {
            margin: 12px 0;
            padding: 20px;
            border: 1px solid #bababa;
            box-sizing: border-box;
            font-family: "Wemakeprice-Bold";
            font-size: 25px;
        }
    }
`;
