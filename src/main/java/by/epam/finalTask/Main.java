package by.epam.finalTask;


import org.mindrot.jbcrypt.BCrypt;

public class Main {

    public static void main(String[] args) {

        String string="cat";
        String string1="dog";
        String newS= BCrypt.hashpw(string, BCrypt.gensalt());
        String newS1=BCrypt.hashpw(string, BCrypt.gensalt());

        boolean check=BCrypt.checkpw(string, newS);
        boolean check1=BCrypt.checkpw(string1, newS);

        System.out.println(string);
        System.out.println(newS.length());
        System.out.println(string1);
        System.out.println(newS);
        System.out.println(newS1);
        System.out.println(check);
        System.out.println(check1);
    }



}
