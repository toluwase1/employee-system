package com.ems.employeemanagementsystem.Endpoints;

import com.ems.employeemanagementsystem.Exceptions.ResourceNotFoundException;
import com.ems.employeemanagementsystem.Models.Vacation;
import com.ems.employeemanagementsystem.Repositories.UserRepository;
import com.ems.employeemanagementsystem.RequestEntities.VacationRequest;
import com.ems.employeemanagementsystem.ResponseBody.ResponseApi;
import com.ems.employeemanagementsystem.Services.ServiceImplementation.VacationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vacations")
public class VacationEndpoint {
    @Autowired
    private VacationServiceImpl vacationService;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/all")
    public List<Vacation> getAllVacations () {
        var vacationList = vacationService.listAllVacation();
        return vacationList;
    }

    @RequestMapping("/{id}")
    public Vacation getVacationsById (@PathVariable(value = "id") Long vacationsId ) {
        return this.vacationService.getVacationById(vacationsId).
                orElseThrow(()-> new ResourceNotFoundException("Task with ID" + vacationsId +" not found"));
    }

    @RequestMapping("/users/{id}")
    public List getVacationsByUser(@PathVariable Long id) {
        var users = userRepository.findById(id).get();
        return this.vacationService.getVacationByUser(users);
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<ResponseApi> createVacation (@RequestBody VacationRequest vacationRequest, @PathVariable Long id) throws Exception {
        ResponseApi responseApi=  vacationService.createVacationRequest(vacationRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(responseApi);
    }
}
