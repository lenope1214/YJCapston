import React, { useState, useEffect } from "react";
import * as S from "./style";
import { Link } from "react-router-dom";
import $ from "jquery";
//npm install --save jquery 설치하기
window.$ = $;

const Pos = ({ shopId, name, num, handleNum, number, nowTime }) => {
    const [thisShopId, setThisShopId] = useState("");
    useEffect(() => {
        setThisShopId(shopId);
    });

    let i = 0;
    let g = 0;

    //테이블 추가 버튼 이벤트
    const addGroup = () => {
        $(document).ready(function () {
            $(".addGroupbut").unbind("click");
            $(".addGroupbut").bind("click", function () {
                // $(".groupbox").append("<hr width='400px'/>")
                $(".groupbox").append("<span class='line-1'></span>");

                // $(".groupbox").append("<input type='text' id='g"+i+"'/><br/>")
                i++;
                console.log(i);
            });
        });
    };

    //테이블 삭제 이벤트
    const deleteGroup = () => {
        $(document).ready(function () {
            $(".deleteGroupbut").unbind("click");
            $(".deleteGroupbut").bind("click", function () {
                $(".line-1:last-child").remove();
            });
        });
    };

    return (
        <>
            <S.PosWrap>
                <div className="left-container">
                    <div className="shopName">{name}</div>
                </div>
                <div className="right-container">
                    {/* 테이블추가 되는 영역 */}
                    <span className="groupbox"></span>

                    {/* 테이블추가 버튼 */}
                    <input
                        type="button"
                        value="+
                    "
                        className="addGroupbut"
                        onClick={addGroup()}
                    />

                    {/* 테이블삭제버튼 */}

                    <input
                        type="button"
                        value="-
                    "
                        className="deleteGroupbut"
                        onClick={deleteGroup()}
                    />
                    <button
                        class="chatlist"
                        onClick={() =>
                            window.open(
                                `http://3.34.55.186:3000/chat/${shopId}`,
                                "_blank",
                                "location = no, toolbars= no, status= no, width = 400, height = 500 , scrollbars = no"
                            )
                        }
                    >
                        채팅방가기
                    </button>

                    {/* <button className="addGroupbut">+</button> */}

                    {/* <div className="line">
                    <span className="line-1">
                        <input
                            className="input-box"  
                            type="text"
                        />
                    </span>
                    <span className="line-2">
                        <input
                            className="input-box"  
                            type="text"
                        />
                    </span>
                    <span className="line-3">
                        <input
                            className="input-box"  
                            type="text"
                        /></span>
                    <span className="line-4">
                        <input
                            className="input-box"  
                            type="text"
                        /></span>
                    <span className="line-5">
                        <input
                            className="input-box"  
                            type="text"
                        /></span>
                </div>
                

                <div className="line">
                    <span className="line-1">
                        <input
                            className="input-box"  
                            type="text"
                        />
                    </span>
                    <span className="line-2">
                        <input
                            className="input-box"  
                            type="text"
                        />
                    </span>
                    <span className="line-3">
                        <input
                            className="input-box"  
                            type="text"
                        /></span>
                    <span className="line-4">
                        <input
                            className="input-box"  
                            type="text"
                        /></span>
                    <span className="line-5">
                        <input
                            className="input-box"  
                            type="text"
                        /></span>
                </div>


                <div className="line">
                    <span className="line-1">
                        <input
                            className="input-box"  
                            type="text"
                        />
                    </span>
                    <span className="line-2">
                        <input
                            className="input-box"  
                            type="text"
                        />
                    </span>
                    <span className="line-3">
                        <input
                            className="input-box"  
                            type="text"
                        /></span>
                    <span className="line-4">
                        <input
                            className="input-box"  
                            type="text"
                        /></span>
                    <span className="line-5">
                        <input
                            className="input-box"  
                            type="text"
                        /></span>
                </div>


                <div className="line">
                    <span className="line-1">
                        <input
                            className="input-box"  
                            type="text"
                        />
                    </span>
                    <span className="line-2">
                        <input
                            className="input-box"  
                            type="text"
                        />
                    </span>
                    <span className="line-3">
                        <input
                            className="input-box"  
                            type="text"
                        /></span>
                    <span className="line-4">
                        <input
                            className="input-box"  
                            type="text"
                        /></span>
                    <span className="line-5">
                        <input
                            className="input-box"  
                            type="text"
                        /></span>
                </div>


                <div className="line">
                    <span className="line-1">
                        <input
                            className="input-box"  
                            type="text"
                        />
                    </span>
                    <span className="line-2">
                        <input
                            className="input-box"  
                            type="text"
                        />
                    </span>
                    <span className="line-3">
                        <input
                            className="input-box"  
                            type="text"
                        /></span>
                    <span className="line-4">
                        <input
                            className="input-box"  
                            type="text"
                        /></span>
                    <span className="line-5">
                        <input
                            className="input-box"  
                            type="text"
                        /></span>
                </div> */}
                </div>
            </S.PosWrap>
        </>
    );
};

export default Pos;
