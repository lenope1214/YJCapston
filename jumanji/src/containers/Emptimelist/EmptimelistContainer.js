import React, {useState, useEffect} from "react";
import Header from "../../components/Header/Header";
import Emptimelist from "../../components/Emptimelist/Emptimelist";
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import PosNavbar from "../../components/PosNavbar/PosNavbar";
import Empbar from "../../components/Empbar/Empbar";
import { useHistory } from "react-router-dom";
import { getempList, getemptimelist } from "../../lib/Emptimelist";

const EmptimelistContainer = (props) => {

    const[shopId, setShopId] = useState("");
    const [emps, setEmps] = useState([{}]);
    const [times, settimes] = useState([{}]);
    const [date, setDate] = useState("");
    const [empNo, setEmpNo] = useState("");
    const [startTime, setStartTime] = useState("");

    const handleDate = (e) => {
        const value = e.target.value;
        setDate(value);
        console.log(date);
    };
    const handleEmpNo = (e) => {
        const value = e.target.value;
        setEmpNo(value);
        console.log(empNo);
    }
    

    useEffect(() => {
        showEmpList(props.match.params.shopId);
        setShopId(props.match.params.shopId);
    }, []);

    const showEmpList = () => { //직원 리스트 가져오기
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

    const timelist = () => {
        getemptimelist(shopId,date,empNo,startTime)
        .then((res) => {
            // window.location.reload();
            settimes(res.data);

            const time = res.data.map((time) => {
                return {
                    shopId:time.shopId,
                    empNo:time.empNo,
                    empName:time.empName,
                    phone:time.phone,
                    startTime:time.startTime,
                    finishTime:time.finishTime,
                };
            });
            settimes(time);
            console.log(res.data);
        })
        .catch((err) => {
            alert("err");
        });
    };
        
    


console.log(startTime);
    return(
        <>
            <Header/>
            <OwnerNavbar shopId={shopId}/>
            <PosNavbar shopId={shopId}/>
            <Empbar shopId={shopId}/>
            <Emptimelist
                shopId={shopId}
                emps={emps}
                date={date}
                handleDate={handleDate}
                empNo={empNo}
                handleEmpNo={handleEmpNo}
                timelist={timelist}
                times={times}
            />
        </>
    );
};

export default EmptimelistContainer;