import React, { useEffect, useState } from "react";
import MyShop from "../../components/MyShop/MyShop";
import {getmyShop} from "../../lib/MyShop";
import { useHistory } from "react-router";
import Header from "../../components/Header/Header";
import { ShopsWrap } from "../../components/Shop/style";
import { removeShops } from "../../lib/MyShop/index"


const MyShopContainer = (props) => {
    const history = useState();
    const [shops, setShops] = useState([
        {
            
            id:"",
            img: "",
        }
    ]); 
    const [shopId, setShopId] =useState("");

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
                    id: shop.id,
                    shopname:shop.name,
                    intro:shop.intro,
                    category:shop.category,
                    open_time:shop.openTime,
                    close_time:shop.closeTime,
                    address:shop.address,
                    addressDetail:shop.addressDetail,
                    isRsPos:shop.isRsPos,
                    isOpen:shop.isOpen
                };
            });
            setShops(shop);
        })
        .catch((err) => {
            alert("등록된 매장이 없습니다.");
        });
    }

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
        />
        </>
    );
};



export default MyShopContainer;
