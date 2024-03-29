import styled from "styled-components";

export const headerWrap = styled.div`
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
        top:0;
        color: white;
        display: flex;
        padding-top: 30px;
        padding-bottom: 30px;
        font-weight: 700;
        font-size: 33px;
        font-family: "Wemakeprice-Bold";
        position: fixed;
        background-color: white;
        z-index: 10;
    }
    a {
        text-decoration: none;
        color: black;
    }
    .date{
        margin-left:900px;
    }
    .time{
        margin-left:40px;
    }
`;
