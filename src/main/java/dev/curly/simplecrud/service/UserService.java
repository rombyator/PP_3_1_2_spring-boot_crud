package dev.curly.simplecrud.service;

import dev.curly.simplecrud.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAll();

    public void add(User user);

    public User getById(long id);

    public void update(User user);

    public void delete(long id);
}