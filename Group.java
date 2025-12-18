package com.expensesharing;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Group {
    private String groupId;
    private String name;
    private List<User> members;

    public Group(String name) {
        this.groupId = UUID.randomUUID().toString();
        this.name = name;
        this.members = new ArrayList<>();
    }

    public void addMember(User user) {
        members.add(user);
    }

    public List<User> getMembers() {
        return members;
    }
}
