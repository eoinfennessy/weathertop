package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller {
    public static void signup() {
        render("signup.html");
    }

    public static void login() {
        render("login.html");
    }

    public static void register(String firstName, String lastName, String email, String password) {
        Logger.info("Registering new member: " + email);
        Member member = new Member(firstName, lastName, email, password);
        member.save();
        redirect("/");
    }

    public static void authenticate(String email, String password) {
        Logger.info("Attempting to authenticate user: " + email);

        Member member = Member.findByEmail(email);
        if (member != null && member.isCorrectPassword(password)) {
            Logger.info("Authentication successful");
            session.put("logged_in_member_id", member.id);
            redirect("/dashboard");
        } else {
            Logger.info("Authentication failed");
            redirect("/login");
        }
    }

    public static void logout() {
        session.clear();
        redirect("/");
    }

    public static Member getLoggedInMember() {
        Member member = null;
        if (session.contains("logged_in_member_id")) {
            String memberId = session.get("logged_in_member_id");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }
}
