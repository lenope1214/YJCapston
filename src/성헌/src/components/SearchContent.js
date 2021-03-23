import React, { Component } from 'react';
import '../App.css';

class SearchContent extends Component {
    render() {
        return (
            <div className="center-nav">
                <input type="text" placeholder="매장을 검색하세요." style={{ width: '300px', border: '0', borderRadius: '7px 0 0 7px' }} />
                <button style={{ border: '0', borderRadius: '0 7px 7px 0', backgroundColor: 'white', color: 'grey' }}>검색</button>
            </div>
        );
    }
}
export default SearchContent;
