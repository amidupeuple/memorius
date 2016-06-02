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
import java.util.List;

/**
 * Created by dpivovar on 18.05.2016.
 */
@Controller
@RequestMapping("/")
public class MemoriusController {
    private static final Logger LOG = Logger.getLogger(MemoriusController.class);
    private GoalService goalService;

    @Autowired
    public MemoriusController(GoalService goalService) {
        this.goalService = goalService;
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String showHome(@RequestParam(value = "isGoalSaved", required = false) String isGoalSaved,
                           @RequestParam(value = "goalName", required = false) String goalName,
                           Model model) {
        if (isGoalSaved != null) {
            model.addAttribute("isGoalSaved", isGoalSaved);
        }
        if (goalName != null) {
            model.addAttribute("goalName", goalName);
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
        model.addAttribute("goalName", newGoal.getName());
        model.addAttribute("isGoalSaved", true);
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
        model.addAttribute("goalId", goalId);
        return "goal";
    }
}
