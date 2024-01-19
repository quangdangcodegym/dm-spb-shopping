package com.cg.shopping.service;

import com.cg.shopping.exception.ResourceExistsException;
import com.cg.shopping.model.Category;
import com.cg.shopping.model.Product;
import com.cg.shopping.model.Role;
import com.cg.shopping.model.User;
import com.cg.shopping.model.dto.req.ProductReqDto;
import com.cg.shopping.model.dto.req.RegisterReqDto;
import com.cg.shopping.model.dto.req.UserReqDto;
import com.cg.shopping.model.dto.req.UserUpdateReqDto;
import com.cg.shopping.model.dto.res.ProductResDto;
import com.cg.shopping.model.dto.res.RegisterResDto;
import com.cg.shopping.model.dto.res.UserResDto;
import com.cg.shopping.repository.CategoryRepository;
import com.cg.shopping.repository.ProductRepository;
import com.cg.shopping.repository.RoleRepository;
import com.cg.shopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


//    public List<ProductResDto> getAllUsers(){
//        List<User> users = userRepository.findAll();
//        return products.stream().map(Product::toProductResDto).collect(Collectors.toList());
//    }
//    public Product getProductById(@PathVariable Long id) {
//        return productRepository.findById(id).orElse(null);
//    }

    public RegisterResDto save(RegisterReqDto productReqDto) {
        Role role = roleRepository.findById(2L).get();

        User userExists = userRepository.findUserByEmail(productReqDto.getEmail());
        if (userExists == null) {
            User user = new User();
            user.setName(productReqDto.getName());
            user.setEmail(productReqDto.getEmail());
            user.setPassword(productReqDto.getPassword());
            user.addRole(role);

            userRepository.save(user);
            return user.toRegisterResDto();
        }else{
            throw new ResourceExistsException("Email đã tồn tại");
        }
    }


    public UserResDto update(UserUpdateReqDto userUpdateReqDto) {
        User user = userRepository.findById(userUpdateReqDto.getId()).get();
        List<Role> roles = userUpdateReqDto.getRoles()
                .stream()
                .map(i -> roleRepository.findById(i).get())
                .collect(Collectors.toList());

        user.setRoles(roles);
        user.setEmail(userUpdateReqDto.getEmail());
        user.setPassword(userUpdateReqDto.getPassword());
        user.setName(userUpdateReqDto.getName());

        userRepository.save(user);

        return user.toUserResDto();
    }

    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceExistsException("Id user not found"));
        userRepository.delete(user);


    }

    public UserResDto login(UserReqDto userReqDto) {
        User user = userRepository.findUserByEmailAndPassword(userReqDto.getEmail(), userReqDto.getPassword());
        if (user == null) {
            throw new ResourceExistsException("Username and password not correct");
        }
        return user.toUserResDto();
    }
}
