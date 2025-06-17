package com.ooredoo.report_builder.repo;


import com.ooredoo.report_builder.entity.Form;
import com.ooredoo.report_builder.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormRepository extends JpaRepository<Form, Integer> {

    List<Form> findByCreator(User user);
    List<Form> findByAssignedUsersContaining(User user);
}
