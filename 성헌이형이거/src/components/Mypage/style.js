import styled from "styled-components";

export const MypageWrap = styled.div`
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

    margin: 24px auto 0;
    padding: 24px;
    border-radius: 40px;
    box-shadow: 0 3px 15px black;
    z-index: -1;

    > header {
        padding: 12px 0;
        text-align: center;
    }
    > body {
        p {
            font-size: 20px;
            margin: 0;
            padding-left: 0px;
            font-weight: bold;
        }
        span {
            padding-right: 30px;
        }

        > input {
            margin: 12px 0;
            padding: 4px 3px;
            border: 1px solid #bababa;
            box-sizing: border-box;
            font-size: 18px;
        }
    }
    button {
        padding: 8px;
        box-sizing: border-box;
    }
    .abc {
        z-index: 30;
        color: black;
        background-color: black;
        z-index: 300;
    }
    .name {
        padding-right: 63px;
    }
    .id {
        padding-right: 32px;
    }
    .email {
        padding-right: 47px;
    }
    select {
        padding: 6px;
        border-radius: 0;
        font-size: 14px;
    }
    .point {
        padding-right: 55px;
    }
    .address {
        padding-right: 61px;
    }
    #address {
        margin-right: 10px;
    }
    footer {
        padding-left: 35%;
    }
`;
