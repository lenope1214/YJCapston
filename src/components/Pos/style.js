import styled from "styled-components";

// export const PosWrap = styled.div`
// .pos-container{
//     padding-top: 160px;
//     padding-left: 50%;
//     transform: translateX(-27%);
//     width: 70%;
//     padding-bottom: 70px;

// }
// `
export const PosWrap = styled.div` 
    .left-container {
        border-right: 1px solid black;
        margin-top: 53px;
        margin-left: 100px;
        width: 45%;
        height: 700px;
    }
    .right-container {
        // border-left:1px solid black;
        width: 80%;
        margin-left: 10%;
        height: 620px;
        margin-top: 60px;
    }
    .shopName {
        display: inline-block;
        border-bottom: 1px solid black;
        text-align: center;
        padding-top: 10px;
        padding-bottom: 10px;
        width: 100%;
        font-weight: bold;
        font-size: 20px;
        padding: 10px;
    }
    .line {
        // border:1px solid black;
        margin-top: 20px;
    }
    
    .input-box {
        width: 30px;
    }

    .box {
        margin: 5%;
        width: 36px;
        height: 36px;
        background: #000;
        display: inline-block;
    }
    .addGroupbut {
        margin-left: 50px;
        background: gary;
        border: 0px;
        font-size: 30px;
        padding-left: 10px;
    }
    .deleteGroupbut {
        background: gary;
        border: 0px;
        left: 50px;
        font-size: 30px;
    }
    .chatlist {
        position: relative;
        top: 50%;
        left: 10%;
    }

    .table {
        width: 180px;
        height: 182px;
        border: 1px solid black;
        display: inline-block;
        margin-left: 40px;
        background: #333333;
        color: white;
        position: relative;
        margin: 15px 15px 15px 15px;
    }

    .tables {
        margin-top: 4px;
        width: 180px;
        height: 130px;
        overflow-y: scroll;
        background: white;
        color: black;
    }
    .tables-price {
        bottom: 0;
        position: absolute;
        width: 180px;
        text-align: center;
        color: white;
        margin-bottom: 2px;
    }
`;
