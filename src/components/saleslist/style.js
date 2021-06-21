import styled from "styled-components";

export const saleslistWrap = styled.div`

@font-face {
    font-family: 'LAB디지털';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/LAB디지털.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
    .left-container{
        position:absolute;
        margin-top:200px;
        width:35%;
        height:630px;
        background-color:white;
        border-right:10px solid #DDDDDD;
    }
    .right-container {
        border-left:5px solid #DDDDDD;
        position:absolute;
        margin-left:35%;
        margin-top:200px;
        background-color:white;
        padding-top:0px;
        width: 59%;
        height:630px;
    }
    
    .emp-container {
        padding-top: 80px;
        margin-left: 550px;
        transform: translateX(-27%);
        width: 92%;
        padding-bottom: 70px;
    }
    .emp-link {
        text-decoration: none;
        text-align: center;
    }
    .emp-list {
        width: 100%;
        padding-top: 20px;
    }
    .emp-title {
        margin-left: 40%;
    }
    .emp-title-1 {
        font-size: 25px;
        font-weight: bold;
    }
    .table-head {
        background: #dddddd;
    }
    .item-1 {
        padding-top: 10px;
        padding-bottom: 10px;
    }
    .item-2 {
        width: 60px;
        position: center;
        padding-bottom: 10px;
    }
    .body-item-1 {
        text-align: center;
        border-bottom: 3px solid #f2f2f2;
        padding-top: 10px;
        padding-bottom: 10px;
    }
    .body-item-2 {
        text-align: center;
        border-bottom: 3px solid #f2f2f2;
        padding-top: 10px;
        padding-bottom: 10px;
    }
    .body-item-3 {
        text-align: center;
        border-bottom: 3px solid #f2f2f2;
        padding-top: 10px;
        padding-bottom: 10px;
    }
    .body-item-4 {
        text-align: center;
        border-bottom: 3px solid #f2f2f2;
        padding-top: 10px;
        padding-bottom: 10px;
    }
    .body-item-5 {
        text-align: center;
        border-bottom: 3px solid #f2f2f2;
        padding-top: 10px;
        padding-bottom: 10px;
    }
    .body-item-6 {
        text-align: center;
        border-bottom: 3px solid #f2f2f2;
        padding-top: 10px;
        padding-bottom: 10px;
    }
    .body-item-7 {
        width: 10px;
    }
    .item-1:hover {
        background-color: #c3c2c2;
    }
    .delete-button:hover {
        background-color: black;
    }
    .delete-button {
        padding: 8px;
        background-color: gray;
        color: white;
        // border-radius: 7px;
        border: 0px;
    }
    .time {
        font-size: 30px;
        font-weight: bold;
        margin-left: 60%;
        padding-bottom: 20px;
    }
    .left-head{
        // font-weight:bold;
        // text-align:center;
        // font-size:25px;
        border-bottom:7px solid #DDDDDD;
        padding-top:10px;
        padding-bottom:10px;
    }
    .shopname{
        
        font-weight:bold;
        text-align:center;
        font-size:25px;
        // border-bottom:7px solid #DDDDDD;
        padding-top:10px;
        padding-bottom:30px;
    }
    .chart{
        margin-left:10%;
       
        width:80%;
        height:70%;
    }
    .search-box {
        margin-left: 20px;
    }

    .input-box {
        width: 130px;
        padding: 2px;
    }

    .button {
        background: #333333;
        color: white;
        border: 0;
        padding: 5px;
        margin-top: 4px;
        transform: translateY(2px);
        margin-left: 1px;
        width: 137px;
    }

    .result-box {
        text-align: center;
        border: 1px solid black;
        margin: 0 20px 0 20px;
        margin-top: 80px;
        height: 120px;
        color: white;
        border: 0px;
        background: #333333;
        padding: 5px;
        border-radius: 10px;
    }
    .type {
        margin-top: 5px;
        text-align: left;
        margin-left: 10px;
        font-size: 15px;
    }
    .money {
        margin-top: 24px;
        font-size: 30px;
        font-family: 'LAB디지털';
    }
    .but-box {
        margin-bottom: 12px;
    }
    .sub-title {
        margin-top: 15px;
        margin-left: 20px;
        font-size: 18px;
        margin-bottom: -20px;
        border-bottom: 1px solid gray;
        width: 80px;
        padding-bottom: 5px;
    }
`;
