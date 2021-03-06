package pl.edu.wszib.petShop.Services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.petShop.Database.Database;
import pl.edu.wszib.petShop.Exceptions.LoginAlreadyUsedException;
import pl.edu.wszib.petShop.Model.User;
import pl.edu.wszib.petShop.Model.View.RegisterUser;
import pl.edu.wszib.petShop.Services.IAuthenticationService;
import pl.edu.wszib.petShop.Session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    Database database;

    @Override
    public void authenticate(String login, String password) {
        Optional<User> user = this.database.getUserByLogin(login);

        if(user.isEmpty() ||
                !user.get().getPass().equals(DigestUtils.md5Hex(password))) {
            return;
        }
        this.sessionObject.setUser(user.get());
    }

    @Override
    public void register(RegisterUser registerUser) {
        Optional<User> userBox = this.database.getUserByLogin(registerUser.getLogin());

        if(userBox.isPresent()) {
            throw new LoginAlreadyUsedException();
        }

        registerUser.setPass(DigestUtils.md5Hex(registerUser.getPass()));
        this.database.addUser(registerUser);
    }
}