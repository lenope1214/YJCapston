import styled from "styled-components";

export const tableWrap = styled.div`
margin: 0;
padding: 0;

.all-container {
    margin-top: 100px;
}

.left-container {
    width: 39%;
    margin-left: 5%;
    padding-right: 5%;
    float: left;
    border-right: 1px solid gray;
}

.right-container {
    width: 44%;
    margin-left: 5%;
    float: left;
}

.left-1 {
    padding-bottom: 60px;
    border-bottom: 1px solid gray;
}

table {
    width: 100%;
    border: 1px solid gray;
    text-align: center;
    border-spacing: 0px;
}
th {
    border-bottom: 1px solid gray;
    border-spacing: 0px;
    padding: 8px;
    background: #444444;
    color: white;
}

td {
    padding: 10px 0 10px 0;
}

.total-box {
    width: 100%;
    margin-top: 10px;
    margin-bottom: 20px;
    text-align: right;
}
.total-price {
    // background: #333333;
    // color: white;
    padding: 10px;
    text-align: center;
    font-size: 20px;
    text-align: right;
    margin-right: 20px;
}

.left-title1 {
    font-size: 20px;
    margin-bottom: 20px;
    font-weight: bold;
}
.left-title2 {
    margin-top: 20px;
    font-size: 20px;
    margin-bottom: 20px;
    font-weight: bold;
}
.button-box {
    width: 100%;
    text-align: right;
}
.button1 {
    margin-top: 10px;
    width: 200px;
    margin: 10px 10px 0 10px;
    padding: 15px;
    border: 0px;
    color: white;
    background: #444444;
}
.button2 {
    width: 200px;
    padding: 15px;
    border: 0px;
    color: white;
    background: #444444;
}
.button3 {
    margin-top: 10px;
    width: 200px;
    padding: 15px;
    border: 0px;
    color: white;
    background: #444444;
}
.del-but {
    background: none;
    border: 0px;
    cursor: pointer;
    font-size: 15px;
    text-decoration: underline;
}
.table-num {
    width: 90%;
    background: #444444;
    color: white;
    padding: 12px;
    font-size: 18px;
    font-weight: bold;
    text-align: center;
    border: 1px solid #444444;
}
.people-num {
    width: 90%;
    text-align: center;
    padding: 12px;
    font-size: 18px;
    border: 1px solid gray;
}
.people-num button {
    margin: 5px;
    font-size: 20px;
    background: none;
    border: 1px solid gray;
}
.item-box {
    width: 90%;
    padding: 12px;
    border: 1px solid gray;
    text-align: center;
    height: 65vh;
}
.item {
    display: inline-block;
    padding: 10px;
    width: 150px;
    text-align: center;
}
`