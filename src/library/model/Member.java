package library.model;

import java.io.Serializable;

public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    private int memberId;
    private String name;
    private String email;
    private String phone;
    private String password;

    public Member(
            int memberId,
            String name,
            String email,
            String phone,
            String password
    ) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public Member(
            int memberId,
            String name,
            String email,
            String phone
    ) {
        this(memberId, name, email, phone, null);
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Member ID: " + memberId
                + ", Name: " + name
                + ", Email: " + email
                + ", Phone: " + phone;
    }
}