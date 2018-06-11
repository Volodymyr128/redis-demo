package com.vbakh.redisdemo.repository;

import com.vbakh.redisdemo.domain.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by volodymyr.bakhmatiuk on 6/11/18.
 */
@Repository
public interface LocationRepository extends CrudRepository<Location, String> {}
