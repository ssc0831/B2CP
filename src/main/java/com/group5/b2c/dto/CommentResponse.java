package com.group5.b2c.dto;

import java.util.Date;

import com.group5.b2c.model.Member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentResponse {
	    private int cnum;
	    private String content;
	    private Date regdate;
	    private MemberResponse member;
	    private boolean editable;

	    // 생성자, Getter, Setter 등 생략

	    // MemberResponse 클래스는 회원 정보를 담는 DTO로 사용자 이름만 포함하도록 구성합니다.
	    public static class MemberResponse {
	        private String username;

	        // 생성자, Getter, Setter 등 생략

	        // 필요한 경우 Member 엔티티를 받아서 MemberResponse로 변환하는 정적 메서드를 추가할 수 있습니다.
	        public static MemberResponse from(Member member) {
	            MemberResponse memberResponse = new MemberResponse();
	            memberResponse.setUsername(member.getUsername());
	            return memberResponse;
	        }

			private void setUsername(String username2) {
				// TODO Auto-generated method stub
				
			}
	    }

		public void setMember(Member member2) {
			// TODO Auto-generated method stub
			
		}
}
