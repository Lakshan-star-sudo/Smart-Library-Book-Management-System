package library.test;

import library.manager.MemberManager;
import library.model.Member;

public class MemberManagerTest {

    public static void main(String[] args) {

        MemberManager memberManager = new MemberManager();

        System.out.println("===== MEMBER MANAGEMENT TEST =====");


        // =========================
        // 1. ADD MEMBERS
        // =========================

        Member member1 = new Member(
                1,
                "John Silva",
                "john@gmail.com",
                "0712345678"
        );

        Member member2 = new Member(
                2,
                "Sarah Perera",
                "sarah@gmail.com",
                "0723456789"
        );

        Member member3 = new Member(
                3,
                "David Fernando",
                "david@gmail.com",
                "0774567890"
        );

        System.out.println("\n--- Adding Members ---");

        System.out.println("Member 1 added: "
                + memberManager.addMember(member1));

        System.out.println("Member 2 added: "
                + memberManager.addMember(member2));

        System.out.println("Member 3 added: "
                + memberManager.addMember(member3));


        // =========================
        // 2. DISPLAY ALL MEMBERS
        // =========================

        System.out.println("\n--- All Members ---");

        for (Member member : memberManager.getAllMembers()) {
            System.out.println(member);
        }


        // =========================
        // 3. SEARCH MEMBER
        // =========================

        System.out.println("\n--- Search Member ---");

        Member foundMember = memberManager.searchMember(2);

        if (foundMember != null) {
            System.out.println("Member found:");
            System.out.println(foundMember);
        } else {
            System.out.println("Member not found.");
        }


        // =========================
        // 4. UPDATE MEMBER
        // =========================

        System.out.println("\n--- Update Member ---");

        boolean updated = memberManager.updateMember(
                2,
                "Sarah Perera Updated",
                "sarah.new@gmail.com",
                "0711111111"
        );

        System.out.println("Update successful: " + updated);

        System.out.println("Updated member:");

        System.out.println(
                memberManager.searchMember(2)
        );


        // =========================
        // 5. DELETE MEMBER
        // =========================

        System.out.println("\n--- Delete Member ---");

        boolean deleted = memberManager.deleteMember(3);

        System.out.println("Delete successful: " + deleted);


        // =========================
        // 6. DISPLAY AFTER DELETE
        // =========================

        System.out.println("\n--- Members After Delete ---");

        for (Member member : memberManager.getAllMembers()) {
            System.out.println(member);
        }


        // =========================
        // 7. MEMBER COUNT
        // =========================

        System.out.println("\n--- Member Information ---");

        System.out.println(
                "Number of Members: "
                        + memberManager.getMemberCount()
        );

        System.out.println(
                "Is Empty: "
                        + memberManager.isEmpty()
        );


        // =========================
        // 8. DUPLICATE ID TEST
        // =========================

        System.out.println("\n--- Duplicate ID Test ---");

        Member duplicateMember = new Member(
                1,
                "Another Person",
                "another@gmail.com",
                "0700000000"
        );

        System.out.println(
                "Duplicate member added: "
                        + memberManager.addMember(duplicateMember)
        );


        // =========================
        // 9. INVALID MEMBER TEST
        // =========================

        System.out.println("\n--- Invalid Member Test ---");

        Member invalidMember = new Member(
                -5,
                "",
                "",
                ""
        );

        System.out.println(
                "Invalid member added: "
                        + memberManager.addMember(invalidMember)
        );


        // =========================
        // 10. INVALID EMAIL TEST
        // =========================

        System.out.println("\n--- Invalid Email Test ---");

        Member invalidEmailMember = new Member(
                10,
                "Test User",
                "wrong-email",
                "0712345678"
        );

        System.out.println(
                "Invalid email member added: "
                        + memberManager.addMember(invalidEmailMember)
        );


        // =========================
        // 11. INVALID PHONE TEST
        // =========================

        System.out.println("\n--- Invalid Phone Test ---");

        Member invalidPhoneMember = new Member(
                11,
                "Test User",
                "test@gmail.com",
                "12345"
        );

        System.out.println(
                "Invalid phone member added: "
                        + memberManager.addMember(invalidPhoneMember)
        );


        // =========================
        // 12. VALID EMAIL + PHONE TEST
        // =========================

        System.out.println("\n--- Valid Email and Phone Test ---");

        Member validMember = new Member(
                12,
                "Valid User",
                "valid@gmail.com",
                "0781234567"
        );

        System.out.println(
                "Valid member added: "
                        + memberManager.addMember(validMember)
        );


        // =========================
        // TEST COMPLETE
        // =========================

        System.out.println("\n===== TEST COMPLETE =====");
    }
}