package com.ems.employeemanagementsystem.Repositories;

import com.ems.employeemanagementsystem.Models.Task;
import com.ems.employeemanagementsystem.Models.Users;
import com.ems.employeemanagementsystem.Models.Vacation;
import com.ems.employeemanagementsystem.RequestEntities.TaskRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VacationRepository extends JpaRepository<Vacation, Long> {
    Vacation findVacationByTitle(String title);
    Vacation findVacationByDuration(Long duration);
    Void deleteVacationByUsers(Users users);
    Void deleteVacationById(Long id);
    Vacation createVacation(TaskRequest taskRequest);
    Optional<Vacation> findById(Long id);
}
