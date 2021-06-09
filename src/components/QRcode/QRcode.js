import React, { useState, useEffect } from 'react';
import {Link} from "react-router-dom";
import * as S from "./style";

const QRcode = ({
    id,
    shopId,
    tabno,
    handletabno,
    handleseatQty,
    QR,
    seatQty,
    qrcode,
    qrcode_v1,
    qrCodes,
    qrCode,
    removetable,
    tabId,
}) => {
    const [thisShopId, setThisShopId] = useState("");
    useEffect(() => {
        setThisShopId(shopId);
    })
    
    return (
        <>
        <S.QRcodeWrap>
<div className="qr-container">
        <div className="qr-title1">
            <h2 className="qr-title">QR코드</h2>
            <div>
                <div className="title"><h3>테이블번호</h3></div>
                <input
                    type="text"
                    id="no"
                    placeholder="테이블번호"
                    value={tabno}
                    className="input-box"
                    onChange={handletabno}
                /> 
                <div className="title"><h3>좌석수(n인석)</h3></div>
                <input
                    type="text"
                    id="seatQty"
                    placeholder="ex)4"
                    value={seatQty}
                    className="input-box"
                    onChange={handleseatQty}
                />
            </div>
            <div><button onClick={QR} className="button1">등록</button>
            </div>
            
        </div>
        <span>
        <table className="qr-list">
            <thead>
                <th align="center" className="item-1">테이블번호</th>
                <th align="center" className="item-2">qr코드</th>
                <th align="center" className="item-3">테이블 아이디</th>
                <th align="center" className="item-4"></th>
            </thead>
            <tbody>
                {!qrCodes.length && (
                    <tr>
                        <td colSpan="3" className="empty-list">목록이 비었습니다.</td>
                    </tr>
                )}
                {!!qrCodes.length && qrCodes.map((table) => {
                    return(
                        <tr>
                            <td className="body-item-1">
                            {table.no}
                            </td>
                            <td className="body-item-2">
                               <img src={`${table.qrCode}`}
                                    width='250'
                                    height='250'
                                    className='img-box'
                               >
                               </img>
                            </td>
                            <td className="body-item-3">{table.tabId}</td>
                            <td className="body-item-4" onClick={() => removetable(`${table.tabId}`)}><button className="delete-button">X</button></td>
                        </tr>
                    )
                })}
            </tbody>
        </table>
        </span>
</div>
        </S.QRcodeWrap>

        </>
 );
}
export default QRcode;