package com.tuaev.coffee_machine.repositories;

import com.tuaev.coffee_machine.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface ResourceRepo extends JpaRepository<Resource, Long> {

    @Query(value = "select r.id, r.resource, r.amount from  resources r \n" +
            "inner join coffee_machine_resources cmr \n" +
            "on r.id = cmr.resource_id\n" +
            "inner join coffee_machine cm \n" +
            "on cmr.machine_id = cm.id where cm.id = :cm.id", nativeQuery = true)
    List<Resource> findByCoffeeMachineId(@Param("cm.id") Long coffeeMachineId);

}
