import React, { useState, useEffect } from 'react';
import { Link } from "react-router-dom";
import * as S from "./style";
import topimg from "../Main/img/QR코드사진2.png";

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
                <div className="total">
                <div>
                    <img src={topimg} className="topimg" />
                </div>
                <div className="topimg-text">
                    <p>QR코드 관리</p>
                </div>
                <div className="qr-title1">
                        <div>
                            <div className="tbadd">테이블 추가</div>
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
                    <div className="table-title">TABLE LIST</div>
                <div className="qr-container">
                    <div className="card-box">
                                {!qrCodes.length && (
                                    <div>
                                        <td colSpan="3" className="empty-list">목록이 비었습니다.</td>
                                    </div>
                                )}
                                {!!qrCodes.length && qrCodes.map((table) => {
                                    return (
                                        <div className="card">
                                            <div className="body-item-1">
                                                {table.no}
                                            </div>
                                            <div className="body-item-2">
                                                <img src={`${table.qrCode}`}
                                                    width='180'
                                                    height='180'
                                                    className='img-box'
                                                >
                                                </img>
                                            </div>
                                            {/* <div className="body-item-3">{table.tabId}</div> */}
                                            <div className="body-item-4" onClick={() => removetable(`${table.tabId}`)}><button className="delete-button">X</button></div>
                                        </div>
                                    )
                                })}
                                </div>
                </div>
                </div>
            </S.QRcodeWrap>

        </>
    );
}
export default QRcode;