import { apiDefault } from "../client";

export const postReview = (
    formData,
) => {
    alert("postReview함수 실행");
    return apiDefault().post("/reviews", {
        formData,
        
    }, {
        headers: {
            Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
            "content-type": "multipart/form-data",
        },
    });
};