package exp.server.app_server.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import exp.server.app_server.domain.User;
import exp.server.app_server.repository.UserRepository;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user/add")
    public User add(User user) {
        return userRepository.insert(user);
    }

    @RequestMapping(value = "/user/findAll")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/user/findById")
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @RequestMapping(value = "/user/update")
    public User update(User user) {
        if (userRepository.existsById(user.getId()))
            return userRepository.save(user);
        else
            return null;
    }

    @RequestMapping(value = "/user/delete")
    public boolean delete(String id) {
        userRepository.deleteById(id);
        if (userRepository.existsById(id))
            return false;
        else
            return true;
    }
}