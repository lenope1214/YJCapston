import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router";
import Table from "../../components/Table/Table";
import { getshopmenu, postorder, getorderlist } from "../../lib/Table/index";

const TableContainer = () => {
    const param = useParams();

    const history = useHistory();
    const [menu, setMenu] = useState([]);
    const [jmMenu, setJmMenu] = useState([]);
    let sum = 0;
    let pricesum = 0;
    let [count, setCount] = useState(0);
    const [tablepeople, setTablepeople] = useState();
    const [yetjmMenu, setYetjmMenu] = useState([]);
    const [orderId, setOrderId] = useState();

    useEffect(() => {
        getmenu();
        getorderlistshow();
    }, []);

    const plus = () => {
        setCount(count + 1);
    };

    const minus = () => {
        setCount(count - 1);
    };

    const getorderlistshow = () => {
        getorderlist(param.shopId)
            .then((res) => {
                const tabletest = res.data.map((tabletest) => {
                    if (
                        !(tabletest.table_id == null) &&
                        tabletest.table_id.substring(10, 12) ==
                            param.tableamount &&
                        tabletest.status == "rd"
                    ) {
                        setOrderId(tabletest.orderId);

                        const tablelist = tabletest.orderMenuList.map(
                            (test) => {
                                return {
                                    name: test.menuName,
                                    price: test.menuPrice,
                                    menuId: test.orderMenuId,
                                    count: test.quantity,
                                };
                            }
                        );
                        setYetjmMenu(tablelist);
                    }
                });

                // });

                // const tablenum = res.data.map((tablenum) => {
                //     return {
                //         tableno: tablenum.table.id.substring(10, 12),
                //         tablepeople: tablenum.people,
                //     };
                // });
                // console.log(tablenum);

                // tablenum.map((tablelist) => {
                //     if (tablelist.tableno == param.tableamount) {
                //         console.log(tablelist.tablepeople);
                //         setCount(tablelist.tablepeople);
                //     }
                // });

                // for (let i = 0; i < tablenum.length; i++) {
                //     if (tablenum.tableno[i] == param.tableamount) {
                //         console.log(tablenum.tableno[i]);
                //     }
                // }

                // console.log(res.data.table.substring(10, 12));
            })
            .catch((err) => {
                console.log(err);
            });
    };

    const handleMenu = (name, price, menuId) => {
        let a = 1;
        if (a === 1) {
            setJmMenu([...jmMenu, { name, price, menuId, count: 1 }]);
            a = 2;
        }

        for (let i = 0; i < jmMenu.length; i++) {
            if (jmMenu[i].name === name) {
                const copy = [...jmMenu];
                copy[i].count++;
                sum = jmMenu[i].count * jmMenu[i].price;
                setJmMenu(copy);
                break;
            }
        }
    };
    const handleDeleteMenu = (name) => {
        const filteredMenu = jmMenu.filter(({ name: filtername }) => {
            return name !== filtername;
        });

        setJmMenu(filteredMenu);
    };

    const getmenu = () => {
        getshopmenu(param.shopId)
            .then((res) => {
                const getmenulist = res.data.map((getmenulist) => {
                    return {
                        name: getmenulist.name,
                        img: getmenulist.imgPath,
                        intro: getmenulist.intro,
                        price: getmenulist.price,
                        isPopular: getmenulist.isPopular,
                        menuId: getmenulist.menuId,
                    };
                });
                setMenu(getmenulist);
            })
            .catch((err) => {
                console.log(err);
            });
    };

    const reload = () => {
        localStorage.setItem("sitejmlist", JSON.stringify(jmMenu));
        localStorage.setItem(`posnumber`, param.tableamount);

        history.push(`/pos/${param.shopId}`);
    };
    return (
        <Table
            menu={menu}
            handleMenu={handleMenu}
            jmMenu={jmMenu}
            handleDeleteMenu={handleDeleteMenu}
            reload={reload}
            plus={plus}
            minus={minus}
            count={count}
            yetjmMenu={yetjmMenu}
            tablepeople={tablepeople}
            orderId={orderId}
        />
    );
};

export default TableContainer;
