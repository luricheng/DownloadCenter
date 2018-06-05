package com.example.demo.model;

public class Account {
    public Account(String id,
                   String password,
                   int type, String nick,
                   int age,
                   String mail,
                   String question,
                   String answer){
        setId(id);
        setPassword(password);
        setType(type);
        setNick(nick);
        setAge(age);
        setMail(mail);
        setAnswer(answer);
        setQuestion(question);
    }

    public Account(String id, String password, int type){
        setId(id);
        setPassword(password);
        setType(type);
        setNick("");
        setAge(0);
        setMail("");
        setAnswer("");
        setQuestion("");
    }
    private String id;

    private String password;

    private Integer type;

    private String nick;

    private Integer age;

    private String mail;

    private String question;

    private String answer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick == null ? null : nick.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }
}