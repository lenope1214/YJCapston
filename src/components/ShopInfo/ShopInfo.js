import { Link } from "react-router-dom";
import React from "react";
import * as S from "./style";
import DaumPostcode from "react-daum-postcode";

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
    Shop_v2,
    Shop_v3,
    isOpen,
    roadAddr,
    handleAddress,
    modal,
    openModal,
    handleComplete,     
    handleRoadAddr,
    img,
    shop
}
) => {
    
    const postCodeStyle = {
        display: "block",
        position: "fixed",
        top: "50%",
        width: "400px",
        height: "500px",
        padding: "7px",
        left: "50%",
        transform: "translate(-50%, -50%)",
    };

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
                value={shopName}
            />

            <div className="info-3">
                <h1>매장소개</h1>
            </div>
            <textarea
            className="input-box-area"
            row="10"
            placeholder={intro}
            id="intro"
            onChange={handleShopIntro}
            value={shopIntro}
            />

            <div className="info-4">
                <h1>오픈시간</h1>
            </div>
            <input 
                type="text"
                id="open_time"
                placeholder={openTime}
                onChange={handleShopOpenTime}
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
                disabled
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
            {/* <input
                type="text"
                id="address"
                placeholder={address}
                onChange={handleShopAddress}
                // value={address}
                value={shopAddress}
            /> */}
            <input 
                type="text"
                id="address"
                placeholder={roadAddr}
                onChange={handleRoadAddr}
                value={roadAddr}
                disabled
                />

                <button onClick={openModal}>검색</button>

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
        <div>
            {/* <span className="info-10"
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
            </span> */}
            
            
            <button onClick={Shop_v2} className="rs-button">예약여부변경하기</button>
            {/* <span className="open">{isRsPos}</span> */}
        </div>
            
            <div className="info-12">
              <h1>오픈여부</h1>
            </div>
        <div>
            <button onClick={Shop_v3} className="open-button">오픈여부변경하기</button>
            {/* <span className="open">{isOpen}</span>                 */}
        </div>    

        

        <div className="button-div">
            <button onClick={Shop_v1} className="button">수정</button>
            <Link to="/myshop">
            <button className="button" >취소</button>
            </Link>
        </div> 

        
        
        </S.InfoWrap>

        {modal && (
                <DaumPostcode
                    onComplete={handleComplete}
                    style={postCodeStyle}
                    height={700}
                />
            )}

        </>
    );
}

export default ShopInfo;