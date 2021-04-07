import styled from "styled-components";

export const NavWrap = styled.div`
* {
    margin: 0;
    padding: 0;
}

a { color:black;
}
a:hover {
    color: gray;
}

nav {
    position: relative;
    display: flex;
    top: 0;
    left: 0;
    right: 0;
    padding: 1rem;
    color: black;
    font-weight: bold;
    border-top: 1px solid #444444;
    border-bottom: 1px solid #444444;
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