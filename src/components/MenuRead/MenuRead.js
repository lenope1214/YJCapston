import React from "react"
import { Link } from "react-router-dom"

const MenuRead = ({menuRead}) => {
    return(
        <table>
            <tbody>
                <tr>
                    <td>상품번호</td>
                    <td>
                        <input 
                            type="text"
                            value={menuRead.id} readOnly
                        />
                    </td>
                </tr>
            </tbody>
        </table>
    );
}

export default MenuRead;