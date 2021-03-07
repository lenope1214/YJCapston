import { Route, BrowserRouter as Router, Switch } from "react-router-dom";
import "./App.css";
import LoginContainer from "./containers/Login/LoginContainer";
import MainContainer from "./containers/Main/MainContainer";
import RegisterContainer from "./containers/Register/RegisterContainer";

const App = () => {
    return (
        <Router>
            <Switch>
                <Route path="/login" component={LoginContainer} />
                <Route path="/register" component={RegisterContainer} />
                <Route path="/" component={MainContainer} />
            </Switch>
        </Router>
    );
};

export default App;
