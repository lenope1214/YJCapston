import React, { useEffect, useState } from "react";
import { useHistory } from "react-router";
import Header from "../../components/Header/Header";
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import ShopInfo from "../../components/ShopInfo/Shopinfo";
import { getShopInfo, getShopList, putShopInfo } from "../../lib/ShopInfo";

const ShopInfoContainer = (props) => {
    const history = useHistory();
    const [shopInfo, setShopInfo] = useState([]);
    const [shopId, setShopId] = useState("");
    const [shopName, setShopName] = useState(null);
    const [shopIntro, setShopIntro] = useState(null);
    const [shopOpenTime, setShopOpenTime] = useState(null);
    const [shopCloseTime, setShopCloseTime] = useState(null);
    const [shopAddress, setShopAddress] = useState(null);
    const [shopAddressDetail, setShopAddressDetail] = useState(null);
    const [shopIsRsPos, setShopIsRsPos] = useState(null);
    const [shopCategory, setShopCategory] = useState(null);

    const handleShopName = (e) => {
        const value = e.target.value;
        setShopName(value);
    };
    const handleShopIntro = (e) => {
        const value = e.target.value;
        setShopIntro(value);
    };
    const handleShopOpenTime = (e) => {
        const value = e.target.value;
        setShopOpenTime(value);
    };
    const handleShopCloseTime = (e) => {
        const value = e.target.value;
        setShopCloseTime(value);
    };
    const handleShopAddress = (e) => {
        const value = e.target.value;
        setShopAddress(value);
    };
    const handleShopAddressDetail = (e) => {
        const value = e.target.value;
        setShopAddressDetail(value);
    };
    const handleShopIsRsPos = (e) => {
        const value = e.target.value;
        setShopIsRsPos(value);
    };
    const handleShopCategory = (e) => {
        const value = e.target.value;
        setShopCategory(value);
    };

    const Shop_v1 = () => {
        putShopInfo(
            shopId,
            shopIntro,
            shopOpenTime,
            shopCloseTime,
            shopAddress,
            shopAddressDetail,
            shopCategory
        )
            .then((res) => {
                history.push("/myShop");
                alert("수정되었습니다.");
            })
            .catch((err) => {
                alert("putshopInfo err");
            });
    };

    useEffect(() => {
        ShowShopInfo(props.match.params.shopId);
        setShopId(props.match.params.shopId);
    }, []);

    const ShowShopInfo = () => {
        getShopInfo(props.match.params.shopId)
            .then((res) => {
                setShopInfo(res.data);
            })
            .catch((err) => {
                alert("showshopInfo err");
            });
    };

    return (
        <>
            <Header />
            <OwnerNavbar shopId={shopId} />
            <ShopInfo
                id={shopInfo.id}
                name={shopInfo.name}
                intro={shopInfo.intro}
                openTime={shopInfo.openTime}
                closeTime={shopInfo.closeTime}
                category={shopInfo.category}
                address={shopInfo.address}
                addressDetail={shopInfo.addressDetail}
                isRsPos={shopInfo.isRsPos}
                shopId={shopId}
                shopName={shopName}
                handleShopName={handleShopName}
                shopIntro={shopIntro}
                handleShopIntro={handleShopIntro}
                shopOpenTime={shopOpenTime}
                handleShopOpenTime={handleShopOpenTime}
                shopCloseTime={shopCloseTime}
                handleShopCloseTime={handleShopCloseTime}
                shopAddress={shopAddress}
                handleShopAddress={handleShopAddress}
                shopAddressDetail={shopAddressDetail}
                handleShopAddressDetail={handleShopAddressDetail}
                shopIsRsPos={shopIsRsPos}
                handleShopIsRsPos={handleShopIsRsPos}
                shopCategory={shopCategory}
                handleShopCategory={handleShopCategory}
                Shop_v1={Shop_v1}
            />
        </>
    );
};

export default ShopInfoContainer;
