

import styled from "styled-components";

export const searchWrap = styled.div`
    border:1px solid black;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 500px;
    margin: 24px auto 0;
    padding: 5% 1%;
    border-radius: 8px;
    background-color: white;
    color: black;
    z-index: 10;
`;
export const postCodeStyle = styled.div`
    display: block;
    position: absolute;
    top: 50%;
    width: 400px;
    height: 500px;
    padding: 7px;
`;

export const ShopsWrap = styled.div`

    position: absolute;
    top: 90%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 700px;
    margin: 20px;
    padding: 24px;
    // border-radius: 40px;
    // box-shadow: 0 3px 15px black;
    z-index: -1;
    font-family: "Wemakeprice-Bold";
    
    > p {
        font-size:40px;
    }

    > body{
        font-family: "Wemakeprice-Bold";
        > form {
            > div {
            font-size: 30px;
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
            > select {
                width: 30%;
                margin: 12px 0;
                padding: 10px 8px;
                border: 1px solid #bababa;
                box-sizing: border-box;
                font-family: "Wemakeprice-Bold";
                font-size: 18px;
            }
        }
    }



   

    // > body {
    //     // background-color: white;
    //     // color: black;
    //     font-family: "Wemakeprice-Bold";
    //     // padding: 5% 20% 0%;
    //     > p {
    //         font-size: 40px;
    //     }
    //     > span {
    //         font-size: 30px;
    //     }
    //     // > div {
    //     //     border: 1px solid #bababa;
    //     // }
        // > select {
        //     width: 30%;
        //     margin: 12px 0;
        //     padding: 10px 8px;
        //     border: 1px solid #bababa;
        //     box-sizing: border-box;
        //     font-family: "Wemakeprice-Bold";
        //     font-size: 18px;
        // }
        // > input {
        //     width: 100%;
        //     margin: 12px 0;
        //     padding: 10px 8px;
        //     border: 1px solid #bababa;
        //     box-sizing: border-box;
        //     font-family: "Wemakeprice-Bold";
        //     font-size: 18px;
        // }
    //     > div {
    //         font-size:20px;
    //     }
    

    

`;
