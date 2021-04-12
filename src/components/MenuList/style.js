import styled from "styled-components";

export const MenuWrap = styled.div`
margin-top: 70px;
width: 60%;
margin-left: 20%;

a { color: black;}

.btn-link, btn-link:visited {
    color: whitesmoke;
    font-size: 1.2em;
    margin: 16px 0px;
    display: block;
    font-weight: bold;
    background: #323232;
    box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.37 );
    backdrop-filter: blur( 8.5px );
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
    margin-top: 100px;
    width: 100%; 
    border-collapse: collapse;
    margin-bottom: 70px;   
}

.menu-link {
    text-decoration: none;
    text-align: center;
}

th, td {
    border-bottom: 1px solid #444444;
}

.menu-title {
    margin-bottom : 100px;
}

.item-1 {
    width: 20%;
    padding: 20px;   
}

.item-2 {
    width: 55%;
}

.item-3 {
    width: 20%;
    text-align: center;
}

.item-4 {
    width: 5%;
}
  
.body-item-1 {
    padding: 3px 10px 2px 10px;
    text-align: center;
}

.img-box {
    border-radius: 15%;
    text-align: center;
}

.body-item-2 {
    padding-left: 10%;
    font-size: 19px;

}

.body-item-3 {
    padding: 15px;
    text-align:center;
    font-size: 19px;
}

.body-item-4 {
    padding: 15px;
    font-size: 19px;
    text-aling: center;
}
.delete-button {
    padding: 8px;
    background-color: black;
    color: white;
    border-radius: 7px;
    border: 0px;
    cursor: pointer;
}
.empty-list {
    text-align: center;
    padding: 20px;
    font-size: 24px;
}
`;