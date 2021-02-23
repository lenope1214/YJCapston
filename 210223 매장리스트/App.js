import logo from './logo.svg';
import './App.css';
import juminicon from './주민아이콘.png';
import pig from './카테고리/돼지고기.png';
import cow from './카테고리/소고기.png';
import koreanfood from './카테고리/한식.png';
import schoolfood from './카테고리/분식.png';
import japanesefood from './카테고리/돈까스.png';
import soup from './카테고리/찜탕.png';
import cafe from './카테고리/디저트.png';
import chicken from './카테고리/치킨.png';
import jokbo from './카테고리/족발.png';
import drink from './카테고리/주점.png';
import asian from './카테고리/아시안.png';
import chinesefood from './카테고리/중식.png'


function App() {
    return (
        <div className="App" style={{
                margin: '0px'
            }}>
            <div className="black-nav">
                <div className="left-nav">주문<span
                    style={{
                fontSize: '23px',
                paddingTop: '10px'
            }}>의</span>민족</div>
                <div className="center-nav">
                    <input
                        type="text"
                        placeholder="매장을 검색하세요."
                        style={{
                            width: '300px',
                            border: '0',
                            borderRadius: '7px 0 0 7px'
                        }}></input>
                    <button
                        style={{
                            border: '0',
                            borderRadius: '0 7px 7px 0',
                            backgroundColor: 'white',
                            color: 'grey'
                        }}>검색</button>
                </div>
                <div className="right1-nav">LOGIN</div>
                <div className="right2-nav">JOIN</div>
            </div>
            <div className="but-item1">
                <div className="but-item1-text"></div>
                <div className="but-item1-icon">
                    <div className="category">
                        <button
                            style={{
                                margin: '20px',
                                height: '200px',
                                width: '200px',
                                backgroundColor: 'white',
                                borderRadius: '8%',
                                
                            }}>
                            <img src={pig} width='150px' height='150px'/>
                            <br></br>돼지고기
                        </button>

                        <button
                            style={{
                                margin: '20PX',
                                height: '200px',
                                width: '200px',
                                backgroundColor: 'white',
                                borderRadius: '8%'
                            }}>
                            <img src={cow} width='150px' height='150px'/>
                            <br></br>소고기
                        </button>

                        <button
                            style={{
                                margin: '20PX',
                                height: '200px',
                                width: '200px',
                                backgroundColor: 'white',
                                borderRadius: '8%'
                            }}>
                            <img src={koreanfood} width='150px' height='150px'/>
                            <br></br>한식
                        </button>

                        <button
                            style={{
                                margin: '20PX',
                                height: '200px',
                                width: '200px',
                                backgroundColor: 'white',
                                borderRadius: '8%'
                            }}>
                            <img src={schoolfood} width='150px' height='150px'/>
                            <br></br>분식
                        </button>

                        <button
                            style={{
                                margin: '20PX',
                                height: '200px',
                                width: '200px',
                                backgroundColor: 'white',
                                borderRadius: '8%'
                            }}>
                            <img src={japanesefood} width='150px' height='150px'/>
                            <br></br>돈까스 회 일식
                        </button>

                        <button
                            style={{
                                margin: '20PX',
                                height: '200px',
                                width: '200px',
                                backgroundColor: 'white',
                                borderRadius: '8%'
                            }}>
                            <img src={asian} width='150px' height='150px'/>
                            <br></br>아시안
                        </button>

                        <button
                            style={{
                                margin: '20PX',
                                height: '200px',
                                width: '200px',
                                backgroundColor: 'white',
                                borderRadius: '8%'
                            }}>
                            <img src={chinesefood} width='150px' height='150px'/>
                            <br></br>중식
                        </button>

                        <button
                            style={{
                                margin: '20PX',
                                height: '200px',
                                width: '200px',
                                backgroundColor: 'white',
                                borderRadius: '8%'
                            }}>
                            <img src={drink} width='150px' height='150px'/>
                            <br></br>주점
                        </button>

                        <button
                            style={{
                                margin: '20PX',
                                height: '200px',
                                width: '200px',
                                backgroundColor: 'white',
                                borderRadius: '8%'
                            }}>
                            <img src={soup} width='150px' height='150px'/>
                            <br></br>찜탕
                        </button>

                        <button
                            style={{
                                margin: '20PX',
                                height: '200px',
                                width: '200px',
                                backgroundColor: 'white',
                                borderRadius: '8%'
                            }}>
                            <img src={cafe} width='150px' height='150px'/>
                            <br></br>카페 디저트
                        </button>

                        <button
                            style={{
                                margin: '20PX',
                                height: '200px',
                                width: '200px',
                                backgroundColor: 'white',
                                borderRadius: '8%'
                            }}>
                            <img src={chicken} width='150px' height='150px'/>
                            <br></br>치킨 피자
                        </button>

                        <button
                            style={{
                                margin: '20PX',
                                height: '200px',
                                width: '200px',
                                backgroundColor: 'white',
                                borderRadius: '8%'
                            }}>
                            <img src={jokbo} width='150px' height='150px'/>
                            <br></br>족발 보쌈
                        </button>
                        
                    </div>
                </div>
               
            </div>

        </div>
    );
}

export default App;
