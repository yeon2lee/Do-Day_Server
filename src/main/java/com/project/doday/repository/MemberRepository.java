package com.project.doday.repository;

import com.project.doday.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Boolean existsMemberByUserId(String userId);

    Member findAllByUserId(String userId);
}
