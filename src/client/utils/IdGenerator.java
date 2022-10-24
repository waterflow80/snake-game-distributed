package client.utils;

public class IdGenerator {
    public static Integer id = -1;

    public static Integer getId(){
        return ++id;
    }
}
