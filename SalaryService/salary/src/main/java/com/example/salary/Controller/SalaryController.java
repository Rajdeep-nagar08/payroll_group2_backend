package com.example.salary.Controller;
import com.example.salary.Dto.AllEmployeeSalary;
import com.example.salary.Dto.ApiResponse;
import com.example.salary.Entity.Salary;
import com.example.salary.Services.SalaryService;
import com.example.salary.config.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/salary")
public class SalaryController {

  @Autowired
    SalaryService salaryService;

    @PostMapping("/{id}")
    public ResponseEntity<Salary> createSalary(@RequestBody Salary salary) {
        Salary createdSalary = salaryService.saveSalary(salary);
        return new ResponseEntity<>(createdSalary, HttpStatus.CREATED);
    }

    @PutMapping("updateById/{employeeId}")
    public ResponseEntity<Salary> updateSalary(@PathVariable Long employeeId, @RequestBody Salary salary) {
        Salary updatedSalary = salaryService.updateSalary(employeeId,salary);
        return new ResponseEntity<>(updatedSalary, HttpStatus.OK);
    }


    @DeleteMapping("deleteBYId/{employeeId}")
    public ResponseEntity<ApiResponse> deleteSalary(@PathVariable Long employeeId) {
        salaryService.deleteSalary(employeeId);
        return new ResponseEntity<>(new ApiResponse("Employee Deleted Successfully",true), HttpStatus.OK);
    }

    @GetMapping("fetchById/{employeeId}")
    public ResponseEntity<Salary> getSalary(@PathVariable Long employeeId) {
        Salary salary = salaryService.getSalary(employeeId);
        return new ResponseEntity<>(salary, HttpStatus.OK);
    }


    @GetMapping("/getAll")
    private ResponseEntity<AllEmployeeSalary> getAllPosts(@RequestParam(value="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pgNo,
                                                          @RequestParam(value="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pgSize,
                                                          @RequestParam(value="sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
                                                          @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir){
        AllEmployeeSalary salary = this.salaryService.getAllSalary(pgNo,pgSize,sortBy,sortDir);
        return new ResponseEntity<AllEmployeeSalary>(salary,HttpStatus.OK);
    }


}
