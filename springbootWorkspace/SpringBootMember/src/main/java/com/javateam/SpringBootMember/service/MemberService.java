package com.javateam.SpringBootMember.service;

import java.util.List;

import com.javateam.SpringBootMember.domain.MemberVO;

public interface MemberService {
	
	/**
	 * 회원 가입(회원정보 생성) 처리
	 * @param member 회원정보(객체)
	 */
	void insertMember(MemberVO member);
	
	/**
	 * 회원 존재 여부 점검(회원 아이디 중복 점검)
	 * @param id 회원 아이디
	 * @return 회원 존재 여부
	 */
	boolean isMember(String id);
	
	/**
	 * 이메일 중복 점검
	 * @param email이메일
	 * @return 이메일 중복 여부
	 */
	boolean isEnableEmail(String email);
	
	/**
	 * 휴대폰 중복 점검
	 * @param phone 휴대전화 번호
	 * @return 휴대폰 중복 여부
	 */
	boolean isEnablePhone(String phone);
	
	/**
	 * 개별 회원정보 레코드(튜플) 조회(read)
	 * 
	 * @param memberId 회원 아이디
	 * @return 회원정보
	 * @throws Exception 예외처리
	 */
	MemberVO getMember(String memberId);
	
	/**
	 * 개별 회원정보 레코드(튜플) 수정(갱신) (update)
	 * 
	 * @param member 회원정보
	 * @throws Exception 예외처리
	 */
	void updateMember(MemberVO member);
	
	/**
	 * 개별 회원정보 레코드(튜플) 삭제 (delete)
	 * 
	 * @param memberId 회원 아이디
	 * @throws Exception 예외처리
	 */
	void deleteMember(String memberId);
	
	/**
	 * 주어진 회원 아이디의 이메일을 타 회원들과 중복하지 않고 사용가능한지 여부 점검
	 * 
	 * usage) 회원 가입/변경시 이메일 중복 점검
	 * 
	 * @param memberId 회원 아이디
	 * @param email 이메일
	 * @return 이메일 사용가능 여부
	 * @throws Exception 예외처리
	 */
	boolean isEnableEmail(String memberId, String email);
	
	/**
	 * 연락처가 타 회원들과 중복하지 않고 사용가능한지 여부 점검
	 * 
	 * usage) 회원 수정  연락처 중복 점검
	 * 
	 * @param memberId 회원 아이디
	 * @param phone 연락처
	 * @return 연락처 사용가능 여부
	 * @throws Exception 예외처리
	 */
	boolean isEnablePhone(String memberId, String phone);
	
	/**
     * 페이징을 활용하여 일정한 수의 회원들만을 화면에 출력
     *
     * @param page 현재 페이지
     * @param limit 한번에 출력할 수 있는 회원정보
     * @return 회원정보들
     * @throws Exception 예외처리
     */
    List<MemberVO> getMembersByPaging(int page, int limit);
 
    /**
     * 전체 회원정보  레코드(튜플) 조회(read)
     *
     * @return 전체 회원정보
     * @throws Exception 예외처리
     */
    List<MemberVO> getAllMembers();
    
	/**
	 * 전체 회원수 조회(획득)
	 * 
	 * @return 전체 회원수
	 */
	int getCountAllMembers();
    
    /**
     * 특정 필드(이름, 아이디, 주소 등등)를 이용한 검색 조회 : 페이징 지원
     *
     * @param fld 검색할 필드
     * @param value 필드 값
     * @param isLike 유사 검색(like) 여부  usage) 유사 검색 : true, 동등 검색 : false  
     * @param page 현재 페이지
     * @param limit 한번에 출력할 수 있는 회원정보
     * @return 검색 결과(회원 정보들)
     * @throws Exception 예외처리
     */
    List<MemberVO> getMembersByFieldAndPaging(String fld,
                                              Object value,
                                              boolean isLike,
                                              int page,
                                              int limit);
    
    /**
     * 특정 필드(이름, 아이디, 주소 등등)를 이용한 검색 레코드수 조회
     *
     * @param fld 검색할 필드
     * @param value 필드 값
     * @param isLike 유사 검색(like) 여부  usage) 유사 검색 : true, 동등 검색 : false  
     * @param limit 한번에 출력할 수 있는 회원정보
     * @return 검색 결과(회원 정보들)
     * @throws Exception 예외처리
     */
    int getCountMembersByFieldAndPaging(String fld,
							            Object value,
							            boolean isLike,
							            int limit);
		
}