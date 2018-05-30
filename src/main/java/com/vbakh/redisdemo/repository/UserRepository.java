package com.vbakh.redisdemo.repository;

import com.vbakh.redisdemo.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by volodymyr.bakhmatiuk on 5/30/18.
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {}

