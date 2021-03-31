import React from "react";
import { Link } from "react-router-dom";
import * as S from "./style";

const Shoplist = ({ isLogin, logout, openModal }) => {
    return (
        <>
            <S.ShoplistWrap>
                <header>
                    <Link to="/" class="movemainpage">
                        <div className="left-nav">
                            주문
                            <span
                                style={{
                                    fontSize: "23px",
                                    paddingTop: "10px",
                                }}
                            >
                                의
                            </span>
                            민족
                        </div>
                    </Link>
                    <div className="center-nav">
                        <input
                            type="text"
                            placeholder="매장을 검색하세요."
                            style={{
                                width: "300px",
                                border: "0",
                                borderRadius: "3px 0 0 3px",
                                backgroundColor: "white",
                            }}
                        ></input>
                        <button
                            style={{
                                border: "0",
                                width: "10%",
                                borderRadius: "0 3px 3px 0",
                                backgroundColor: "white",
                                color: "grey",
                            }}
                        >
                            검색
                        </button>
                    </div>
                    {isLogin ? (
                        <div className="right-nav">
                            <button className="right1-nav" onClick={logout}>
                                LOGOUT
                            </button>
                            <Link to="/mypage" class="right2-nav">
                                MYPAGE
                            </Link>
                        </div>
                    ) : (
                        <div className="right-nav">
                            <button className="right1-nav" onClick={openModal}>
                                LOGIN
                            </button>

                            <Link to="/register" class="right2-nav">
                                JOIN
                            </Link>
                        </div>
                    )}
                    <Link to="/shop" class="shop">
                    <button>
                          매장등록하러가기
                    </button>
                    </Link>
                </header>
                
                <table className="shoplist"border="1">
                    <thead>
                    <tr>
                        <td>번호</td>
                        <td>제목</td>
                        <td>작성자</td>
                        <td>날짜</td>
                        <td>조회수</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>주민</td>
                        <td>주민</td>
                        <td>2021.03.24</td>
                        <td>2</td>
                    </tr>    
                    </tbody>
                </table>

                {/* <table class="table">
                <thead>
               <tr class="warning">
                   <th class="col-sm-1">매장번호</th>
                   <th class="col-sm-1">매장이름</th>
                   <th class="col-sm-5">매장소개</th>
                   <th class="col-sm-3">오픈시간</th>
                   <th class="col-sm-3">마감시간</th>
                   <th class="col-sm-3">매장주소</th>
                   <th class="col-sm-3">매장상세주소</th>
                   <th class="col-sm-3">카테고리</th>
                   <th class="col-sm-3">예약가능여부</th>
               </tr>
               </thead>
               <tbody class="shoplist">

               </tbody>
               </table> */}

                {/* <main>
                매장 리스트
                    <div style={{
                       padding:"50px"

                    }}
                    >
                        
                    </div>
                    <div class name="shop-nav">
                        asd
                    </div>
                </main> */}
                <footer></footer>
            </S.ShoplistWrap>
        </>
    );
};

export default Shoplist;
