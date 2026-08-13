package library.manager;

import library.model.Member;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class MemberManager {

    private static final String DATA_FILE = "members.dat";

    private final List<Member> members;

    public MemberManager() {
        members = new ArrayList<>();
        loadMembers();
    }

    // ADD MEMBER
    public boolean addMember(Member member) {

        if (member == null) {
            return false;
        }

        if (member.getMemberId() <= 0) {
            return false;
        }

        if (member.getName() == null
                || member.getName().trim().isEmpty()) {
            return false;
        }

        if (!isValidEmail(member.getEmail())) {
            return false;
        }

        if (!isValidPhone(member.getPhone())) {
            return false;
        }

        if (!isValidPassword(member.getPassword())) {
            return false;
        }

        if (searchMember(member.getMemberId()) != null) {
            return false;
        }

        // Email is used for login, therefore it must be unique.
        if (searchMemberByEmail(member.getEmail()) != null) {
            return false;
        }

        members.add(member);

        saveMembers();

        return true;
    }


    // SEARCH BY ID
    public Member searchMember(int memberId) {

        for (Member member : members) {

            if (member.getMemberId() == memberId) {
                return member;
            }
        }

        return null;
    }


    // SEARCH BY EMAIL
    public Member searchMemberByEmail(String email) {

        if (email == null) {
            return null;
        }

        for (Member member : members) {

            if (member.getEmail() != null
                    && member.getEmail()
                    .equalsIgnoreCase(email.trim())) {

                return member;
            }
        }

        return null;
    }

    // MEMBER LOGIN
    public Member authenticateMember(
            String login,
            String password
    ) {

        if (login == null
                || login.trim().isEmpty()
                || password == null
                || password.isEmpty()) {

            return null;
        }

        Member member = null;

        String loginValue = login.trim();

        // Allow Member ID OR Email
        try {

            int memberId =
                    Integer.parseInt(loginValue);

            member = searchMember(memberId);

        } catch (NumberFormatException ignored) {

            member =
                    searchMemberByEmail(loginValue);
        }

        if (member == null) {
            return null;
        }

        if (member.getPassword() == null) {
            return null;
        }

        if (!member.getPassword().equals(password)) {
            return null;
        }

        return member;
    }

    // UPDATE MEMBER
    public boolean updateMember(
            int memberId,
            String name,
            String email,
            String phone,
            String password
    ) {

        if (memberId <= 0) {
            return false;
        }

        if (name == null
                || name.trim().isEmpty()) {
            return false;
        }

        if (!isValidEmail(email)) {
            return false;
        }

        if (!isValidPhone(phone)) {
            return false;
        }

        Member member =
                searchMember(memberId);

        if (member == null) {
            return false;
        }


        Member emailOwner =
                searchMemberByEmail(email);

        if (emailOwner != null
                && emailOwner.getMemberId() != memberId) {

            return false;
        }

        if (password == null
                || password.trim().isEmpty()) {

            if (!isValidPassword(member.getPassword())) {
                return false;
            }

        } else {

            if (!isValidPassword(password)) {
                return false;
            }

            member.setPassword(password);
        }

        member.setName(name.trim());
        member.setEmail(email.trim());
        member.setPhone(phone.trim());

        saveMembers();

        return true;
    }

    public boolean updateMember(
            int memberId,
            String name,
            String email,
            String phone
    ) {

        return updateMember(
                memberId,
                name,
                email,
                phone,
                null
        );
    }


    // DELETE MEMBER
    public boolean deleteMember(int memberId) {

        Member member =
                searchMember(memberId);

        if (member == null) {
            return false;
        }

        boolean removed =
                members.remove(member);

        if (removed) {
            saveMembers();
        }

        return removed;
    }


    // GET MEMBERS
    public List<Member> getAllMembers() {
        return new ArrayList<>(members);
    }

    public boolean isEmpty() {
        return members.isEmpty();
    }

    public int getMemberCount() {
        return members.size();
    }


    // VALIDATION
    private boolean isValidEmail(String email) {

        return email != null
                && email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        );
    }

    private boolean isValidPhone(String phone) {

        return phone != null
                && phone.matches("\\d{10}");
    }

    private boolean isValidPassword(String password) {

        return password != null
                && password.length() >= 6;
    }


    // SAVE
    private void saveMembers() {

        try (
                ObjectOutputStream output =
                        new ObjectOutputStream(
                                new FileOutputStream(DATA_FILE)
                        )
        ) {

            output.writeObject(members);

        } catch (IOException e) {

            System.err.println(
                    "Could not save members: "
                            + e.getMessage()
            );
        }
    }


    // LOAD
    @SuppressWarnings("unchecked")
    private void loadMembers() {

        try (
                ObjectInputStream input =
                        new ObjectInputStream(
                                new FileInputStream(DATA_FILE)
                        )
        ) {

            List<Member> savedMembers =
                    (List<Member>) input.readObject();

            members.addAll(savedMembers);

        } catch (java.io.FileNotFoundException e) {


        } catch (
                IOException
                | ClassNotFoundException e
        ) {

            System.err.println(
                    "Could not load members: "
                            + e.getMessage()
            );
        }
    }
}