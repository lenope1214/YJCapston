import React, { Component } from 'react';
import '../App.css';

class NowShop extends Component {
    render() {
        return (
            <div className="center-nav-nowshop">
               현재 매장 :<span style={{fontSize:'22px', marginTop:'-2px', marginLeft: '10px'}}>{this.props.shop}</span>
               <button>∨</button>
            </div>
        );
    }
}
export default NowShop;
