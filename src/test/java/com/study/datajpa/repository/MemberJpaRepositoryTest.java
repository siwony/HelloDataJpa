package com.study.datajpa.repository;

import com.study.datajpa.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepo;

    @Test
    public void testMember(){
        Member member = new Member("memberA");
        Member savedMember = memberJpaRepo.save(member);

        Member findMember = memberJpaRepo.find(savedMember.getId());

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void basicCRUD(){
        Member member1 = new Member("member1");
        Member member2 = new Member("member1");
        memberJpaRepo.save(member1);
        memberJpaRepo.save(member2);

        //단건 조회 검증
        Member findMember1 = memberJpaRepo.findById(member1.getId()).get();
        Member findMember2 = memberJpaRepo.findById(member2.getId()).get();
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        findMember1.setUsername("member!!!!!!!!!");

        List<Member> all = memberJpaRepo.findAll();
        assertThat(all.size()).isEqualTo(2);

        //카운트 검증
        long count = memberJpaRepo.count();
        assertThat(count).isEqualTo(2);

        //삭제 검증
        memberJpaRepo.delete(member1);
        memberJpaRepo.delete(member2);

        long deleteCount = memberJpaRepo.count();
        assertThat(deleteCount).isEqualTo(0);
    }
}