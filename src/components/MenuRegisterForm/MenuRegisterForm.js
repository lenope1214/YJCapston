import { Link } from "react-router-dom";
import React from "react";
import * as S from "./style";
import topimg from "../Main/img/QRcode2.png";
import $ from 'jquery';
//npm install --save jquery 설치하기
window.$ = $;

const MenuRegisterForm = ({
    menuname,
    handleMenuname,
    price,
    handlePrice,
    handleImg,
    menudesc,
    handleMenudesc,
    menu_v1,
    duration,
    handleDuration,
    shopId,
}) => {

    let i = 0;
    let g = 0;

    // // 그룹 추가 버튼 이벤트
    // const addGroup = () => {
    //     $(document).ready(function(){
    //         $('.addGroupbut').unbind("click");
    //         $('.addGroupbut').bind("click",function(){
    //             i++;
    //             $(".groupbox").append("<hr width='400px'/>")
    //             $(".groupbox").append("<div class='label'>그룹명</div>")
    //             $(".groupbox").append("<input type='text' id='g"+i+"'/><br/>")
    //             // $(".groupbox").append("<div class='label'>옵션 추가</div>")
    //             // $(".groupbox").append("<div class='inputbox"+i+"'></div><br/>")
    //             // $(".groupbox").append("<input type='button' value='추가' class='addinputbut" + i +"' /><br/>")   
    //             // console.log("그룹 i = "+i);
    //         });
    //     });
    // }

    // // 옵션 추가 버튼 이벤트      
    //     $('.groupbox').off('click').on("click", ".addinputbut1", function(){
    //         g++
    //             $(".inputbox1").append("<input type='text' id='v"+g+"'/><br/>")
    //     })

    //     $('.groupbox').on("click", ".addinputbut2", function(){
    //         g++
    //             $(".inputbox2").append("<input type='text' id='v"+g+"'/><br/>")
    //     })
    //     $('.groupbox').on("click", ".addinputbut3", function(){
    //         g++
    //             $(".inputbox3").append("<input type='text' id='v"+g+"'/><br/>")
    //     })
    //     $('.groupbox').on("click", ".addinputbut4", function(){
    //         g++
    //             $(".inputbox4").append("<input type='text' id='v"+g+"'/><br/>")
    //     })

    return (
        <>
            <S.MenuRegisterWrap>
                <div>
                    <img src={topimg} className="topimg" />
                </div>
                <div className="topimg-text">
                    <p>메뉴 추가</p>
                </div>
                <div className="total-body">
                    <form encType="multipart/form-data">
                                    <div className="label">메뉴명</div>
                                    <div>
                                        <input
                                            type="text"
                                            id="name"
                                            placeholder="메뉴명"
                                            value={menuname}
                                            onChange={handleMenuname}
                                            className="input-box"
                                        />
                                    </div>
      
                                <div>
                                    <div className="label">메뉴가격</div>
                                    <div>
                                        <input
                                            type="number"
                                            id="price"
                                            placeholder="메뉴가격"
                                            value={price}
                                            onChange={handlePrice}
                                            className="input-box"
                                        />
        
                                        <span className="in-won">₩</span>
                 
                                    </div>
                                </div>
                                <div>
                                    <div className="label">메뉴이미지</div>
                                    <div>
                                        <input
                                            type="file"
                                            onChange={handleImg}
                                            className="input-box"
                                        />
                                    </div>
                                    <div className="label">소요시간</div>
                                    <div>
                                        <input
                                            type="number"
                                            id="duration"
                                            placeholder="예상 소요시간(분)"
                                            value={duration}
                                            onChange={handleDuration}
                                            className="input-box"
                                        />
                                        <span className="in-minute">분</span>
                                    </div>
                                    <div className="label">메뉴설명</div>
                                    <div>
                                        <textarea
                                            row="8"
                                            placeholder="메뉴설명"
                                            id="description"
                                            value={menudesc}
                                            onChange={handleMenudesc}
                                            className="input-box-area"
                                        />
                                    </div>
                                    </div>
                                    
                                    {/* <div className="label">그룹 추가</div> */}

                                    {/* 그룹추가 되는 영역 */}
                                    {/* <div className="groupbox">
                                        
                                    </div> */}

                                    {/* 그룹추가 버튼 */}
                                    {/* <input type="button" value="그룹추가" className="addGroupbut" onClick={addGroup()}/> */}
                                    
                                    {/* <div className="label">옵션 추가</div> */}
                                    
                                    {/* 옵션추가 영역 */}
                                    {/* <div className="inputbox">
                                    </div> */}

                                    {/* 옵션추가 버튼 */}
                                    {/* <input type="button" value="추가" className="addinputbut"/> */}
                    </form>
                    <div className="button-box">
                        <button onClick={menu_v1} className="button3">추가</button>
                        <Link to={`/menulist/${shopId}`} className="button">
                            <button className="button4">취소</button>
                        </Link>
                    </div>
                </div>
            </S.MenuRegisterWrap>
        </>
    );
}

export default MenuRegisterForm;