package com.unwe.bugtracker.servicesImpl;

import com.unwe.bugtracker.constants.Constants;
import com.unwe.bugtracker.entities.Role;
import com.unwe.bugtracker.entities.User;
import com.unwe.bugtracker.entities.VerificationToken;
import com.unwe.bugtracker.models.bindingModels.users.RegistrationModel;
import com.unwe.bugtracker.models.viewModels.user.AllUsersViewModel;
import com.unwe.bugtracker.repositories.UserRepository;
import com.unwe.bugtracker.repositories.VerificationTokenRepository;
import com.unwe.bugtracker.services.CompanyService;
import com.unwe.bugtracker.services.RoleService;
import com.unwe.bugtracker.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CompanyService companyService;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  this.userRepository.findOneByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException(Constants.INVALID_CREDENTIALS);
        }

//        if (!this.getEnabled(user.getId())){
//            user.setEnabled(false);
//        }
        return user;
    }

    public Boolean getEnabled(long userId){
        return this.userRepository.getEnabled(userId);
    }

    @Override
    public List<AllUsersViewModel> findAll() {
        List<User> userList = this.userRepository.findAll();
        List<AllUsersViewModel> allUsersViewModels = new ArrayList<>();

        for (User user : userList) {
            allUsersViewModels.add(this.modelMapper.map(user, AllUsersViewModel.class));
        }

        return allUsersViewModels;
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findOneByUsername(username);
    }

    @Override
    public List<User> getAllByAuthorities(List<Role> roles) {
        return this.userRepository.findAllByAuthorities(roles);
    }

    @Override
    public User register(RegistrationModel registrationModel) {
        User user = this.modelMapper.map(registrationModel, User.class);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(registrationModel.getPassword());
        user.setPassword(encryptedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        List<Role> authorities = new ArrayList<>();
        authorities.add(this.roleService.findByAuthority(registrationModel.getRole()));
        user.setAuthorities(authorities);

        user.setCompany(this.companyService.getById(registrationModel.getCompany()));
        user.setEnabled(true);
        User userResult = this.userRepository.save(user);
        System.out.println(userResult.getUsername());
        return userResult;
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        this.tokenRepository.save(myToken);
    }

    @Override
    public void saveRegisteredUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return this.tokenRepository.findByToken(VerificationToken);
    }


}
