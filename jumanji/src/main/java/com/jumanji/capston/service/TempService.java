package com.jumanji.capston.service;

import com.jumanji.capston.data.Board;
import com.jumanji.capston.dto.BoardDto;
import com.jumanji.capston.repository.TempRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// 영속성 등록이 된 컴포넌트를 가져오는 방법이 2가지가 있음.
//  1. 객체를 final로 만들고, 주입받는 방법
//  2. Autowired 사용
@RequiredArgsConstructor // final로 선언된 객체 혹은 변수를 의존성 주입 해줌.
public class TempService {
    // private final TempRepository tempRepository; // 1번방법
    @Autowired
    TempRepository tempRepository;

    public List<Board> getAll(){ // 1개가 아니니까 List형식으로 반환
        List<Board> boardList = tempRepository.findAll();
        return boardList;
    }

    public void insert(BoardDto dto) {
        Board board = new Board(dto.getTitle(), dto.getContents());
        tempRepository.save(board);
    }

    public void update(BoardDto dto) {
        Board board = tempRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("안한지 오래됐어 모르겠다")); // findById는 Optional이라는 걸로 깜사서 줌.
        // null일수도 있고? null이 아닐수도 있는
        // 자바에서는 일반적으로 null과 연산을 하면 NullPointerException >> Null이랑 연산을 하면 안되니까 오류 났다고 알려줌.
        board.setTitle(dto.getTitle());
        board.setContents(dto.getContents());

        tempRepository.save(board); // insert , update
    }

    public void delete(BoardDto dto) {
        Board board = tempRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("안한지 오래됐어 모르겠다")); // findById는 Optional이라는 걸로 깜사서 줌.
        tempRepository.delete(board);
    }
}
