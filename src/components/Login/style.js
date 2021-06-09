import styled from "styled-components";

export const LoginWrap = styled.div`
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 500px;
    margin: 24px auto 0;
    padding: 24px;
    border-radius: 8px;
    box-shadow: 0 3px 15px #bababa;
    z-index: 10;
    > header {
        padding: 12px 0;
        text-align: center;
    }
    > main {
        > p {
            font-size: 18px;
        }
        > input {
            width: 100%;
            margin: 12px 0;
            padding: 4px 8px;
            border: 1px solid #bababa;
            box-sizing: border-box;
            font-size: 18px;
        }
    }
    footer {
        div {
            display: flex;
            align-items: center;
            input {
                margin-right: 4px;
            }
        }
        button {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
    }
`;

export const Loginback = styled.div`
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: #f2f2f2bb;
    z-index: 9;
`;
