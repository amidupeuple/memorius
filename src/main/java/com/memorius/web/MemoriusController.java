package com.memorius.web;

import com.memorius.model.Goal;
import com.memorius.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by dpivovar on 18.05.2016.
 */
@Controller
@RequestMapping("/")
public class MemoriusController {

    private GoalService goalService;

    @Autowired
    public MemoriusController(GoalService goalService) {
        this.goalService = goalService;
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String showHome() {
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
        model.addAttribute("newGoal", goal);
        return "addGoal";
    }

    @RequestMapping(value = "addGoal", method = RequestMethod.POST)
    public String goalSubmit(@ModelAttribute Goal newGoal, Model model ) {
        //set username in controller, because I didn't find a way to set it in jsp
        newGoal.setCreator(SecurityContextHolder.getContext().getAuthentication().getName());

        goalService.saveGoal(newGoal);
        model.addAttribute("goal", newGoal);
        return "result";
    }


    @RequestMapping(value = "showGoals", method = RequestMethod.GET)
    public String showGoals(Model model) {
        List<Goal> goals = goalService.findAllGoals();
        model.addAttribute("goals", goals);
        return "showGoals";
    }
}
