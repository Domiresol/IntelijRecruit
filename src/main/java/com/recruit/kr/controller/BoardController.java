package com.recruit.kr.controller;

import com.recruit.kr.domain.board.Board;
import com.recruit.kr.domain.board.BoardDTO;
import com.recruit.kr.domain.board.Board_JPAREPO;
import com.recruit.kr.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final Board_JPAREPO boardJparepo;
    private final BoardService boardService;

    //여기에 쓰인 이름은 가칭이므로 추후 변동 가능
    @GetMapping("/api/board/{boardWantedRole}")
    public List<Board> getBoard(@PathVariable String boardWantedRole){
        switch (boardWantedRole){
            case "프론트엔드": // 프론트 엔드 항목으로 글 출력

            case "백엔드": // 백엔드 항목으로 글 출력

            case "보안": // 보안 항목으로 글 출력

            case "AI": // AI 항목으로 글 출력
                return boardJparepo.findAllByBoardWantedRoleContainingOrderByBoardIdDesc(boardWantedRole);
            default: // 기본값은 ALL 항목으로 글 출력
                return boardJparepo.findAllByOrderByBoardIdDesc();
        }

    }


    //등록 API
    @PostMapping("/api/board")
    public Board postBoard(@RequestBody BoardDTO boardDTO){
        Board board = new Board(boardDTO);
        boardJparepo.save(board);
        return board;
    }


    @PutMapping("/api/board/{boardTitle}")
    public List<Board> putBoardTitleSub(@PathVariable String boardTitle){
        List<Board> boardList = boardJparepo.findAllByBoardTitleContainingOrderByBoardIdDesc(boardTitle);
        if(boardList.size()!=0){
            return boardList;
        }
        else{ return null;}

    }

    //삭제 API
    @DeleteMapping("/api/board/{boardId}")
    public void deleteBoard(@PathVariable Long boardId){
        boardJparepo.deleteById(boardId);
    }

    //수정 API
    @PatchMapping("/api/board/{boardId}")
    public void updateBoard(@PathVariable Long boardId, @RequestBody BoardDTO boardDTO){
        Optional<Board> optionalBoard = boardJparepo.findById(boardId);

        if(optionalBoard.isPresent()){
            boardService.updateBoard(boardId,boardDTO);
        }

    }
}
