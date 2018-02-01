package com.unwe.bugtracker.servicesImpl;

import com.unwe.bugtracker.constants.Constants;
import com.unwe.bugtracker.entities.User;
import com.unwe.bugtracker.models.bindingModels.RegistrationModel;
import com.unwe.bugtracker.repositories.UserRepository;
import com.unwe.bugtracker.services.RoleService;
import com.unwe.bugtracker.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  this.userRepository.findOneByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(Constants.INVALID_CREDENTIALS);
        }

        return user;
    }

    @Override
    public void register(RegistrationModel registrationModel) {
        User user = this.modelMapper.map(registrationModel, User.class);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(registrationModel.getPassword());
        user.setPassword(encryptedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);
        user.setAuthorities(Arrays.asList(this.roleService.findByAuthority(registrationModel.getRoles().get(0))));
        this.userRepository.save(user);
    }
}
