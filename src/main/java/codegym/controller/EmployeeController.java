package codegym.controller;

import codegym.model.Branch;
import codegym.model.Employee;
import codegym.service.IBranchService;
import codegym.service.IEmployeeService;
import codegym.validate.EmployeeCodeDuplicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    IEmployeeService employeeService;

    @Autowired
    IBranchService branchService;

    @Autowired
    EmployeeCodeDuplicate employeeCodeDuplicate;

    @GetMapping("/employees")
    public ModelAndView list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "employeeCode") String option) {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("employees", employeeService.findAll(PageRequest.of(page, 3, Sort.by(option))));
        modelAndView.addObject("option", option);
        return modelAndView;
    }

    @GetMapping("/{id}/detail")
    public ModelAndView detail(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/detail");
        modelAndView.addObject("employee", employeeService.findById(id));
        return modelAndView;
    }

    @ModelAttribute("employee")
    public Employee employee () {
        return new Employee();
    }

    @ModelAttribute("branches")
    public List<Branch> branches () {
        return branchService.findAll();
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("/create");
    }

    @PostMapping("/create")
    public String createEmployee(@Valid @ModelAttribute Employee employee, BindingResult bindingResult) {
        employeeCodeDuplicate.validate(employee, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/create";
        }
        employeeService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editForm(@PathVariable Long id) {
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
    public String delete(@RequestParam(value = "id") Long id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }

    @GetMapping("/search")
    public ModelAndView searchByName(@RequestParam(value = "search") String search) {
        ModelAndView modelAndView = new ModelAndView("/searchByName");
        modelAndView.addObject("employees", employeeService.findAllByName(search));
        return modelAndView;
    }

}
