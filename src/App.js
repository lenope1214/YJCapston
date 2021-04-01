import { useEffect, useState } from "react";
import {
    Route,
    BrowserRouter as Router,
    Switch,
    useParams,
} from "react-router-dom";
import "./App.css";
import ShoplistContainer from "./containers/Shoplist/ShoplistContainer";
import LoginContainer from "./containers/Login/LoginContainer";
import MainContainer from "./containers/Main/MainContainer";
import MypageContainer from "./containers/MyPage/MyPageContainer";
import RegisterContainer from "./containers/Register/RegisterContainer";
import ShopContainer from "./containers/Shop/ShopContainer";
import ShopcontentContainer from "./containers/shopcontent/shopcontentcontainer";
import Fdc from "./containers/fd/fdC";

const App = () => {
    const [isLogin, setIsLogin] = useState(false);

    // let { shopid } = useParams();

    useEffect(() => {
        const accesstoken = sessionStorage.getItem("access_token");

        if (accesstoken) {
            setIsLogin(true);
        }
    }, []);

    // const shopid = () => {
    //     const { shopid } = useParams();
    //     return <div>{shopid}</div>;
    // };

    const handleLogin = () => {
        setIsLogin(true);
        alert("회원님 반가워요!");
    };

    const handleLogout = () => {
        setIsLogin(false);
        sessionStorage.removeItem("access_token");
        alert("로그아웃이 완료 되었습니다.");
    };

    return (
        <Router>
            <Switch>
                <Route path="/login" component={LoginContainer} />
                <Route path="/register" component={RegisterContainer} />
                <Route path="/mypage" component={MypageContainer} />
                <Route path="/shop" component={ShopContainer} />
                <Route path="/fd" component={Fdc} />

                <Route
                    path="/shoplist"
                    component={() => (
                        <ShoplistContainer
                            isLogin={isLogin}
                            handleLogin={handleLogin}
                            handleLogout={handleLogout}
                        />
                    )}
                />

                <Route
                    path="/shopcontent"
                    component={() => (
                        <ShopcontentContainer
                            isLogin={isLogin}
                            handleLogin={handleLogin}
                            handleLogout={handleLogout}
                        />
                    )}
                />

                <Route
                    path="/"
                    component={() => (
                        <MainContainer
                            isLogin={isLogin}
                            handleLogin={handleLogin}
                            handleLogout={handleLogout}
                        />
                    )}
                />
            </Switch>
        </Router>
    );
};

export default App;
