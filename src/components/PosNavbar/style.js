import styled from "styled-components";

export const PosNavbarWrap = styled.div`
    a {
        color: black;
    }
    a:hover {
        color: darkgray;
    }
    pos-item:hover {
        color: darkgray;
    }
    .chatlist {
        position: fixed;
        z-index: 1000;
    }
    nav {
        position: fixed;
        display: flex;
        z-index: 4;
        top: 0;
        left: 0;
        right: 0;
        padding: 16px;
        font-weight: bold;
        background-color: #555555;
        height: 20px;
        color: white;
        margin-top: 146px;
    }
    ul {
        width: 100%;
        position: relative;
        list-style: none;
        height: 100%;
        margin-left: -20px;
        color: white;
    }
    .pos-item {
        position: relative;
        height: 100%;
        width: 25%;
        float: left;
        color: white;
        text-align: center;
    }
    .pos-item1 {
        position: relative;
        height: 100%;
        width: 25%;
        float: left;
        color: white;
        text-align: center;
        cursor: pointer;
    }
    pos-item1:hover {
        color: darkgray;
        cursor: pointer;
    }

    .why1 {
        margin-left: -10px;
    }
`;
