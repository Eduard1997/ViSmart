package com.viSmart.viSmart.Repository;

import com.viSmart.viSmart.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupsInventory extends JpaRepository<Groups, Long>  {

    Groups findById(int id);
}
