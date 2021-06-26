import styled from "styled-components";

export const QRcodeWrap = styled.div`
background: #F2F2F2;
height: 0;

.total {
    background: #F2F2F2;
}

.topimg {
    margin-top: 145px;
    height: 200px;
    width: 100%;
    margin-bottom: 50px;
    position: absolute;
}
.topimg-text {
    font-family: "Wemakeprice-Bold";
    margin-top: 220px;
    width: 100%;
    position: absolute;
    text-align: center;
    font-size: 46px;
    color: white;
}
.qr-container{
    position: relative;
    padding-top: 380px;
    margin-left: 15%;
    width: 70%;
    padding-bottom: 70px;
    text-align: center;
}

.qr-title1 {
    margin-top: 430px;
    position: absolute;
    width: 170px;
    border: 0px;
    float: left;
    margin-left: 20px;
    background: white;
    text-align: center;
}

.tbadd {
    width: 100%;
    background: #333333;
    color: white;
    margin-bottom: 10px;
    padding: 5px 0 5px 0;
}

.QR{
    width:150px;
    height:150px;
}
.title{
    font-size: 14px;
    text-align: left;
    margin-left: 30px;
}
.input-box{
    margin-top: -15px;
    width: 100px;
    height: 30px;
    border-radius: 5px;
    border: 1px solid gray;
    font-size: 10px;
    margin-bottom: 10px;
}
.qr-list{
    margin-top: 70px;
    width: 100%; 
    border-collapse: collapse;
    padding-bottom: 160px;   
    background: white;
    border-radius: 3% 3% 0 0;
}

.button1 {
    margin-top: 10px;
    width: 100px;
    background: #222222;
    color: white;
    border: 0px;
    padding: 3px;
    margin-bottom: 15px;
}

th, td {
    // border-top: 1px solid #444444;
    border-bottom: 1px solid #444444;
}
.item-1{
    width: 20%;
    padding:20px;
    text-align: center;
}
.item-2{
    width: 50%;
    padding:20px;
    text-align: center;
}
.item-3{
    width: 20%;
    padding:20px;
    text-align: center;
}
.item-4{
    width: 10%;
    padding:20px;
    text-align: center;
}
.body-item-1{
    width: 100%;
    padding: 4px 0 4px 0;
    background: #333333;
    color: white;
    text-align: center;
}
.body-item-2{
    width: 180px;
    height: 180px;
    margin-left: 20px;
    margin-top: 10px;
    border: 1px solid black;
    text-align: center;
}
.body-item-3{
    padding:20px;
    text-align: center;
}
.body-item-4{
    width: 10%;
    padding:10px;
    text-align: center;
}
.empty-list {
    text-align: center;
    padding: 20px;
    font-size: 24px;
}
.delete-button{
    margin-top: 20px;
    padding: 8px;
    background-color: #333333;
    color: white;
    border-radius: 7px;
    border: 0px;
    width: 150px;
    margin-left: 25px;
}

.card-box {
    margin-top: 20px;
    width: 100%;
    background: white;
}

.card {
    width: 220px;
    border: 1px solid black;
    text-align: center;
    display: inline-block;
    margin: 20px;
}
.table-title {
    position: relative;
    display: flex;
    transform: translateY(380px);
    margin-left: 15%;
    font-weight: bold;
    font-size: 24px;
}

`;