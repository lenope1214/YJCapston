import React, {useState, useEffect} from "react";
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import Header from "../../components/Header/Header";
import Empbar from "../../components/Empbar/Empbar";
import Employee from "../../components/Employee/Employee";
import { useHistory } from "react-router-dom";
import { postEmp,getShopInfo, } from "../../lib/Employee";
import PosNavbar from "../../components/PosNavbar/PosNavbar";

const EmployeeContainer = (props) => {
    
    const history = useHistory();
    const [shopInfo, setShopInfo] = useState({});
    const [roadAddr, setRoadAddr] = useState("주소를 입력하세요.");
    const [shopId, setShopId] = useState("");
    const [empNo, setEmpNo] = useState("");
    const [empName, setEmpName] = useState("");
    const [birthday, setBirthday] = useState("");
    const [hiredate, setHiredate] = useState("");
    const [phone1, setPhone1] = useState("");
    const [phone2, setPhone2] = useState("");
    const [phone3, setPhone3] = useState("");
    const [gender, setGender] = useState("");

    const handleempNo = (e) => {
        const value = e.target.value;
        setEmpNo(value);
    }
    const handleempName = (e) => {
        const value = e.target.value;
        setEmpName(value);
    }
    const handlebirthday = (e) => {
        const value = e.target.value;
        setBirthday(value);
    }
    const handlehiredate = (e) => {
        const value = e.target.value;
        setHiredate(value);
    }
   
    const handlephone1 = (e) => {
        const value = e.target.value;
        setPhone1(value);
    }
    const handlephone2 = (e) => {
        const value = e.target.value;
        setPhone2(value);
    }
    const handlephone3 = (e) => {
        const value = e.target.value;
        setPhone3(value);
    }
    const handlegender = (e) => {
        const value = e.target.value;
        setGender(value);
    }
    
    const phone= phone1 + phone2 + phone3;

    useEffect(() => {
        setShopId(props.match.params.shopId);
    },);
    console.log(shopId);

    const Emp = () => {
        postEmp(
            shopId,
            empNo,
            empName,
            birthday,
            hiredate,
            phone,
            gender,

        )
            .then((res) => {
                history.push(`/emplist/${shopId}`);
                alert("등록완료");
            })
            .catch((res) => {
                alert("직원등록 에러");
            });
    };

    useEffect(() => {
        ShowShopInfo(props.match.params.shopId);
        setShopId(props.match.params.shopId);
    }, []);

    const ShowShopInfo = () => {
        getShopInfo(props.match.params.shopId)
            .then((res) => {
                setShopInfo(res.data);
                setRoadAddr(res.data.address);
                console.log(res.data);
                
            })
            .catch((err) => {
                alert("showshopInfo err");
            });
    };
    return(
        <>
        <Header/>
        <OwnerNavbar
        shopId={shopId}/>
        <Empbar
        shopId={shopId}
        />
        <PosNavbar
        shopId={shopId}
        />
        <Employee
        shopId={shopId}
        Emp={Emp}
        empNo={empNo}
        empName={empName}
        birthday={birthday}
        hiredate={hiredate}
        phone={phone}
        gender={gender}
        handleempNo={handleempNo}
        handleempName={handleempName}
        handlebirthday={handlebirthday}
        handlehiredate={handlehiredate}
        // handlePhone={handlePhone}
        handlephone1={handlephone1}
        handlephone2={handlephone2}
        handlephone3={handlephone3}
        handlegender={handlegender}

        name={shopInfo.name}
        />
        </>
    );
};

export default EmployeeContainer;
