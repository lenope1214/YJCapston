
import React, { useEffect, useState } from 'react';
import { useHistory } from "react-router";
import Header from "../../components/Header/Header";
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import ShopInfo from "../../components/ShopInfo/ShopInfo";
import { getShopInfo, putShopreserve, putShopopen, putShopInfo} from '../../lib/ShopInfo';


const ShopInfoContainer = (props) => {
    const history = useHistory();
    const [shopInfo, setShopInfo] = useState({});
    const [shopId, setShopId] = useState("");
    const [shopName, setShopName] = useState(null);
    const [shopPhone, setShopPhone] = useState(null);
    const [shopIntro, setShopIntro] = useState(null);
    const [shopOpenTime, setShopOpenTime] = useState(null);
    const [shopCloseTime, setShopCloseTime] = useState(null);
    const [shopAddress, setShopAddress] = useState(null);
    const [shopAddressDetail, setShopAddressDetail] =useState(null);
    const [shopIsRsPos, setShopIsRsPos] = useState(null);
    const [shopCategory, setShopCategory] = useState(null);
    const [roadAddr, setRoadAddr] = useState("주소를 입력하세요.");
    const [modal, setModal] = useState(false);
    const [showLocation, setShowLocation] = useState([
        {
            roadAddr: "",
        },
    ]);
  
    const handleShopName = (e) =>{
        const value = e.target.value;
        setShopName(value);
    }
    const handleShopPhone = (e) => {
        const value= e.target.value;
        setShopPhone(value);
    }
    const handleShopIntro = (e) => {
        const value = e.target.value;
        setShopIntro(value);
    }
    const handleShopOpenTime =(e) => {
        const value = e.target.value;
        setShopOpenTime(value);
    }
    const handleShopCloseTime = (e) => {
        const value = e.target.value;
        setShopCloseTime(value);
    }
    const handleShopAddress = (e) => {
        const value = e.target.value;
        setShopAddress(value);
        console.log(value);
    }
    const handleShopAddressDetail = (e) => {
        const value = e.target.value;
        setShopAddressDetail(value);
        console.log(shopAddressDetail);
    }
    const handleShopIsRsPos = (e) => {
        const value = e.target.value;
        setShopIsRsPos(value);
    }
    const handleShopCategory = (e) => {
        const value = e.target.value;
        setShopCategory(value);
    }

    const openmodal = () => {
        setModal(true);
        console.log("true");
    };
    const closemodal = () => {
        setModal(false);
        console.log("false");
    };

    const handleRoadAddr = (roadAddr) => {
        setRoadAddr(roadAddr);
        closemodal();
    };


const Shop_v2 = () => {
    putShopreserve(
        shopId,
    )
    .then((res) => {
        // history.push("/shopInfo/"+shopId)
        history.push("/myshop")
        alert("예약여부변경완료");
    })
    .catch((err) => {
        alert("예약변경에러");
    });
};

const Shop_v3 = () => {
    putShopopen(
        shopId
    )
    .then((res) => {
        //history.push("/shopInfo/"+shopId)
        history.push("/myshop")
        alert("오픈여부변경완료");
    });
};

    //-----상세정보 수정 
    const Shop_v1 = () => {
        putShopInfo(
            shopId,
            shopIntro,
            shopOpenTime,
            shopCloseTime,
            // shopAddress,
            roadAddr,
            shopAddressDetail,
            // shopCategory
        )
        .then((res) => {
            history.push("/myshop")
            alert("수정되었습니다.");
        })
        .catch((err) => {
            alert("putshopInfo err");
        });
    };
   
    //-------선택한 아이디정보 가져오기
    useEffect(() => {
        ShowShopInfo(props.match.params.shopId);
        setShopId(props.match.params.shopId);
    },[]);
    
    const ShowShopInfo = () => {
        getShopInfo(props.match.params.shopId)
        .then((res) => {
            setShopInfo(res.data);
            setRoadAddr(res.data.address);
            console.log(res.data);
        })
        .catch((err) => {
            console.log(props.match.params.shopId);
            alert("showshopInfo err");
        });
    };

    //--------주소 api
    const handleComplete = (data) => {
        let fullAddress = data.address;
        let extraAddress = "";

        if (data.addressType === "R") {
            if (data.bname !== "") {
                extraAddress += data.bname;
            }
            if (data.buildingName !== "") {
                extraAddress +=
                    extraAddress !== ""
                        ? `, ${data.buildingName}`
                        : data.buildingName;
            }
            fullAddress += extraAddress !== "" ? ` (${extraAddress})` : "";
        }

        handleRoadAddr(fullAddress); // e.g. '서울 성동구 왕십리로2길 20 (성수동1가)'
    };

    const goBack = () => {
        history.goBack();
    }

    return(
        <>
        <Header />
        <OwnerNavbar 
        shopId={shopId}
        />   
        <ShopInfo
        id={shopInfo.id}
        name={shopInfo.name}
        intro={shopInfo.intro}
        openTime={shopInfo.openTime}
        closeTime={shopInfo.closeTime}
        category={shopInfo.category}
        img={shopInfo.imgPath}
        address={shopInfo.address}
        addressDetail={shopInfo.addressDetail}
        isRsPos={shopInfo.isRsPos}
        isOpen={shopInfo.isOpen}
        shopId={shopId}
        shopName={shopName}
        handleShopName={handleShopName}
        shopPhone={shopPhone}
        handleShopphone={handleShopPhone}
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
        Shop_v2={Shop_v2}
        Shop_v3={Shop_v3}
        handleComplete={handleComplete}
        roadAddr={roadAddr}
        handleRoadAddr={handleRoadAddr}
        modal={modal}
        openModal={openmodal}
        closeModal={closemodal}
        goBack={goBack}
        
        // handleShopreserve={handleShopreserve}
             />
        </>
    )

}


export default ShopInfoContainer;