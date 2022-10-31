package com.example.crud.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

}
