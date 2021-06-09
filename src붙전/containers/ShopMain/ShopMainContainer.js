import React, { useEffect, useState } from "react";
import ShopMain from "../../components/ShopMain/ShopMain";
import {getmyShop} from "../../lib/ShopMain";
import { useHistory } from "react-router";

const ShopMainContainer = () => {
    
    const [shop, setShop] = useState([]); 

    useEffect(()=> {   
    }, []);

    // 이게 서버에서 코드 받아오는 함수
    useEffect(() => {
        getmyShop()
        .then((res) => {

            setShop(res.data);
            
            const shop = res.data.map((shop) => {
                return {
                    id: shop.id,
                    shopname:shop.name,
                    intro:shop.intro,
                    open_time:shop.openTime,
                    close_time:shop.closeTime,
                    address:shop.address,
                    addressDetail:shop.addressDetail,
                    is_rs_pos:shop.isRsPos
                };
            });
            setShop(shop);
            console.log(shop);
        })
        .catch((err) => {
            alert("err");
        });
    }, []);

    return (

        <ShopMain shop={shop}/>
    );
}

//     useEffect(() => {
//         getshopmain();
//     },[]);

//     const getshopmain = () => {
//         getmyShop()
//         .then((res) => {
//             console.log(res.data);
//             setShop(res.data);


//         })
//         .catch((err) => {
//             console.log(err);
//         });
//     };

//     return <ShopMain 
//         shop={shop}
        
//         />;
// };


export default ShopMainContainer;
