import styled from "styled-components";

export const NavWrap = styled.div`
* {
    margin: 0;
    padding: 0;
}

a { 
    color: white;
}

a:hover {
    color: darkgray;
}

nav {
    position: relative;
    display: flex;
    top: 0;
    left: 0;
    right: 0;
    padding: 1rem;
    font-weight: bold;
    background-color: #282828;
    height: 20px;
    color: white;
}

ul {
    width: 100%;
    position:relative;
    list-style:none;
    height: 100%;
}

.nav-item {
    position:relative;
    height:100%;
    width:33%;
    float:left;
    text-align: center;
}
`;