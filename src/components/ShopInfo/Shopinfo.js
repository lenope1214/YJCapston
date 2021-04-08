import { Link } from "react-router-dom";
import React from "react";
import * as S from "./style";

const ShopInfo = ({
    shopId,
    id,
    name,
    intro,
    openTime,
    closeTime,
    category,
    address,
    addressDetail,
    isRsPos,
    shopName,
    handleShopName,
    // handleId,
    shopIntro,
    handleShopIntro,
    shopOpenTime,
    handleShopOpenTime,
    shopClostTime,
    handleShopCloseTime,
    shopAddress,
    handleShopAddress,
    shopCategory,
    handleShopCategory,
    shopAddressDetail,
    handleShopAddressDetail,
    shopIsRsPos,
    handleShopIsRsPos,
    Shop_v1,
}
) => {

        // console.log(shopname.id);
    return(
        <>
        <S.InfoWrap>
            <div className="info-1">
                <h1>사업자아이디</h1>
            </div>
            <input
                type="text"
                value={id}
                disabled
            />

            <div className="info-2">
                <h1>매장이름</h1>
            </div>
            <input
                type="text"
                id="name"
                placeholder={name}
                onChange={handleShopName}
                // value={name}
                value={shopName}
                // disabled
            />
            <div className="info-3">
                <h1>매장소개</h1>
            </div>
            <textarea
            className="input-box-area"
            fow="10"
            placeholder={intro}
            id="intro"
            value={shopIntro}
            onChange={handleShopIntro}
            />

            {/* <input
                type="text"
                id="intro"
                placeholder={intro}
                onChange={handleShopIntro}
                // value={intro}
                value={shopIntro}
            /> */}
            <div className="info-4">
                <h1>오픈시간</h1>
            </div>
            <input 
                type="text"
                id="open_time"
                placeholder={openTime}
                onChange={handleShopOpenTime}
                // value={openTime}
                value={shopOpenTime}
            />
            <div className="info-5">
                <h1>마감시간</h1>
            </div>
            <input
                type="text"
                id="close_time"
                placeholder={closeTime}
                onChange={handleShopCloseTime}
                // value={closeTime}
                value={shopClostTime}
            />
            <div className="info-6">
            <h1>카테고리</h1>
            </div>
            <select
                id="category"
                // placeholder={category}
                onChange={handleShopCategory}
                // value={shopCategory}
                value={category}
                >
                <option value="" disable={true}>선택</option>
                <option value="한식">한식</option>
                <option value="일식">일식</option>
                <option value="중식">중식</option>
                <option value="고기">고기</option>
                <option value="분식">분식</option>
                <option value="술집">술집</option>
                <option value="패스트푸드">패스트푸드</option>
                <option value="찜.탕">찜.탕</option>
                <option value="카페.디저트">카페.디저트</option>    

            </select>
            <div className="info-7">
            <h1>매장주소</h1>
            </div>
            <input
                type="text"
                id="address"
                placeholder={address}
                onChange={handleShopAddress}
                // value={address}
                value={shopAddress}
            />
            <div className="info-8">
                <h1>매장상세주소</h1>
            </div>
            <input
                type="text"
                id="addressDetail"
                placeholder={addressDetail}
                onChange={handleShopAddressDetail}
                // value={addressDetail}
                value={shopAddressDetail}
            />
            <div className="info-9">
            <h1>예약가능여부</h1>
            </div>
            <span className="info-10"
                // style={{
                //     fontSize:"20px",
                //     paddingTop:"20px",
                // }}
            >
            가능
            <input 
                type="radio"
                id="is_rs_pos1"
                name="is_rs_pos"
                // placeholder={isRsPos ="Y"}
            
                value={isRsPos = "Y"}
                // onChange={handleIs_rs_pos}
            />
            </span>
            <span className="info-11"
                // style={{
                //     fontSize:"20px",
                //     paddingTop:"20px",
                // }}
            >
            불가능
            <input
                type="radio"
                id="is_rs_pos2"
                name="is_rs_pos"
                // placeholder={isRsPos ="N"}
                value={isRsPos = "N"}
                // onChange={handleIs_rs_pos}
            />
            </span>
        {/* <div className="info-1">
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
                value={shopname.name}

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
            <select
                id="category"
                onChange={handleCategory}
                value={shopname.category}
                >
                <option value="" disable={true}>선택</option>
                <option value="한식">한식</option>
                <option value="일식">일식</option>
                <option value="중식">중식</option>
                <option value="고기">고기</option>
                <option value="분식">분식</option>
                <option value="술집">술집</option>
                <option value="패스트푸드">패스트푸드</option>
                <option value="찜.탕">찜.탕</option>
                <option value="카페.디저트">카페.디저트</option>    

            </select>
            
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
        */}

        <div className="button-div">
            <button onClick={Shop_v1} className="button">수정</button>
            <Link to="/myshop">
            <button className="button" >취소</button>
            </Link>
        </div> 

        
        </S.InfoWrap>
        </>
    );
}

export default ShopInfo;