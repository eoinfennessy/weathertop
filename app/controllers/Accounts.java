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

    public static void settings() {
        if (session.contains("logged_in_member_id")) {
            render("account-settings.html");
        } else {
            login();
        }
    }

    public static void settings(String message, String messageType) {
        if (session.contains("logged_in_member_id")) {
            render("account-settings.html", message, messageType);
        } else {
            login();
        }
    }

    public static void register(String firstName, String lastName, String email, String password) {
        Logger.info("Registering new member: " + email);
        Member member = new Member(firstName, lastName, email, password);
        member.save();
        session.put("logged_in_member_id", member.id);
        redirect("/dashboard");
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
            Logger.info("Getting logged in member");
            String memberId = session.get("logged_in_member_id");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            Logger.info("No logged in member found");
        }
        return member;
    }
    
    public void updateName(String firstName, String lastName) {
        Member member = getLoggedInMember();
        if (member == null) {
            login();
        } else {
            Logger.info("Updating logged in member's name");
            member.firstName = firstName;
            member.lastName = lastName;
            member.save();
            settings("Name updated", "success");
        }
    }

    public void updateEmail(String email) {
        Member member = getLoggedInMember();
        if (member == null) {
            login();
        } else {
            Logger.info("Updating logged in member's email");
            member.email = email;
            member.save();
            settings("Email updated", "success");
        }
    }

    public void updatePassword(String password, String confirmPassword, String oldPassword) {
        Member member = getLoggedInMember();
        if (member == null) {
            login();
        } else if (member.isCorrectPassword(oldPassword)) {
            if (password.equals(confirmPassword)) {
                Logger.info("Updating password");
                member.setPassword(password);
                member.save();
                settings("Password updated", "success");
            } else {
                Logger.info("Password update failed: New password does not match confirmation password");
                settings("New password did not match confirmation password", "error");
            }
        } else {
            Logger.info("Password update failed: Incorrect password entered");
            settings("Incorrect password entered", "error");
        }
    }

    public void deleteAccount(String password) {
        Member member = getLoggedInMember();
        if (member == null) {
            login();
        } else if (member.isCorrectPassword(password)) {
            Logger.info("Deleting account for member: " + member.email);
            member.delete();
            session.clear();
            redirect("/");
        } else {
            Logger.info("Delete account failed: Incorrect password entered");
            settings("Incorrect password entered", "error");
        }
    }
}