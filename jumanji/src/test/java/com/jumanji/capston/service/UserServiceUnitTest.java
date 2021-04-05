//package com.jumanji.capston.service;
//
//// 서비스에서는 메소드 말고 필드에있는거만 IoC에 띄워주면 됨. 필드에 있는 Repository 같은 것들이 없으면 안되니까.
//
//
//import com.jumanji.capston.repository.UserRepository;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
///**
// *
// * @author LSB
// * 단위 테스트 ( Service와 관련된 애들만 메모리에 올리면 됨)
// * UserRepository => 가짜 객체로 만들 수 있음.
// */
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceUnitTest {
//
//    // 그냥 @Mock 해서 하면은 Mockito 의 UserService의 UserREpository는 null이기 떄문에
//    // @InjectMocks 로 선언.
//    // InjectMocks가 있는 해당 파일에 @Mock로 등록된 모든 애들을 주입받는다. 여기선 UserRepository가 해당.
//    @InjectMocks
//    private UserService userService;
//
//    @Mock
//    private UserRepository userRepository;
//}