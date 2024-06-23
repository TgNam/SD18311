package org.example.asm_2.service;

import org.example.asm_2.model.UserEntity;

import java.util.List;

public interface UserService extends Service{
    UserEntity findUser(String email, String password);
}
