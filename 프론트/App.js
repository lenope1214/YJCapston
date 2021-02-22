import logo from './logo.svg';
import './App.css';
import adimg1 from './adimg1.png';
import adimg2 from './adimg2.jpg';
import topimg from './topimg.jpg';
import juminicon from './주민아이콘.png';
import downimg from './다운로드.png';

function App() {
  return (
    <div className="App">
      <div className="black-nav">
        <div className="left-nav">주문<span style={{fontSize: '23px', paddingTop: '10px'}}>의</span>민족</div>
        <div className="center-nav">
          <input type="text" placeholder="매장을 검색하세요." style={{width: '300px',border: '0',borderRadius: '7px 0 0 7px'}}></input>
          <button style={{border: '0',borderRadius: '0 7px 7px 0', backgroundColor: 'white',color:'grey'}}>검색</button>
        </div>
        <div className="right1-nav">LOGIN</div>
        <div className="right2-nav">JOIN</div>
      </div>
      <div className="adimg">
        <div className="topimg"><img 
        src={topimg}
        width='100%'
        height='800px'
        />
        </div>
        <div className="toptext">
          <span>테이블</span><span style={{fontSize: '30px'}}>에서</span><br></br>
          <span>주문</span><span style={{fontSize: '30px'}}>하고</span><br></br>
          <span>집</span><span style={{fontSize: '30px'}}>에서</span><br></br>
          <span>예약</span><span style={{fontSize: '30px'}}>하자</span>
        </div>
        
        </div>
        <div className="but-item1">
          <div className="but-item1-text">
          <p>주문의민족</p>
          <p>모든</p>
          <p>기능</p>
          <p>이용하기</p>
          </div>
          <div className="but-item1-icon">
          <img src={juminicon} 
          width='200px'
          height='200px'
          />
          <div className="but-item1-but">
          <button style={{fontSize: '30px',fontFamily: 'Wemakeprice-Bold',height: '130px',width:'210px'
          ,borderRadius: '60px',border: 0}}>주민앱<br></br>
          다운로드<br></br>
          <img src={downimg} 
          width='40px'
          />
          </button>
          </div>
          </div>
          
        </div>
        <div className="but-item2"><p>사업자 페이지</p></div>
        <div className="but-item3"><p>예약하러 가기</p></div>
    </div>
  );
}

export default App;
