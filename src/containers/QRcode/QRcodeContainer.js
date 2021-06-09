
import React, { useState, useEffect } from "react";
import Header from "../../components/Header/Header";
import QRcode from "../../components/QRcode/QRcode";
import OwnerNavbar from "../../components/OwnerMenubar/OwnerNavbar";
import { useHistory } from "react-router-dom";
import { postQRcode, getAddQR, } from "../../lib/QRcode";
import {removetables} from "../../lib/QRcode/index"

const QRcodeContainer = (props) => {
    const [AddQR, setAddQR] = useState({});
    const [shopId, setShopId] = useState("");
    const history = useHistory();
    const [seatQty, setseatQty] = useState("");
    const [no, setno] = useState("");
    const [tabno, settabno] = useState("");
    const [tabId, setTabId] = useState("");
    const [qrCodes, setQrCodes] = useState([
        {
            qrCode:""
        }
    ]);
    const handletabId = (e) => {
        const value = e.target.value;
        setTabId(value);
    }
    const handletabno = (e) => {
        const value = e.target.value;
        settabno(value);
    }

    const handleno = (e) => {
        const value = e.target.value;
        setno(value);
    }
    const handleseatQty = (e) => {
        const value = e.target.value;
        setseatQty(value);
    }
    

    const qrCode = `https://chart.apis.google.com/chart?cht=qr&chs=350x350&chl=http://3.34.55.186:3000/shopcontent/${shopId}/${tabno}`;


    const QR = () => {
        postQRcode(
            shopId,
            tabno,
            qrCode,
            seatQty

        )
            .then((res) => {
                history.push(`/qrcode/${shopId}`);
                alert("qr코드 생성완료");
                window.location.reload();
            })
            .catch((err) => {
                
                alert("post QRcode err");
               
            });
    };

    useEffect(() => {
        ShowAddQR(props.match.params.shopId);
        setShopId(props.match.params.shopId);
        
    }, []);

    const ShowAddQR = () => {
        getAddQR(props.match.params.shopId)
            .then((res) => {
                setQrCodes(res.data);
                const table = res.data.map((table) => {

                    return {
                        shopId: table.id,
                        no: table.no,
                        qrCode: table.qrCode,
                        seatQty: table.seatQty,
                        tabId:table.tabId
                    };
                });
                setQrCodes(table);
                console.log(res.data);
            })
            .catch((err) => {
                alert("showQRcode err");
            });
    };

    const removetable = (table) => {
        removetables(table)
        .then((res) => {
            alert("테이블QR코드 삭제완료");
            history.push(`/qrcode/${shopId}`);
            window.location.reload();
        })
        .catch((err) => {
            alert("테이블QR코드 삭제 에러");
        });
    };

    return (
        <>
            <Header />
            <OwnerNavbar
                shopId={shopId}
            />
            <QRcode
                shopId={shopId}
                QR={QR}
                no={no}
                qrCode={qrCode}
                seatQty={seatQty}
                handleno={handleno}
                handleseatQty={handleseatQty}
                AddQR={AddQR}
                qrCodes={qrCodes}
                tabId={tabId}
                removetable={removetable}
                handletabno={handletabno}
                handletabId={handletabId}
                

            />
        </>
    );
}
export default QRcodeContainer;