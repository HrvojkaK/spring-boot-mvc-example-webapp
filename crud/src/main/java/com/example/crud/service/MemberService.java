package com.example.crud.service;

import java.util.List;

import com.example.crud.entity.Member;

public interface MemberService {
	
	public List<Member> findAll();

	public Member findById(int theId);
	
	public void save(Member theMember);
	
	public void deleteById(int theId);
}
