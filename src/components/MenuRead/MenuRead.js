import React from "react"
import { Link } from "react-router-dom"

const MenuRead = (menuRead) => {
    console.log(menuRead);
    return(
        <table>
            <tbody>
                <tr>
                    <td>상품번호</td>
                    <td>
                        <input 
                            type="text"
                            value={menuRead}
                        />
                    </td>
                </tr>
                <tr>
                    <td>상품명</td>
                    <td>
                        <input 
                            type="text"
                            
                        />
                    </td>
                </tr>
                <tr>
                    <td>상품가격</td>
                    <td>
                        <input 
                            type="text"
                            
                        />
                    </td>
                </tr>
                <tr>
                    <td>상품소개</td>
                    <td>
                        <textarea 
                            row="3"
                            
                        />
                    </td>
                </tr>
            </tbody>
        </table>
    );
}

export default MenuRead;