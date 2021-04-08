import React, { useEffect, useState } from "react";
import MyShop from "../../components/MyShop/MyShop";
import {getmyShop} from "../../lib/MyShop";
import { useHistory } from "react-router";
import Header from "../../components/Header/Header";
import { ShopsWrap } from "../../components/Shop/style";
import { removeShops } from "../../lib/MyShop/index"


const MyShopContainer = (props) => {
    const history = useState();
    const [shop, setShop] = useState([
        {
            id:"",
            img: "",
        }
    ]); 
    const [shopId, setShopId] =useState("");

    useEffect(() => {
    }, []);
    useEffect(() => {
        getmyShop()
        .then((res) => {

            setShop(res.data);
            
            const shop = res.data.map((shop) => {
                return {
                    img: shop.imgPath,
                    id: shop.id,
                    shopname:shop.name,
                    intro:shop.intro,
                    category:shop.category,
                    open_time:shop.openTime,
                    close_time:shop.closeTime,
                    address:shop.address,
                    addressDetail:shop.addressDetail,
                    isRsPos:shop.isRsPos
                };
            });
            setShop(shop);
            
        })
        .catch((err) => {
            alert("errrrr");
        });
    }, []);

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
        shop={shop}
        removeShop={removeShop}
        shopId={shopId}
        />
        </>
    );
};



export default MyShopContainer;
