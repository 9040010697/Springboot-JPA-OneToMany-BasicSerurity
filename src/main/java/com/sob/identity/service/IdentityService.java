package com.sob.identity.service;

import com.sob.identity.exception.IdentityGenericException;
import com.sob.identity.exception.IdentityStandardError;
import com.sob.identity.model.AuthToken;
import com.sob.identity.repo.models.Identity;
import com.sob.identity.model.LoginRequest;
import com.sob.identity.repo.IdentityRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class IdentityService {

    @Autowired
    private IdentityRepository identityRepo;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthToken login(LoginRequest loginRequest) {
        return getAuthToken(loginRequest.getUserName(), loginRequest.getPassword(), "login");
    }

    @SneakyThrows
    public AuthToken register(Identity identity) {
        identityRepo.save(identity);
        return getAuthToken(identity.getEmail(), identity.getPassword(), "register");
    }

    private AuthToken getAuthToken(String email, String password, String type) {
        try {
            return jwtUtil.generate(identityRepo
                    .findUserByEmailAndPassword(
                            email, password), type);
        } catch (RuntimeException exception) {
            throw new IdentityGenericException(IdentityStandardError.INVALID_LOGIN_CRED);
        }
    }




    public Identity getIdentity(String userName){
        Identity identity = identityRepo.findUserByEmail(userName);

        return  identity;
    }
}
