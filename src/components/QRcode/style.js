import styled from "styled-components";

export const QRcodeWrap = styled.div`
background: #F2F2F2;

.qr-container{
    padding-top: 160px;
    padding-left: 50%;
    transform: translateX(-27%);
    width: 70%;
    padding-bottom: 70px;
}

.QR{
    width:150px;
    height:150px;
}
.title{
    font-size:17px;
}
.input-box{
    width:50px;
    height: 30px;
    border-radius: 5px;
    border: 1px solid gray;
    font-size:10px;
}
.qr-list{
    margin-top: 70px;
    width: 100%; 
    border-collapse: collapse;
    padding-bottom: 160px;   
    background: white;
    border-radius: 3% 3% 0 0;
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
    width: 20%;
    padding:20px;
    text-align: center;
}
.body-item-2{
    width: 50%;
    padding:20px;
    text-align: center;
}
.body-item-3{
    width: 20%;
    padding:20px;
    text-align: center;
}
.body-item-4{
    width: 10%;
    padding:20px;
    text-align: center;
}
.empty-list {
    text-align: center;
    padding: 20px;
    font-size: 24px;
}
.delete-button{
    padding: 8px;
    background-color: gray;
    color: white;
    border-radius: 7px;
    border: 0px;
}
`;