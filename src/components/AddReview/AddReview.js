import { Link } from "react-router-dom";
import React from "react";
import * as S from "./style";
import topimg from "../Main/img/QRcode2.png";

//npm install --save sweetalert2 설치하기

const AddReview = ({
    handleImg,
    menudesc,
    handleMenudesc,
    review_v1,
    shopId,
    orderId,
    score,
    handleScore,
}) => {
    return (
        <>
            <S.AddReviewWrap>
                <div>
                    <img src={topimg} className="topimg" />
                </div>
                <div className="topimg-text">
                    <p>리뷰 등록</p>
                </div>
                <div className="total-body">
                    <form encType="multipart/form-data">
                        <div>
                            <div className="reimgbox">
                                <div className="label">리뷰이미지</div>
                                <div>
                                    <input
                                        type="file"
                                        onChange={handleImg}
                                        className="input-box"
                                    />
                                </div>
                            </div>
                            <div className="score">
                                <div className="label">평점</div>
                                <div>
                                    <select
                                        className="scorebox"
                                        value={score}
                                        id="score"
                                        onChange={handleScore}
                                    >
                                        <option value="">::선택::</option>
                                        <option value="5">★★★★★</option>
                                        <option value="4">★★★★☆</option>
                                        <option value="3">★★★☆☆</option>
                                        <option value="2">★★☆☆☆</option>
                                        <option value="1">★☆☆☆☆</option>
                                    </select>
                                </div>
                            </div>
                            <div className="label-desc">리뷰 내용</div>
                            <div>
                                <textarea
                                    row="8"
                                    placeholder="리뷰 내용"
                                    id="description"
                                    value={menudesc}
                                    onChange={handleMenudesc}
                                    className="input-box-area"
                                />
                            </div>
                        </div>
                    </form>
                    <div className="button-box">
                        <button onClick={review_v1} className="button3">
                            추가
                        </button>
                        <Link to={"/mypage"} className="button">
                            <button className="button4">취소</button>
                        </Link>
                    </div>
                </div>
            </S.AddReviewWrap>
        </>
    );
};

export default AddReview;
