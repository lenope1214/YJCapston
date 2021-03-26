import React, { Component } from 'react';
import '../App.css';

class ShopInfo extends Component {
      render() {
          var lists = [];
          var data = this.props.data
          var i = 0;
          while(i < data.length){
            lists.push(<li key={data[i].id}><a href={"/content/"+data[i].id}
            data-id={data[i].id}
            onClick={function(e){
                
                e.preventDefault();
                this.props.onChangePage(e.target.dataset.id);
            }.bind(this)}>{data[i].title}</a></li>);
            i = i + 1;
          }
        return (
            <div className="shop-info-box">
                <div className="shop-info-title">매장 정보</div>
                <nav className="shop-info-select">
                    <ul>
                        {lists}
                    </ul>
                </nav>
                <div className="shop-info-desc">
                    <p>{this.props.desc}</p>
                </div>
                <div className="shop-info-but">
                    <button>등록</button>
                    <span><button>수정</button></span>
                    <button>삭제</button>
                </div>
            </div>
        );
    }
}
export default ShopInfo;