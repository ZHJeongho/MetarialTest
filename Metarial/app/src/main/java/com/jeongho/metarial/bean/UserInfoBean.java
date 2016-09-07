package com.jeongho.metarial.bean;

/**
 * Created by Jeongho on 2016/8/16.
 */
public class UserInfoBean {
    public int result;
    public UserDetails user;

    public class UserDetails{
        public int userId;
        public String nickname;
        public boolean sex;
        public int age;
        public String birthday;
        public String icon;
        public String userCode;
    }

    @Override
    public String toString() {
        return result + user.userId + user.nickname + user.sex + user.age + user.birthday + user.icon + user.userCode;
    }
}
