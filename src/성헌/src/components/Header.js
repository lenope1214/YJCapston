import React, { Component } from 'react';
import '../App.css';
import NowShop from './NowShop';

class Header extends Component {
    constructor(props) {
        super(props);
        this.state = {
          shop: { shop: '영진식당' }
        }
      }
    render() {
        return (
            <div className="black-nav">
                <div className="left-nav">
                    <a href="/">{this.props.title}<span style={{ fontSize: '23px', paddingTop: '10px' }}>의</span>{this.props.title2}</a>
                </div>
                {/* <SearchContent></SearchContent> */}
                <NowShop shop={this.state.shop.shop}></NowShop>
                <div className="right1-nav">{this.props.logon_out}</div>
                <div className="right2-nav">{this.props.logon_mypage}</div>
            </div>
        );
    }
}
export default Header;