package ru.zankov.model;

public class PasswordReq {

    private String original;
    private String password;
    private String confirm;

    public PasswordReq(String original, String password, String confirm) {
        this.original = original;
        this.password = password;
        this.confirm = confirm;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}
