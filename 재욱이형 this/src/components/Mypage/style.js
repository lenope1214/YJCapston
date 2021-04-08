import styled from "styled-components";

export const MypageWrap = styled.div`
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 500px;
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
            padding: 10px;
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
    button {
        width: 100%;
        padding: 8px;
        box-sizing: border-box;
    }
    .abc {
        z-index: 30;
        color: black;
        background-color: black;
        z-index: 300;
    }
`;
