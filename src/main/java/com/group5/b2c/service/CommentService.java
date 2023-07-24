package com.group5.b2c.service;

import java.util.ArrayList;
import java.util.List;


import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.group5.b2c.dto.CommentResponse;
import com.group5.b2c.model.Board;
import com.group5.b2c.model.Comment;
import com.group5.b2c.model.Member;
import com.group5.b2c.repository.BoardRepository;
import com.group5.b2c.repository.CommentRepository;
import com.group5.b2c.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	 private final CommentRepository commentRepository;
	 private final BoardRepository boardRepository;
	 private final MemberRepository memberRepository;
	
	 @Transactional
	 public void addComment1(Long boardId, Long memberId, Comment comment) {
	     Board board = boardRepository.findById(boardId).orElse(null);
	     Member member = memberRepository.findById(memberId).orElse(null);

	     if (board == null || member == null) {
	         throw new IllegalArgumentException("해당 게시물이나 회원을 찾을 수 없습니다.");
	     }

	     comment.setBoard(board);
	     comment.setMember(member);
	     commentRepository.save(comment);
	 }


	    @Transactional
	    public void deleteComment(long commentId) {
	        commentRepository.deleteById(commentId);
	    }

	    public List<CommentResponse> getCommentsByBoardId(long boardId, long currentUserId) {
	        Board board = boardRepository.findById(boardId).orElse(null);
	        if (board == null) {
	            throw new IllegalArgumentException("해당 게시물을 찾을 수 없습니다.");
	        }

	        List<Comment> comments = board.getComments();
	        List<CommentResponse> commentResponses = new ArrayList<>();

	        for (Comment comment : comments) {
	            CommentResponse commentResponse = new CommentResponse();
	            commentResponse.setCnum(comment.getCnum());
	            commentResponse.setContent(comment.getContent());
	            commentResponse.setRegdate(comment.getRegdate());
	            commentResponse.setMember(comment.getMember());

	            // 현재 로그인한 사용자와 댓글 작성자 비교
	            if (comment.getMember() != null && comment.getMember().getMemberid() == currentUserId) {
	                commentResponse.setEditable(true);
	            } else {
	                commentResponse.setEditable(false);
	            }

	            commentResponses.add(commentResponse);
	        }

	        return commentResponses;
	    }

	    
	    @Transactional
	    public void addComment(Long boardId, Long memberId, Comment comment) {
	        Board board = boardRepository.findById(boardId).orElse(null);
	        Member member = memberRepository.findById(memberId).orElse(null);

	        if (board != null && member != null) {
	            comment.setBoard(board);
	            comment.setMember(member);
	            commentRepository.save(comment);
	        } else {
	            throw new IllegalArgumentException("해당 게시물이나 회원을 찾을 수 없습니다.");
	        }
	    }


		public void addComment(long num, Comment comment) {
			// TODO Auto-generated method stub
			
		}
}
