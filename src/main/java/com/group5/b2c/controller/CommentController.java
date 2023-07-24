package com.group5.b2c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group5.b2c.dto.CommentResponse;
import com.group5.b2c.model.Comment;
import com.group5.b2c.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
	    private final CommentService commentService;

	    @GetMapping("/list/{num}")
	    public List<CommentResponse> getCommentsByBoardId(@PathVariable long num) {
	        // CommentService를 통해 해당 게시물의 댓글 목록을 가져오는 메서드를 호출
	        return commentService.getCommentsByBoardId(num, num);
	    }

	    @PostMapping("/add/{num}")
	    public String addComment(@PathVariable long num, @RequestBody Comment comment) {
	        // CommentService를 통해 댓글을 추가하는 메서드를 호출
	        commentService.addComment(num, comment);
	        return "댓글이 추가되었습니다.";
	    }

	    @DeleteMapping("/delete/{cnum}")
	    public String deleteComment(@PathVariable long cnum) {
	        // CommentService를 통해 댓글을 삭제하는 메서드를 호출
	        commentService.deleteComment(cnum);
	        return "댓글이 삭제되었습니다.";
	    }
}