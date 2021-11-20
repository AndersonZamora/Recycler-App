package com.example.recycler.presenter.validatePresenter;

public class validateFields {

   public static boolean validatePassword(String password){
        return !password.matches(".*\\s.*");
    }
   public static boolean validateLength(String password){
        return password.length() >= 5;
    }
}
