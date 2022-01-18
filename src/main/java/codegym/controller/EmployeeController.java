package codegym.controller;

import codegym.model.Branch;
import codegym.model.Employee;
import codegym.service.IBranchService;
import codegym.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    IEmployeeService employeeService;

    @Autowired
    IBranchService branchService;

    @GetMapping("/employees")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("/list");
        List<Employee> employees = employeeService.findAll();
        modelAndView.addObject("employees", employees);
        List<Branch> branches = branchService.findAll();
        modelAndView.addObject("branches", branches);
        return modelAndView;
    }

    @GetMapping("/{id}/detail")
    public ModelAndView detail(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/detail");
        modelAndView.addObject("employee", employeeService.findById(id));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        List<Branch> branches = branchService.findAll();
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("employee", new Employee());
        modelAndView.addObject("branches", branches);
        return modelAndView;
    }

    @PostMapping("/create")
    public String createEmployee(@ModelAttribute Employee employee, @RequestParam(value = "branchId") Long branchId) {
        Branch branch = new Branch();
        branch.setId(branchId);
        employee.setBranch(branch);
        employeeService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editForm (@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("employee", employeeService.findById(id));
        modelAndView.addObject("branches", branchService.findAll());
        return modelAndView;
    }

    @PostMapping("/update")
    public String edit(@ModelAttribute Employee employee, @RequestParam(value = "branchId") Long branchId) {
        Branch branch = new Branch();
        branch.setId(branchId);
        employee.setBranch(branch);
        employeeService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/delete");
        modelAndView.addObject("employee", employeeService.findById(id));
        return modelAndView;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(value = "id") Long id){
        employeeService.delete(id);
        return "redirect:/employees";
    }


}
