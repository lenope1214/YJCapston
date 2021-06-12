import React, { useState, useEffect } from 'react';
import * as S from "./style";
import { Link } from "react-router-dom";

const Pos = ({
   shopId,
   name,
   num,
   handleNum,
   number,
   nowTime,
   add_div,
   remove_div,
}) => {
    const [thisShopId, setThisShopId] = useState("");
    useEffect(() => {
        setThisShopId(shopId);
    })
    
    
    
    
    
    // function remove_div(obj){
    
    // document.getElementById('field').removeChild(obj.parentNode);
    
    // }
    
    
    
   

   
    return (
        <>
            

            
            <S.PosWrap>
                
                

            <div className="left-container">
                <div className="shopName">{name}</div>
                
            </div>
            <div className="right-container">
                

            <input type="button" value="추가" onclick={add_div}/><br/>

            <div id="room_type">

                <div className="box"></div>

            {/* <input type="button" value="삭제" onclick={remove_div(this)}/> */}

            </div>

            <div id="field"></div>





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