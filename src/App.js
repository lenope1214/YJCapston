
import { Route, BrowserRouter as Router, Switch } from "react-router-dom";
import "./App.css";
import LoginContainer from "./containers/Login/LoginContainer";
import MainContainer from "./containers/Main/MainContainer";
import MypageContainer from "./containers/MyPage/MyPageContainer";
import RegisterContainer from "./containers/Register/RegisterContainer";
import ShopContainer from "./containers/Shop/ShopContainer";
import ShopMainContainer from "./containers/ShopMain/ShopMainContainer";
import ShoplistContainer from "./containers/Shoplist/ShoplistContainer";
import AddShopContainer from "./containers/AddShop/AddShopContainer";
import { useEffect, useState } from "react";
    
const App = () => {
    const [isLogin,setIsLogin] = useState(false);

    useEffect(() => {
        const accesstoken = localStorage.getItem("access_token");

        if (accesstoken) {
            setIsLogin(true);
        }
    }, []);

    const handleLogin = () => {
        setIsLogin(true);
    };

    const handleLogout = () => {
        setIsLogin(false);
    };
    return (
        <Router>
            <Switch>
                <Route path="/login" component={LoginContainer} />
                <Route path="/register" component={RegisterContainer} />
                <Route path="/mypage" component={MypageContainer} />
                <Route path="/shopMain" component={ShopMainContainer} />
                <Route path="/shop" component={ShopContainer} />
                <Route path="/shoplist" component={ShoplistContainer} />
                <Route path="/addshop" component={AddShopContainer} />
                {/* <Route path="/" component={MainContainer} /> */}
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
