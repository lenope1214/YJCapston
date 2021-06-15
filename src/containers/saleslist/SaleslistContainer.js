import React from "react";
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import Header from "../../components/Header/Header";
import PosNavbar from "../../components/PosNavbar/PosNavbar";
import Saleslist from "../../components/saleslist/Saleslist";
import { useEffect, useState } from "react";
import { getshoporder, getShopInfo,getmonsearch,getnowsearch, getnowweeksearch,getnowmonthsearch,getbeforesearch,getnextsearch,getweeksearch,getmonthsearch,gettermsearch,getdaysearch} from "../../lib/saleslist/index";
import moment from 'moment';
import 'moment/locale/ko';

const SaleslistContainer = (props) => {
    const [shopId, setShopId] = useState(props.match.params.shopId);
    const [allamount, setallmount] = useState([]);
    const [a, b] = useState([]);
    const [shopInfo, setShopInfo] = useState({});
    const [month, setMonth] = useState({});
    const [month2, setMonth2] = useState({});
    const [month3, setMonth3] = useState({});
    const [month4, setMonth4] = useState({});
    const [sales, setsales] = useState({});
    const [RSsales, setRSsales] = useState({});
    const [sales1, setsales1] = useState({});
    const [RSsales1, setRSsales1] = useState({});
    const [sales2, setsales2] = useState({});
    const [RSsales2, setRSsales2] = useState({});
    const [sales3, setsales3] = useState({});
    const [nowDate1, setnowDate1] = useState({});
    const [resultDate, setresultDate]= useState("");
    const [resultDate2, setresultDate2] = useState("");
    const [resultDate3, setresultDate3] = useState("");
    const [resultDate4, setresultDate4] = useState("");
    const [resultDate5, setresultDate5] = useState("");


    // const [realTime, setRealTime] = useState(Date.now());
    const nowDate = moment().format('YYYY-MM-DD');
    
    
    const handlemonth = (e) => {
        const value = e. target.value;
        setMonth(value);
    }
    const handlemonth2 = (e) => {
        const value = e. target.value;
        setMonth2(value);
    }
    const handlemonth3 = (e) => {
        const value = e. target.value;
        setMonth3(value);
    }
    const handlemonth4 = (e) => {
        const value = e. target.value;
        setMonth4(value);
    }
    const handlenowDate = (e) => {
        const value = e. target.value;
        setnowDate1(value);
    }
    


    useEffect(() => {
        setShopId(props.match.params.shopId);
        shoporderlist();
        ShowShopInfo(props.match.params.shopId);
        setShopId(props.match.params.shopId);
        nowsearch();
        nowweeksearch();
        nowmonthsearch();
    }, []);
    console.log(shopId);

    const shoporderlist = () => {
        getshoporder(shopId)
            .then((res) => {
                const sjmamount = res.data.map((sjmamount) => {
                    return {
                        jmmount: sjmamount.amount,
                    };
                });

                setallmount(sjmamount);
                console.log(sjmamount);
            })
            .catch((err) => {
                console.log(err);
            });
    };
    console.log(allamount.length);

    let x = allamount.length;

    const test = allamount.map((test) => {
        return test.jmmount;
    });
    console.log(test);
    // b(test);
    // console.log(a);
    // const sum = allamount.reduce((a, b) => a + b);
    // console.log(sum);
   
    const ShowShopInfo = () => {
        getShopInfo(props.match.params.shopId)
            .then((res) => {
                setShopInfo(res.data);
                console.log(res.data);
                
            })
            .catch((err) => {
                alert("showshopInfo err");
            });
    };
    
    //오늘 매출조회
    const nowsearch = () => {
        getnowsearch(
            shopId,
            nowDate,
            )
            .then((res) => {
                setsales(res.data);
                // alert("오늘");
            })
            .catch((err) => {
                alert(err);
            });
    };

    
    //해당 일 조회
    const daysearch = () => {
        getdaysearch(
            shopId,
            nowDate1,
        )
        .then((res) => {
            setRSsales(res.data);
            setresultDate(nowDate1);
        })
        .catch((err) => {
            alert(err);
        });
    };
    
    //전일 매출조회하기 버튼 눌렀을때
    // const beforesearch = () => {
    //     const beforeDate = moment().subtract(1,'days').format('YYYY-MM-DD');
    //     getbeforesearch(
    //         shopId,
    //         beforeDate,
    //     )
    //     .then((res) => {
    //         setsales1(res.data);
    //         alert(beforeDate);
    //     })
    //     .catch((err) => {
    //         alert(err);
    //     });
    // };

    //후일 매출조회하기 버튼 눌렀을 때
    // const nextsearch = () => {
    //     const nextDate = moment().add(1,'days').format('YYYY-MM-DD');
    //     getnextsearch(
    //         shopId,
    //         nextDate,
    //     )
    //     .then((res) => {
    //         setsales(res.data);
    //         alert(nextDate);
            
    //     })
    //     .catch((err) => {
    //         alert("오늘 매출 조회 에러");
    //     });
    // };

   

    //이번주 조회하기
    const nowweeksearch = () => {
        getnowweeksearch(
            shopId,
            nowDate,
        )
        .then((res) => {
            setsales1(res.data);
            
        })
        .catch((err) => {
            alert(err);
        });
    };

    //해당주 조회하기 버튼 눌렀을 때
    const weeksearch = () => {
        getweeksearch (
            shopId,
            month3,
        )
        .then((res) => {
            setRSsales1(res.data);
            setresultDate2(month3);
            alert(month3);
        })
        .catch((err) => {
            alert(err);
        })
    }

    //이번달 조회하기
    const nowmonthsearch = () => {
        getnowmonthsearch(
            shopId,
            nowDate,
        )
        .then((res) => {
            setsales2(res.data);          
        })
        .catch((err) => {
            alert("해당월 조회 에러");
        });
    };

    //해당 월 조회하기
    const monthsearch = () => {
        getmonthsearch(
            shopId,
            month4,
        )
        .then((res) => {
            setRSsales2(res.data);
            setresultDate3(month4);
            alert(month4);
        })
        .catch((err) => {
            alert(err);
        });
    };

    //해당기간 조회하기
    const termsearch = () => {
        gettermsearch(
            shopId,
            month,
            month2,
        )
        .then((res) => {
            setsales3(res.data);
            setresultDate4(month);
            setresultDate5(month2);
            alert(month+"~"+month2);
        })
        .catch((err) => {
            alert(err);
        });
        
    };

    
    return (
        <>
            <Header />
            <OwnerNavbar shopId={shopId} />
            <PosNavbar shopId={shopId}/>
            <Saleslist 
                shopId={shopId} 
                x={x} 
                name={shopInfo.name}
                month={month}
                handlemonth={handlemonth}
                month2={month2}
                handlemonth2={handlemonth2}
                month3={month3}
                handlemonth3={handlemonth3}
                month4={month4}
                handlemonth4={handlemonth4}
                nowDate1={nowDate1}
                handlenowDate={handlenowDate}
                // nextsearch={nextsearch}
                // beforesearch={beforesearch}
                weeksearch={weeksearch}
                monthsearch={monthsearch}
                nowweeksearch={nowweeksearch}
                nowmonthsearch={nowmonthsearch}
                sales={sales}
                sales1={sales1}
                sales2={sales2}
                sales3={sales3}
                nowDate={nowDate}
                termsearch={termsearch}
                nowDate1={nowDate1}
                daysearch={daysearch}
                resultDate={resultDate}
                resultDate2={resultDate2}
                resultDate3={resultDate3}
                resultDate4={resultDate4}
                resultDate5={resultDate5}
                RSsales={RSsales}
                RSsales1={RSsales1}
                RSsales2={RSsales2}
            />
        </>
    );
};

export default SaleslistContainer;
