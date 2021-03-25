import React, {useState} from "react";

import *as S from "./style";


const AddShop = ({

}) => {
    return (
          <>
             <S.AddShopWrap>
                 <header>
                     <h1>매장 등록하기</h1>

                 </header>
                 <body>
                      <p>매장번호</p>
                      <span>사업자 번호</span>
                      <input
                          type="text"
                          id="id"
                          placeholder="사업자 번호를 입력하세요."
                          />
                      <span></span>    

                 </body>
                 <footer>
                     <span>영진 WD3 4조 캡스톤</span>
                 </footer>
             </S.AddShopWrap>

          </>
    );
}

export default AddShop;