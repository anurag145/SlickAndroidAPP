package com.github.anurag145.slick;

/**
 * Created by anura on 4/20/2018.
 */

public class UserInfo {
    public static UserInfo singleton = new UserInfo();

    public static UserInfo getSingleton() {
        return singleton;
    }

    public static String user;

}