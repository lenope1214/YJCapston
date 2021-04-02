import React, { useEffect, useState } from 'react';
import axios from "axios";
import MenuList from '../../components/MenuList/MenuList';
import { useHistory } from 'react-router';
import { getMenuList } from '../../lib/MenuList';
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import Header from "../../components/Header/Header";
import MenuRead from '../../components/MenuRead/MenuRead';
import { getMenuRead, putMenuRead } from '../../lib/MenuRead';
import { getNowMenu } from '../../lib/MenuRead';


export const MenuReadContainer = (props) => {
    // console.log('View: ', props);
    const history = useHistory();
    const [menuRead, setMenuRead] = useState([]);
    const [menuName, setMenuName] = useState("");
    const [menuPrice, setMenuPrice] = useState("");
    const [menuImg, setMenuImg] = useState("");
    const [menuIntro, setMenuIntro] = useState("");
    const [menuId, setMenuId] = useState("");

    const handleMenuName = (e) => {
        const value = e.target.value;
        setMenuName(value)
    }
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

    const ReadMenu = () => {
        putMenuRead(
                menuName,
                menuPrice,     
                menuIntro,
                menuId,
                )
            .then((res) => {
                history.push("/menulist");
                alert("수정되었습니다.");
            })
            .catch((err) => {
                console.log(menuId, menuName, menuPrice, menuIntro);
                alert("putMenuRead Err");
            });
    };

    useEffect(() => {
        showMenuRead(props.match.params.shopId);
        setMenuId(props.match.params.shopId);
    }, []);
  
    const showMenuRead = () => {
        getMenuRead(props.match.params.shopId)
            .then((res) => {
                setMenuRead(res.data);
            })
            .catch((err) => {
                alert("showMenuRead Err");
            });          
    };

   

    return(
        <div>
            <Header />
            <OwnerNavbar />
            <MenuRead 
                // menuRead={menuRead}
                id={menuRead.id, console.log(menuId)}
                name={menuRead.name}
                price={menuRead.price}
                intro={menuRead.intro}
                menuId={menuId}
                menuName={menuName}
                handleMenuName={handleMenuName}
                menuPrice={menuPrice}
                handleMenuPrice={handleMenuPrice}
                menuImg={menuImg}
                handleMenuImg={handleMenuImg}
                menuIntro={menuIntro}
                handleMenuIntro={handleMenuIntro}
                ReadMenu={ReadMenu}
            />
        </div>
    );
}