import React, { useEffect, useState } from "react";
import MyShop from "../../components/MyShop/MyShop";
import { getmyShop } from "../../lib/MyShop";
import { useHistory } from "react-router";

const MyShopContainer = () => {
    const [shop, setShop] = useState([]);
    useEffect(() => {
    }, []);
    // 이게 서버에서 코드 받아오는 함수
    useEffect(() => {
        getmyShop()
            .then((res) => {
                setShop(res.data);
                const shop = res.data.map((shop) => {
                    return {
                        id: shop.id,
                        shopname: shop.name,
                        intro: shop.intro,
                        open_time: shop.openTime,
                        close_time: shop.closeTime,
                        address: shop.address,
                        addressDetail: shop.addressDetail,
                        is_rs_pos: shop.isRsPos
                    };
                });
                setShop(shop);

            })
            .catch((err) => {
                alert("err");
            });
    }, []);

    return (

        <MyShop shop={shop} />
    );
}

export default MyShopContainer;
