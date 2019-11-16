package ru.progwards.sever;

class Test{
    private String name;
    private int age;
    private String country;

    public Test(){
        country = "RU";
    }
    public Test(String name, int age){
        this();
        this.country = country;
        this.name = name;
        this.age = age;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public String getCountry(){
        return country;
    }
}