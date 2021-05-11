import React, { useState, useEffect } from "react";
import AddReview from "../../components/AddReview/AddReview";
import { useHistory } from "react-router";
import Header from "../../components/Header/Header";
import { apiDefault } from "../../lib/client";

const AddReviewContainer = (props) => {
    const history = useHistory();
    const [img, setImg] = useState(null);
    const [menudesc, setMenudesc] = useState("");
    const [score, setScore] = useState("");
    const [shopId, setShopId] = useState("");
    const [orderId, setOrderId] = useState("");

    useEffect(() => {
        setShopId(props.match.params.shopId);
    }, []);
    useEffect(() => {
        setOrderId(props.match.params.orderId);
    }, []);

    const handleImg = (e) => {
        const files = e.target.files[0];
        setImg(files);
    };

    const handleMenudesc = (e) => {
        const value = e.target.value;
        setMenudesc(value);
    };
    const handleScore = (e) => {
        const value = e.target.value;
        setScore(value);
    };

    const review_v1 = async () => {
        const formData = new FormData();
        formData.append("img", img);
        formData.append("orderId", orderId);
        formData.append("shopId", shopId);
        formData.append("content", menudesc);
        formData.append("score", score);
        
        const res = await apiDefault().post("/reviews",
            formData,
        {
            headers: {
                Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
                "content-type": "multipart/form-data",
            },
        }
        ).then((res) => {
            history.goBack();
            alert("리뷰가 등록되었습니다.");
        })
        .catch((err) => {
            alert("리뷰 등록 실패!");
        });
        console.log(res);
    };

    return (
        <>
        <Header />
        <hr />
        <AddReview 
            img={img}
            handleImg={handleImg}
            menudesc={menudesc}
            handleMenudesc={handleMenudesc}
            review_v1={review_v1}
            shopId={shopId}
            score={score}
            handleScore={handleScore}
        />
        </>
    );
}

export default AddReviewContainer;
