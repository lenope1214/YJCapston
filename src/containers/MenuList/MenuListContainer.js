import React, { useEffect, useState } from 'react';
import MenuList from '../../components/MenuList/MenuList';
import { useHistory } from 'react-router';
import { getMenuList } from '../../lib/MenuList';
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import Header from "../../components/Header/Header";
import { removeMenues } from "../../lib/MenuList/index"


const MenuListContainer = (props) => {
    const [menues, setMenues] = useState([
        {
            id: "",
            img: "",
        }
    ]);
    const history = useHistory();
    const [shopId, setShopId] = useState("");

    useEffect(() => {
        showMenuList(props.match.params.shopId);
        setShopId(props.match.params.shopId);
        console.log(menues);
    }, []);

    const showMenuList = () => {
        getMenuList(props.match.params.shopId)
            .then((res) => {
                setMenues(res.data);
                const menu = res.data.map((menu) => {
                    console.log(menu.id);
                    return {
                        img: menu.imgPath,
                        id: menu.id,
                        name: menu.name,
                        intro: menu.intro,
                        price: menu.price,
                    };
                });
                setMenues(menu);
            })
            .catch((err) => {
                alert("MenuListContainer Err");
            });
    };

    const removeMenu = (id) => {
        removeMenues(id)
            .then((res) => {
                alert("삭제되었습니다.");
                history.push(`/menulist/${shopId}`);
                window.location.reload();
            })
            .catch((err) => {
                alert("메뉴 삭제 에러");
            });
    };

    return (
        <>
            <Header />
            <OwnerNavbar 
                shopId={shopId}
            />

            <MenuList
                menues={menues}
                removeMenu={removeMenu}
                shopId={shopId}
            />
        </>
    );
};

export default MenuListContainer;

