import React, { useEffect, useState } from 'react';
import { useHistory } from 'react-router';
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import Header from "../../components/Header/Header";
import MenuRead from '../../components/MenuRead/MenuRead';
import { getMenuRead, putMenuPopular, putMenuSale, putMenuRead } from '../../lib/MenuRead';
import Swal from 'sweetalert2';

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
        setMenuDuration(value);
    }
    

    const ReadMenu = () => {
        putMenuRead(
            menuPrice,
            menuIntro,
            menuRead.menuId,
            menuDuration,
            menuRead.shopId
        )
            .then((res)=> {
                Swal.fire({
                    title: '메뉴 수정 성공',
                    text: "메뉴 정보가 수정되었습니다.",
                    icon: 'success',
                    // showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    // cancelButtonColor: '#d33',
                    confirmButtonText: '확인',
                    // cancelButtonText: '취소'
                  }).then((result) => {
                    if (result.value) {
                        history.goBack();
                    }
                  })
            })
            .catch((err)=> {
                Swal.fire({
                    title: '메뉴 수정 실패',
                    text: "메뉴 정보 수정에 실패했습니다.",
                    icon: 'error',
                    // showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    // cancelButtonColor: '#d33',
                    confirmButtonText: '확인',
                    // cancelButtonText: '취소'
                  }).then((result) => {
                    if (result.value) {

                    }
                  })
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
               
               
            })
            .catch((err) => {
                console.log(props.match.params.menuId)
                alert("showMenuRead Err");
            });
    };

    const menu_v2 = () => {
        putMenuPopular(props.match.params.menuId,menuRead.shopId)
        .then((res)=> {
            Swal.fire({
                title: '인기 메뉴 설정',
                text: "인기 메뉴 여부가 수정되었습니다.",
                icon: 'success',
                // showCancelButton: true,
                confirmButtonColor: '#3085d6',
                // cancelButtonColor: '#d33',
                confirmButtonText: '확인',
                // cancelButtonText: '취소'
              }).then((result) => {
                if (result.value) {
                    window.location.reload();
                }
              })
        })
        .catch((err)=> {
            Swal.fire({
                title: '인기 메뉴 설정 에러',
                text: "인기 메뉴 여부 수정 에러.",
                icon: 'error',
                // showCancelButton: true,
                confirmButtonColor: '#3085d6',
                // cancelButtonColor: '#d33',
                confirmButtonText: '확인',
                // cancelButtonText: '취소'
              }).then((result) => {
                if (result.value) {
                    window.location.reload();
                }
              })
        });
    };
    const menu_v3 = () => {
        putMenuSale(props.match.params.menuId,menuRead.shopId)
        .then((res)=> {
            Swal.fire({
                title: '판매 메뉴 설정',
                text: "판매 여부가 수정되었습니다.",
                icon: 'success',
                // showCancelButton: true,
                confirmButtonColor: '#3085d6',
                // cancelButtonColor: '#d33',
                confirmButtonText: '확인',
                // cancelButtonText: '취소'
              }).then((result) => {
                if (result.value) {
                    //"삭제" 버튼을 눌렀을 때 작업할 내용을 이곳에 넣어주면 된다. 
                    window.location.reload();
                }
              })
        })
        .catch((err)=> {
            Swal.fire({
                title: '판매 메뉴 설정 에러',
                text: "판매 메뉴 여부 수정 에러.",
                icon: 'error',
                // showCancelButton: true,
                confirmButtonColor: '#3085d6',
                // cancelButtonColor: '#d33',
                confirmButtonText: '확인',
                // cancelButtonText: '취소'
              }).then((result) => {
                if (result.value) {
                    //"삭제" 버튼을 눌렀을 때 작업할 내용을 이곳에 넣어주면 된다. 
                    window.location.reload();
                }
              })
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