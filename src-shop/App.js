import { useEffect, useState } from "react";
import { Route, BrowserRouter as Router, Switch } from "react-router-dom";
import "./App.css";
import ShoplistContainer from "./containers/Shoplist/ShoplistContainer";
import LoginContainer from "./containers/Login/LoginContainer";
import MainContainer from "./containers/Main/MainContainer";
import MypageContainer from "./containers/MyPage/MyPageContainer";
import RegisterContainer from "./containers/Register/RegisterContainer";
import ShopMainContainer from "./containers/ShopMain/ShopMainContainer";
import ShopContainer from "./containers/Shop/ShopContainer";

const App = () => {
    const [isLogin, setIsLogin] = useState(false);

    useEffect(() => {
        const accesstoken = sessionStorage.getItem("access_token");

        if (accesstoken) {
            setIsLogin(true);
        }
    }, []);

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
                <Route path="/shopMain" component={ShopMainContainer} />
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
