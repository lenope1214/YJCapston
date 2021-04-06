import React, { useEffect, useState } from "react";
import axios from "axios";
import MenuList from "../../components/MenuList/MenuList";
import { useHistory } from "react-router";
import { getMenuList } from "../../lib/MenuList";
import MenuRead from "../../components/MenuRead/MenuRead";
import { getMenuRead } from "../../lib/MenuRead";
import { getNowMenu } from "../../lib/MenuRead";

export const MenuReadContainer = (props) => {
    // console.log('View: ', props);
    const [menuRead, setMenuRead] = useState(null);

    useEffect(() => {
        showMenuRead();
        console.log(props);
    }, []);

    const showMenuRead = () => {
        getMenuRead(props.match.params.shopId)
            .then((res) => {
                setMenuRead(res.data);
            })
            .catch((err) => {
                alert("err");
            });
    };

    return (
        <div>
            메뉴상세보기 :
            <MenuRead menuRead={menuRead} />
        </div>
    );
};
