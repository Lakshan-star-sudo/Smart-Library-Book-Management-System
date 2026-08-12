package library.test;

import library.manager.MemberManager;
import library.model.Member;

public class MemberIntegrationTest {

    public static void main(String[] args) {

        System.out.println("===== MEMBER INTEGRATION TEST =====");

        // Create Member Manager
        MemberManager memberManager = new MemberManager();

        // Add test members
        memberManager.addMember(new Member(
                101,
                "Nimal Perera",
                "nimal@gmail.com",
                "0712345678"
        ));

        memberManager.addMember(new Member(
                102,
                "Kamal Silva",
                "kamal@gmail.com",
                "0723456789"
        ));

        // =========================
        // TEST MEMBER SEARCH
        // =========================

        System.out.println("\n--- Search Member ---");

        Member member =
                memberManager.searchMember(101);

        if (member != null) {

            System.out.println("Member found:");
            System.out.println(member);

        } else {

            System.out.println("Member not found.");
        }

        // =========================
        // TEST MEMBER COUNT
        // =========================

        System.out.println("\n--- Member Count ---");

        System.out.println(
                "Number of Members: "
                        + memberManager.getMemberCount()
        );

        // =========================
        // TEST MEMBER EXISTS
        // =========================

        System.out.println("\n--- Member Exists Test ---");

        if (memberManager.searchMember(102) != null) {

            System.out.println(
                    "Member 102 exists."
            );

        } else {

            System.out.println(
                    "Member 102 does not exist."
            );
        }

        // =========================
        // TEST NON-EXISTING MEMBER
        // =========================

        System.out.println(
                "\n--- Non-Existing Member Test ---"
        );

        if (memberManager.searchMember(999) == null) {

            System.out.println(
                    "Correct: Member 999 not found."
            );

        } else {

            System.out.println(
                    "Error: Member 999 was found."
            );
        }

        System.out.println(
                "\n===== INTEGRATION TEST COMPLETE ====="
        );
    }
}
