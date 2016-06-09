package com.memorius.web;

import com.memorius.model.Goal;
import com.memorius.service.GoalService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dpivovar on 18.05.2016.
 */
@Controller
@RequestMapping("/")
public class MemoriusController {
    private static final Logger LOG = Logger.getLogger(MemoriusController.class);
    private GoalService goalService;
    private List<String> updatedFields;

    private Goal currentGoal;

    @Autowired
    public MemoriusController(GoalService goalService) {
        this.goalService = goalService;
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String showHome(Model model) {

        if (currentGoal != null) {
            model.addAttribute("isGoalSaved", true);
            model.addAttribute("goalName", currentGoal.getName());
            currentGoal = null;
        }

        return "home";
    }


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("forward:/home");
        }

        if (error != null) {
            modelAndView.addObject("error", "Invalid username and password");
        }

        if (logout !=  null) {
            modelAndView.addObject("msg", "You've been logged out successfully.");
        }

        modelAndView.setViewName("login");

        return modelAndView;
    }


    @RequestMapping(value = "addGoal", method = RequestMethod.GET)
    public String showAddGoal(Model model) {
        Goal goal = new Goal();
        goal.setCreator(SecurityContextHolder.getContext().getAuthentication().getName());
        goal.setStatus("open");
        model.addAttribute("newGoal", goal);
        return "addGoal";
    }

    @RequestMapping(value = "addGoal", method = RequestMethod.POST)
    public String goalSubmit(@ModelAttribute("newGoal") @Valid Goal newGoal, BindingResult bindingResult, Model model) {
        //set username in controller, because I didn't find a way to set it in jsp
        newGoal.setCreator(SecurityContextHolder.getContext().getAuthentication().getName());
        newGoal.setStatus("open");

        if (bindingResult.hasErrors()) {
            return "addGoal";
        }

        goalService.saveGoal(newGoal);
        currentGoal = newGoal;

        return "redirect:/home";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }


    @RequestMapping(value = "showGoals", method = RequestMethod.GET)
    public String showGoals(Model model) {
        List<Goal> goals = goalService.findAllGoals();
        model.addAttribute("goals", goals);
        return "showGoals";
    }


    @RequestMapping(value = "goal/{goalId}")
    public String showGoal(@PathVariable String goalId, Model model) {
        Goal goal = goalService.findGoalById(Integer.valueOf(goalId));
        model.addAttribute("goal", goal);

        if (updatedFields != null) {
            model.addAttribute("updatedFields", updatedFields);
            updatedFields = null;
        }

        return "goal";
    }


    @RequestMapping(value = "editGoal/{goalId}", method = RequestMethod.GET)
    public String showEditGoal(@PathVariable String goalId, Model model) {
        Goal goal = goalService.findGoalById(Integer.valueOf(goalId));
        model.addAttribute("goal", goal);
        return "editGoal";
    }

    @RequestMapping(value = "editGoal/{goalId}", method = RequestMethod.POST)
    public ModelAndView editGoal(@ModelAttribute("goal") @Valid Goal goal, BindingResult bindingResult, @PathVariable String goalId) {

        goal.setId(Integer.valueOf(goalId));
        updatedFields = goalService.updateGoal(goal);
        return new ModelAndView("redirect:/goal/"  + goalId);
    }

}
