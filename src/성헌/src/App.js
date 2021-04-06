import React, { Component } from 'react';
import Header from "./components/Header";
import './App.css';
import ShopInfo from "./components/ShopInfo";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      mode: 'read',
      selected_content_id:1,
      sajang: { title: '주민', title2: '사장님' },
      log: { logon_out: 'LOG OUT', logon_mypage: 'MY PAGE' },
      shop: { shop: '영진식당' },
      contents: [
        { id: 1, title: '매장정보', desc: '매장 정보가 비어있습니다.'},
        { id: 2, title: '메뉴정보', desc: '메뉴 정보가 비어있습니다.'},
        { id: 3, title: '리뷰이벤트', desc: '리뷰 이벤트가 비어있습니다.'}
      ]
    }
  }
  render() {
    var _desc = null;
    if(this.state.mode === 'read'){
    var i = 0;
    while( i < this.state.contents.length) {
      var data = this.state.contents[i];
      if(data.id === this.state.selected_content_id) {
        _desc = data.desc;
        break;
      }
      i = i + 1;
    }
  }
    return (
      <div className="App">
        <Header
          title={this.state.sajang.title}
          title2={this.state.sajang.title2}
          logon_out={this.state.log.logon_out}
          logon_mypage={this.state.log.logon_mypage}
          onChangePage={function(){
            this.setState({mode:'read'});    
          }.bind(this)}
        >
        </Header>
        <ShopInfo
        onChangePage={function(id){
          this.setState({
            mode:'read',
          selected_content_id:Number(id)
        });    
        }.bind(this)}
          data={this.state.contents}
          desc={_desc}
          ></ShopInfo>
      </div>
    );
  }
}

export default App;
