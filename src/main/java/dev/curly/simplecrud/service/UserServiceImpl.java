package dev.curly.simplecrud.service;

import dev.curly.simplecrud.model.User;
import dev.curly.simplecrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    @Transactional
    public void add(User user) {
        userRepo.save(user);
    }

    @Override
    public User getById(long id) {
        return userRepo.getReferenceById(id);
    }

    @Override
    @Transactional
    public void update(User user) {
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        userRepo.deleteById(id);
    }
}