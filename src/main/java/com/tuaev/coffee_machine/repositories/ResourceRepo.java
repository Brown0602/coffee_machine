package com.tuaev.coffee_machine.repositories;

import com.tuaev.coffee_machine.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public
interface ResourceRepo extends JpaRepository<Resource, Long> {

}
