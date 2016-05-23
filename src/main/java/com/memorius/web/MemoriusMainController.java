package com.memorius.web;

import com.memorius.model.Goal;
import com.memorius.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dpivovar on 18.05.2016.
 */
@Controller
@RequestMapping("/")
public class MemoriusMainController {

    private GoalService goalService;

    @Autowired
    public MemoriusMainController(GoalService goalService) {
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
    public String showAddGoal() {
        return "addGoal";
    }


    @RequestMapping(value = "showGoals", method = RequestMethod.GET)
    public String showGoals(Model model) {
        Goal firstGoal = goalService.findGoalById(0);
        model.addAttribute("goal", firstGoal);
        return "showGoals";
    }
}
