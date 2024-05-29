package com.TimeVenture.repository.member;

import com.TimeVenture.model.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
