package com.github.fabriciolfj.javaexamples.entity;

import java.util.ArrayList;
import java.util.List;

public class Members {

    private List<Member> members = new ArrayList<>();

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
