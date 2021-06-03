import React, { useEffect, useState } from 'react';
import { useHistory } from 'react-router';
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import Header from "../../components/Header/Header";
import MenuRead from '../../components/MenuRead/MenuRead';
import { getMenuRead, putMenuPopular, putMenuSale, putMenuRead } from '../../lib/MenuRead';

export const MenuReadContainer = (props) => {
    const history = useHistory();
    const [shopId, setShopId] = useState("");
    const [menuRead, setMenuRead] = useState([]);
    const [menuPrice, setMenuPrice] = useState(null);
    const [menuImg, setMenuImg] = useState(null);
    const [menuIntro, setMenuIntro] = useState(null);
    const [menuId, setMenuId] = useState("");
    const [menuDuration, setMenuDuration] = useState(null);

    const handleMenuPrice = (e) => {
        const value = e.target.value;
        setMenuPrice(value)
    }
    const handleMenuIntro = (e) => {
        const value = e.target.value;
        setMenuIntro(value)
    }
    const handleMenuImg = (e) => {
        const value = e.target.value;
        setMenuImg(value);
    }

    const handleDuration = (e) => {
        const value = e.target.value;
        setMenuDuration(value)
    }
    

    const ReadMenu = () => {
        putMenuRead(
            menuPrice,
            menuIntro,
            menuId,
            menuDuration,
        )
            .then((res) => {
                history.goBack();
                alert("수정되었습니다.");
            })
            .catch((err) => {
                alert("putMenuRead Err");
            });
    };

    useEffect(() => {
        showMenuRead(props.match.params.menuId);
        setMenuId(props.match.params.id);
    }, []);

    const showMenuRead = () => {
        getMenuRead(props.match.params.menuId)
            .then((res) => {
                setMenuRead(res.data);
                console.log(menuRead.shopId);
            })
            .catch((err) => {
                console.log(props.match.params.menuId)
                alert("showMenuRead Err");
            });
    };
    const menu_v2 = () => {
        putMenuPopular(props.match.params.menuId,menuRead.shopId)
        .then((res)=> {
            
            alert("인기 여부 변경");
            // history.goBack();
            // history.push(`/menu/${menuId}`);
            window.location.reload();
        })
        .catch((err)=> {
            alert("인기여부 변경 에러");
        });

    };
    const menu_v3 = () => {
        putMenuSale(props.match.params.menuId,menuRead.shopId)
        .then((res)=> {
            // history.push(`/menu/${menuId}`);
            alert("판매 여부 변경");
            // history.goBack();
            window.location.reload();
        })
        .catch((err)=> {
            alert("판매 여부 변경 에러");
        });

    };

    const goBack = () => {
        history.goBack();
    }

    return (
        <div>
            <Header />
            <hr />
            <OwnerNavbar 
            shopId={shopId}/>
            <MenuRead
                shopId={menuRead.shopId}
                id={menuRead.id, console.log(menuId)}
                name={menuRead.name}
                price={menuRead.price}
                intro={menuRead.intro}
                menuId={menuId}
                menuPrice={menuPrice}
                handleMenuPrice={handleMenuPrice}
                img={menuRead.imgPath}
                handleMenuImg={handleMenuImg}
                menuIntro={menuIntro}
                handleMenuIntro={handleMenuIntro}
                ReadMenu={ReadMenu}
                goBack={goBack}
                menuDuration={menuDuration}
                duration={menuRead.duration}
                handleDuration={handleDuration}
                isPopular={menuRead.isPopular}
                isSale={menuRead.isSale}
                menu_v2={menu_v2}
                menu_v3={menu_v3}
            />
        </div>
    );
}