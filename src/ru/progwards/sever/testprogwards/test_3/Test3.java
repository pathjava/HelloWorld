// Oleg Kiselev
// 06.11.2020, 18:02

// Oleg Kiselev
// 06.05.2020, 14:50

package ru.progwards.sever.testprogwards.test_3;

//public class Test3 {
//    public static void main(String[] args) {
////        String str1 = "12345";
//////        String str2 = "1234";
//////        str2 += "5";
//////
//////        System.out.println(str1 == str2);
//////        System.out.println(str1.equals(str2));
//        String name1 = "Tom";
//        String name2 = "Tom";
//
//        System.out.println(name1.compareTo(name2));
//    }
//}

public class Test3 {

    private long userID;
    private String name;

    public Test3(long userID, String name) {
        this.userID = userID;
        this.name = name;
    }

    public static void main(String[] args) {
        Test3 user = new Test3(879745, "John");
        SpecificUser specificUser = new SpecificUser("1AAAA", 877777, "Jim");
        Test3 anotherSpecificUser = new SpecificUser("1BBBB", 812345, "Jenny");

        System.out.println(user.getClass()); //Prints "class User"
        System.out.println(specificUser.getClass()); //Prints "class SpecificUser"
        System.out.println(anotherSpecificUser.getClass()); //Prints "class SpecificUser"
    }
}

class SpecificUser extends Test3 {
    private String specificUserID;

    public SpecificUser(String specificUserID, long userID, String name) {
        super(userID, name);
        this.specificUserID = specificUserID;
    }
}

