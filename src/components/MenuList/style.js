import styled from "styled-components";

export const MenuWrap = styled.div`
margin-top: 110px;
width: 60%;
margin-left: 20%;

.btn-link, btn-link:visited {
    color: whitesmoke;
    font-size: 1.2em;
    margin: 16px 0px;
    display: block;
    font-weight: bold;
    background: black;
    box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.37 );
    backdrop-filter: blur( 8.5px );
    -webkit-backdrop-filter: blur( 8.5px );
    border: 1px solid rgba( 255, 255, 255, 0.18 );
    border-radius: 10px;
    width: 85%;
    max-width: 800px;
    height: 50px;
    line-height: 50px;
    text-align: center;
    text-decoration: none;
    margin: 0 auto;
  }

  .menu-list {
      margin-top: 50px;
      width: 100%;
      background-color: black;
      color: white;
      text-decoration: none;
  }

  .menu-title {
      margin-bottom : 70px;
  }

  .item-1 {
      width: 20%;
      
  }

  .item-2 {
      width: 60%;
  }

  .item-3 {
      width: 15%;
  }

  .item-4 {
      width: 5%;
  }
  
`;