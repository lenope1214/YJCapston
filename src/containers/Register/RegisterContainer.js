import axios from "axios";
import React, { useState } from "react";
import { useHistory } from "react-router-dom";

import Register from "../../components/Register/Register";
import { postLogin } from "../../lib/Login";
import { getLocation, postRegister, getIdCheck } from "../../lib/Register";

const RegisterContainer = () => {
    const history = useHistory();
    const [id, setId] = useState("");
    const [pw, setPw] = useState("");
    const [username, setUserName] = useState("");
    const [phone1, setPhone1] = useState("");
    const [phone2, setPhone2] = useState("");
    const [phone3, setPhone3] = useState("");
    const [owner, setOwner] = useState("");
    const [email, setEmail] = useState("");
    const [birthday, setBirthday] = useState("");
    const [address, setAddress] = useState("");
    const [address1, setAddress1] = useState("");
    const [keyword, setKeyword] = useState("");
    const [roadAddr, setRoadAddr] = useState("주소를 입력하세요.");
    const [showLocation, setShowLocation] = useState([
        {
            roadAddr: "",
        },
    ]);

    let check = "u";

    const [modal, setModal] = useState(false);

    const openmodal = () => {
        setModal(true);
        console.log("true");
    };
    const closemodal = () => {
        setModal(false);
        console.log("false");
    };

    if (owner === true) {
        check = "ROLE_OWNER";
        console.log("트류");
    } else {
        check = "ROLE_USER";
        console.log("폴스");
    }

    const phone = phone1 + phone2 + phone3;

    // 이메일 유효성 체크
    function validateEmail(email) {
        let re =
            /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
        return re.test(email);
    }

    //비밀번호 유효성체크 8 ~ 10자 영문, 숫자 조합
    function isPassword(pw) {
        let regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,10}$/; //  8 ~ 10자 영문, 숫자 조합
        return regExp.test(pw); // 형식에 맞는 경우 true 리턴
    }
    //아이디 유효성 체크  영문 대소문자와 숫자 4~12자리로 입력
    function validateId(id) {
        var re = /^[a-zA-Z0-9]{4,12}$/;
        return re.test(id);
    }

    // 이름 유효성 체크 2~5 글자 한글
    function isName(username) {
        let usernameRegExp = /^[가-힣]{2,5}$/;
        return usernameRegExp.test(username);
    }

    const handleId = (e) => {
        const value = e.target.value;
        setId(value);
    };

    const handlePw = (e) => {
        const value = e.target.value;
        setPw(value);
    };

    const handleName = (e) => {
        const value = e.target.value;
        setUserName(value);
    };
    const handlePhone1 = (e) => {
        const value = e.target.value;
        setPhone1(value);
    };
    const handlePhone2 = (e) => {
        const value = e.target.value;
        setPhone2(value);
    };
    const handlePhone3 = (e) => {
        const value = e.target.value;
        setPhone3(value);
    };
    const handleOwner = (e) => {
        const value = e.target.checked;
        setOwner(value);
    };
    const handleEmail = (e) => {
        const value = e.target.value;
        setEmail(value);
    };
    const handleBirthday = (e) => {
        const value = e.target.value;
        setBirthday(value);
    };

    const handleRoadAddr = (roadAddr) => {
        setRoadAddr(roadAddr);
        closemodal();
    };

    const handleAddress = (e) => {
        const value = e.target.value;
        setAddress(value);
    };
    const handleAddress1 = (e) => {
        const value = e.target.value;
        setAddress1(value);
    };

    const handleKeyword = (e) => {
        const value = e.target.value;
        setKeyword(value);
    };

    const register = async () => {
        // 유효성검사
        // if (!validateId(id)) {
        //     return alert(
        //         "아이디는 영문 대소문자와 숫자 4~12자리로 입력해야합니다!"
        //     );
        // }
        // if (!isPassword(pw)) {
        //     return alert(
        //         "비밀번호는 영문과 숫자 조합하여 8~10자리로 입력해야합니다!"
        //     );
        // }
        // if (!isName(username)) {
        //     return alert("이름을 제대로 적어주세요");
        // }
        // if (!validateEmail(email)) {
        //     return alert("이메일이 형식을 맞춰주세요");
        // }

        postRegister(
            id,
            pw,
            username,
            phone,
            check,
            email,
            birthday,
            address,
            address1,
            roadAddr
        )
            .then((res) => {
                history.push("/");
                alert("회원가입완료");
            })
            .catch((err) => err);
    };

    const IdCheck = () => {
        getIdCheck(id)
            .then((res) => {
                alert("사용 가능한 아이디 입니다.");
            })
            .catch((err) => {
                alert(err);
                alert("이미 존재하는 아이디 입니다.");
            });
    };

    const phoneauth = () => {
        var IMP = window.IMP; // 생략해도 괜찮습니다.
        IMP.init("imp59387591"); // "imp00000000" 대신 발급받은 "가맹점 식별코드"를 사용합니다.
        alert("아직 안돼요!");
        IMP.certification(
            {
                // param
                merchant_uid: "ORD20180131-0000011",
            },
            function (rsp) {
                // callback
                if (rsp.success) {
                    console.log("done");
                    // 인증 성공 시 로직,
                } else {
                    // 인증 실패 시 로직,
                    console.log("err");
                }
            }
        );
    };

    const handleComplete = (data) => {
        console.log(data);
        let fullAddress = data.address;
        let extraAddress = "";

        if (data.addressType === "R") {
            if (data.bname !== "") {
                extraAddress += data.bname;
            }
            if (data.buildingName !== "") {
                extraAddress +=
                    extraAddress !== ""
                        ? `, ${data.buildingName}`
                        : data.buildingName;
            }
            fullAddress += extraAddress !== "" ? ` (${extraAddress})` : "";
        }
        console.log(extraAddress);
        handleRoadAddr(fullAddress); // e.g. '서울 성동구 왕십리로2길 20 (성수동1가)'
    };

    return (
        <Register
            id={id}
            pw={pw}
            username={username}
            phone={phone}
            check={check}
            email={email}
            birthday={birthday}
            address={address}
            address1={address1}
            handleId={handleId}
            handlePw={handlePw}
            handleName={handleName}
            handlePhone1={handlePhone1}
            handlePhone2={handlePhone2}
            handlePhone3={handlePhone3}
            handleOwner={handleOwner}
            register={register}
            handleEmail={handleEmail}
            handleBirthday={handleBirthday}
            handleAddress={handleAddress}
            handleAddress1={handleAddress1}
            openModal={openmodal}
            closeModal={closemodal}
            modal={modal}
            handleKeyword={handleKeyword}
            keyword={keyword}
            IdCheck={IdCheck}
            showLocation={showLocation}
            roadAddr={roadAddr}
            handleComplete={handleComplete}
            phoneauth={phoneauth}
        />
    );
};

export default RegisterContainer;
