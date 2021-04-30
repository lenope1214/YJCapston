//package com.jumanji.capston.repository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *  단위 테스트 ( DB 관련 Bean이 IoC(메모리)에 등록되면 됨)
// * @DataJpaTest => jpa 관련된 애들만 띄운다.
// * 실제 데이터베이스로 할건지, 가짜로 할건지 =>
// *  가짜 db : @AutoConfigureTestDatabase(replace = Replace.ANY) 단위테스트에 적용
// *  실제 db : @AutoConfigureTestDatabase(replace = Replace.NONE) 통합테스트에 적용하면 굿
// *
// */
//
//@Transactional
//@AutoConfigureTestDatabase(replace = Replace.ANY)
//@DataJpaTest // 얘는 Repository들을 다 IoC에 띄워줌. 원하는 Repo만 띄울수도 있다함.
//public class UserRepositoryUnitTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//}
