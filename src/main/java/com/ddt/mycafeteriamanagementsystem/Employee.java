package com.ddt.mycafeteriamanagementsystem;

import java.sql.Date;

public class Employee {
    private int id;
    private String username;
    private String password;
    private String question;
    private String answer;
    private Date date;

    public Employee(){}

    public Employee(int id, String username, String password, String question, String answer, Date date) {
        this.id = id;
        this.username = username;
        this.password = MD5.convert_to_MD5(password);
        this.question = question;
        this.answer = answer;
        this.date = date;
    }

    public Employee(int id, String username, String password, String question, String answer) {
        this.id = id;
        this.username = username;
        this.password = MD5.convert_to_MD5(password);
        this.question = question;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = MD5.convert_to_MD5(password);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", date=" + date +
                '}';
    }
}
