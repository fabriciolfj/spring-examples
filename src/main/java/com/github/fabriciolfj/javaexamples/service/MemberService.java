package com.github.fabriciolfj.javaexamples.service;

import com.github.fabriciolfj.javaexamples.entity.Member;
import com.github.fabriciolfj.javaexamples.entity.Members;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private static final List<Member> base = getList();

    public Members findAll() {
        var value = new Members();
        value.setMembers(getList());

        return value;
    }

    public Optional<Member> findById(int memberId) {
        return getList()
                .stream()
                .filter(p -> p.getId().equals(memberId))
                .findFirst();
    }

    public Member save(final Member member) {
        base.add(member);
        return member;
    }

    private static List<Member> getList() {
        var list = new ArrayList<Member>();
        for (int i = 0; i < 100; i++) {
            list.add(Member.builder()
                    .id(1001 + i)
                    .email("fabricio" + i + "@outlook.com")
                    .name("fabricio" + i)
                    .phone("169884343" + i)
                    .build());
        }
        return list;
    }
}
