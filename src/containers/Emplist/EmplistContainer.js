import React, { useState, useEffect } from "react";
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import Header from "../../components/Header/Header";
import Empbar from "../../components/Empbar/Empbar";
import Emplist from "../../components/Emplist/Emplist";
import { useHistory } from "react-router-dom";
import { getempList, removeemps } from "../../lib/Emplist";
import moment from 'moment';
import 'moment/locale/ko';
import {useInterval} from 'react-use';
import PosNavbar from "../../components/PosNavbar/PosNavbar";
//npm i react-use   설치하기


const EmplistContainer = (props) => {
    const history = useHistory();
    const [shopId, setShopId] = useState("");
    const [empNo, setEmpNo] = useState("");
    const [emps, setEmps] = useState([{}]);

    const [realTime, setRealTime] = useState(Date.now());
    const nowTime = moment().format('YYYY-MM-DD HH:mm:ss');
   

    useInterval(() => {
        setRealTime(Date.now());
      }, 1000);

    const handleempNo = (e) => {
        const value = e.target.value;
        setEmpNo(value);
    };

    useEffect(() => {
        showEmpList(props.match.params.shopId);
        setShopId(props.match.params.shopId);
    }, []);

    const showEmpList = () => {
        getempList(props.match.params.shopId)
            .then((res) => {
                setEmps(res.data);

                const Emp = res.data.map((Emp) => {
                    return {
                        shopId: Emp.shopId,
                        empName: Emp.empName,
                        empNo: Emp.empNo,
                        birthday: Emp.birthday,
                        hiredate: Emp.hiredate,
                        phone: Emp.phone,
                        gender: Emp.gender,
                    };
                });
                setEmps(Emp);
                console.log(res.data);
            })
            .catch((err) => {
                alert("직원목록 에러.");
            });
    };

    const removeemp = () => {
        removeemps()
            .then((res) => {
                alert("직원삭제완료");
                history.push(`/emplist/${shopId}`);
                window.location.reload();
            })
            .catch((err) => {
                alert("직원삭제에러");
            });
    };

    return (
        <>
            <Header />
            <OwnerNavbar shopId={shopId} />
            <PosNavbar shopId={shopId}/>
            <Empbar shopId={shopId} />
            <Emplist
                shopId={shopId}
                emps={emps}
                removeemp={removeemp}
                empNo={empNo}
                handleempNo={handleempNo}
                nowTime={nowTime}
                realTime={realTime}
            />
        </>
    );
};

export default EmplistContainer;
