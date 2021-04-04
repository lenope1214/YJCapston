import { Link } from "react-router-dom";
import React from "react";
import * as S from "./style";

const ShopInfo = ({
    shopname,
    id,
    handleId,
    handleName,
    handleIntro,
    handleOpen_time,
    handleClose_time,
    handleCategory,
    handleAddress,
    handleAddressDetail,
    handleIsRsPos
}
) => {
        console.log(shopname.id);
    return(
        <>
        <S.InfoWrap>
        <div className="info-1">
            <h1>사업자아이디</h1>
        </div>
            <input
                type="text"
                id="id"
             // placeholder={id}
                onChange={handleId}
                placeholder={shopname.id}
        />
        <div className="info-2">
            <h1>매장이름</h1>
        </div>
            <input
                type="text"
                id="name"
                onChange={handleName}
                placeholder={shopname.name}

            />
        <div className="info-3">
            <h1>매장소개</h1>
        </div>
            <input
                type="text"
                id="intro"
                onChange={handleIntro}
                placeholder={shopname.intro}
            />
        <div className="info-4">
            <h1>오픈시간</h1>
        </div>
            <input
                type="text"
                id="open_time"
                onChange={handleOpen_time}
                placeholder={shopname.open_time}
            />
        <div className="info-5">
            <h1>마감시간</h1>
        </div>
            <input
                type="text"
                id="close_time"
                onChange={handleClose_time}
                placeholder={shopname.close_time}
            />
        <div className="info-6">
            <h1>카테고리</h1>
        </div>
            <input
                type="text"
                id="close_time"
                onChange={handleCategory}
                placeholder={shopname.category}
            />
        <div className="info-7">
            <h1>매장주소</h1>
        </div>
            <input
                type="text"
                id="address"
                onChange={handleAddress}
                placeholder={shopname.address}
            />
        <div className="info-8">
            <h1>매장상세주소</h1>
        </div>
            <input
                type="text"
                id="addressDetail"
                onChange={handleAddressDetail}
                placeholder={shopname.addressDetail}
            />
        <div className="info-9">
            <h1>예약가능여부</h1>
        </div>
            <input
                type="text"
                id="isRsPos"
                onChange={handleIsRsPos}
                placeholder={shopname.isRsPos}
            />

        
        </S.InfoWrap>
        </>
    );
}

export default ShopInfo;