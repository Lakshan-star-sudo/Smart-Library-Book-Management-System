package library.manager;

import library.model.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberManager {

    private final List<Member> members;

    public MemberManager() {
        members = new ArrayList<>();
    }

    // Add Member
    public boolean addMember(Member member) {

        if (member == null) {
            return false;
        }

        if (member.getMemberId() <= 0) {
            return false;
        }

        if (member.getName() == null ||
                member.getName().trim().isEmpty()) {
            return false;
        }

        if (!isValidEmail(member.getEmail())) {
            return false;
        }

        if (!isValidPhone(member.getPhone())) {
            return false;
        }

        // Prevent duplicate Member IDs
        if (searchMember(member.getMemberId()) != null) {
            return false;
        }

        members.add(member);
        return true;
    }

    // Search Member
    public Member searchMember(int memberId) {

        for (Member member : members) {

            if (member.getMemberId() == memberId) {
                return member;
            }
        }

        return null;
    }

    // Update Member
    public boolean updateMember(int memberId,
                                String name,
                                String email,
                                String phone) {

        if (memberId <= 0) {
            return false;
        }

        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        // Validate email format
        if (!isValidEmail(email)) {
            return false;
        }

        // Validate phone format
        if (!isValidPhone(phone)) {
            return false;
        }

        Member member = searchMember(memberId);

        if (member == null) {
            return false;
        }

        member.setName(name);
        member.setEmail(email);
        member.setPhone(phone);

        return true;
    }

    // Delete Member
    public boolean deleteMember(int memberId) {

        Member member = searchMember(memberId);

        if (member == null) {
            return false;
        }

        return members.remove(member);
    }

    // Get all Members
    public List<Member> getAllMembers() {
        return new ArrayList<>(members);
    }

    // Check whether there are no Members
    public boolean isEmpty() {
        return members.isEmpty();
    }

    // Get number of Members
    public int getMemberCount() {
        return members.size();
    }

    // Validate Email
    private boolean isValidEmail(String email) {
        return email != null
                && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    // Validate Phone
    private boolean isValidPhone(String phone) {
        return phone != null
                && phone.matches("\\d{10}");
    }
}