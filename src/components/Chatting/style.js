import styled from "styled-components";
export const ChattingWrap = styled.div`
background: gray;
height: 100vh;
bottom: 0;
.total {
    padding-top: 50px;
    background-color: gray;
    color: white;
    width: 100%;
    height: auto;
}

.shopname {
    width: 100%;
    height: 30px;
    text-align: center;
    background: white;
    font-size: 9px;
    color: black;
    padding-left: 10px;
    padding-top: 10px;
    position: fixed;
    top: 0;
    box-shadow: 2px 2px 2px 2px #444444;
    z-index: 10;
}

.abc {
    width: 100%;
    position: fixed;
    bottom: 0;
    margin-bottom: 1px;
}

.msg-input {
    width: 81%;
    height: 30px;
    border-radius: 3px;
    border: 1px solid gray;
}

.msg-but {
    transform: translateY(1px);
    width: 17%;
    padding: 7px;
    background: #222222;
    color: white;
    border-radius: 3px;
    border: 1px solid gray;
}

.chat {
    margin-left: 5px;
    margin-bottom: -30px;
}

.chat-body {
    margin-left: 50px;
    transform: translateY(-40px);
    font-size: 12px;
}

.chat-body-body {
    color: black;
    background: white;
    margin-top: -5px;
    margin-right: 50px;
    padding: 5px;
    border-radius: 5px;
    display: inline-block;
    max-width: 65%;
    word-break: break-all;
}

.chat-user {
    text-align: right;
    margin-right: 5px;
    margin-bottom: -30px;
}

.chat-user p {
    margin-right: 45px;
}
`