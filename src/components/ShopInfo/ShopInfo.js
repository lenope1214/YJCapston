import { Link } from "react-router-dom";
import React from "react";
import * as S from "./style";
import DaumPostcode from "react-daum-postcode";

const ShopInfo = ({
    shopId,
    id,
    name,
    intro,
    phone,
    openTime,
    closeTime,
    category,
    address,
    addressDetail,
    isRsPos,
    shopName,
    shopPhone,
    handleShopphone,
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
    shop,
    goBack,
    phone1,
    handlePhone1,
    phone2,
    handlePhone2,
    phone3,
    handlePhone3,
}
) => {
    // console.log(phone);
    
const shopphone1 = phone ? phone.substring(0,3) : '';
const shopphone2 = phone ? phone.substring(3, 7) : '';
const shopphone3 = phone ? phone.substring(7, 11) : '';

console.log(shopphone1);
    const postCodeStyle = {
        display: "block",
        position: "fixed",
        top: "60%",
        width: "400px",
        height: "500px",
        padding: "7px",
        left: "50%",
        transform: "translate(-50%, -50%)",
    };
    
    return(
        <>
        <S.InfoWrap>
        <div className="total-body">
                <div className="title">매장 정보</div>
            <div className="label">
                매장사진
            </div>
            <div className="img-box">
            <img src={`http://3.34.55.186:8088/${img}`}
                width='400'
                height='300'
                className="img-box-box"
            />
            </div>
            <div className="label">
                사업자아이디
            </div>
            <input
                type="text"
                value={shopId}
                className="input-box"
                disabled
            />

            <div className="label">
                매장이름
            </div>
            <input
                type="text"
                id="name"
                placeholder={name}
                onChange={handleShopName}
                value={shopName}
                className="input-box"
                disabled
            />

            <div className="label">전화번호</div>
                <select 
                    id="txtMobile1" 
                    defaultValue="050" 
                    className="phone-box1" 
                    // placeholder={shopphone1}
                    value={shopphone1} 
                    // onChange={handlePhone1}
                    disabled
                    
                    >

            
                    <option value="" >
                        ::선택::
                    </option>
                        <option value="010">010</option>
                        {/* <option value="02">02</option> */}
                        <option value="051">051</option>
                        <option value="053">053</option>
                        <option value="031">031</option>
                        <option value="050">050</option>
                </select>
                    -
                <input
                    className="phone-box"
                    type="text"
                    id="txtMobile2"
                    size="4"
                    onkeypress="onlyNumber();"
                    
                    
                    value={phone2}
                    onChange={handlePhone2}
                    placeholder={shopphone2}
                    disabled
                />

                    -
                <input
                    className="phone-box"
                    type="text"
                    id="txtMobile3"
                    size="4"
                    onkeypress="onlyNumber();"
                   
                  
                    value={phone3}
                    onChange={handlePhone3}
                    placeholder={shopphone3}
                    disabled
                />

            <div className="label">
                매장소개
            </div>
            <textarea
            className="input-box-area"
            row="10"
            placeholder={intro}
            id="intro"
            onChange={handleShopIntro}
            value={shopIntro}
            />

            <div className="label">
                오픈시간
            </div>
            <input 
                type="text"
                id="open_time"
                placeholder={openTime}
                onChange={handleShopOpenTime}
                value={shopOpenTime}
                className="input-box"
            />
            
            <div className="label">
                마감시간
            </div>
            <input
                type="text"
                id="close_time"
                placeholder={closeTime}
                onChange={handleShopCloseTime}
                // value={closeTime}
                value={shopClostTime}
                className="input-box"
            />
            <div className="label">
            카테고리
            </div>
            <select
                id="category"
                // placeholder={category}
                onChange={handleShopCategory}
                // value={shopCategory}
                value={category}
                className="select"
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
            <div className="label">
            매장주소
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
                className="input-box"
                disabled
                />

                <button onClick={openModal} className="button1">검색</button>

            <div className="label">
                매장상세주소
            </div>
            <input
                type="text"
                id="addressDetail"
                placeholder={addressDetail}
                onChange={handleShopAddressDetail}
                // value={addressDetail}
                value={shopAddressDetail}
                className="input-box"
            />
            {/* --------여기서부터 --------------- */}
            
            <div className="label">
            예약가능여부
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
            
            <input
                type="text"
                id="rspos"
                value={isRsPos}
                className="input-box1"
                />
            <button onClick={Shop_v2} className="button0">예약여부변경하기</button>
            {/* <span className="open">{isRsPos}</span> */}
        </div>
            
            <div className="label">
            오픈여부
            </div>
        <div>
        <input
                type="text"
                id="open"
                value={isOpen}
                className="input-box1"
                />
            <button onClick={Shop_v3} className="button0">오픈여부변경하기</button>
            {/* <span className="open">{isOpen}</span>                 */}
        </div>    

        

        <div className="button-box">
            <button onClick={Shop_v1} className="button3">수정</button>
            
            <button className="button4" onClick={goBack} >취소</button>
            
        </div> 

        
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