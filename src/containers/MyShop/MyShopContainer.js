import React, { useEffect, useState } from "react";
import MyShop from "../../components/MyShop/MyShop";
import { getmyShop } from "../../lib/MyShop";
import { useHistory } from "react-router";
import Header from "../../components/Header/Header";
import { ShopsWrap } from "../../components/Shop/style";
import { removeShops } from "../../lib/MyShop/index";
import Swal from 'sweetalert2';

const MyShopContainer = (props) => {
    const history = useState();
    const [shops, setShops] = useState([
        {
            id: "",
            img: "",
        },
    ]);
    const [shopId, setShopId] = useState("");

    useEffect(() => {
        showShopList(props.match.params.shopId);
        setShopId(props.match.params.shopId);
    }, []);

    const showShopList = () => {
        getmyShop(props.match.params.shopId)
            .then((res) => {
                setShops(res.data);
                const shop = res.data.map((shop) => {
                    return {
                        img: shop.imgPath,
                        shopId: shop.shopId,
                        shopname: shop.name,
                        intro: shop.intro,
                        category: shop.category,
                        openTime: shop.openTime,
                        closeTime: shop.closeTime,
                        address: shop.address,
                        addressDetail: shop.addressDetail,
                        isRsPos: shop.isRsPos,
                        isOpen: shop.isOpen,
                    };
                });
                setShops(shop);
                console.log(res.data);
                // console.log(shopId);
            })
            .catch((err) => {
                const status = err?.response?.status;
                console.log(status);
                if (status == 400) {
                Swal.fire({
                    title: '로그인 오류',
                    text: "로그인이 필요합니다.",
                    icon: 'info',
                    // showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    // cancelButtonColor: '#d33',
                    confirmButtonText: '확인',
                    // cancelButtonText: '취소'
                  }).then((result) => {
                    if (result.value) {
                        history.push("/");
                    }
                  })
                    
                } else if (status == 404) {
                    Swal.fire({
                        title: '매장 로드 오류',
                        text: "등록하신 매장이 없습니다.",
                        icon: 'error',
                        // showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        // cancelButtonColor: '#d33',
                        confirmButtonText: '확인',
                        // cancelButtonText: '취소'
                      }).then((result) => {
                        if (result.value) {
                            history.push("/");
                        }
                      })
                } else {
                    Swal.fire({
                        title: '서버 문제',
                        text: "로그인 서버 에러",
                        icon: 'error',
                        // showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        // cancelButtonColor: '#d33',
                        confirmButtonText: '확인',
                        // cancelButtonText: '취소'
                      })
                }
            });
    };

    const removeShop = (id) => {
        removeShops(id)
            .then((res) => {
                alert("삭제되었습니다.");
                history.push(`/myshop/${shopId}`);
                window.location.reload();
            })
            .catch((err) => {
                alert("매장삭제에러");
            });
    };

    return (
        <>
            <Header />

            <MyShop 
            shops={shops} 
            removeShop={removeShop} 
            shopId={shopId} 
            isRsPos={shops.isRsPos}
            isOpen={shops.isOpen}
            />
        </>
    );
};

export default MyShopContainer;
