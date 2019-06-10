package cheesemvc.controllers;

import cheesemvc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "")
    public String index() {
        return "user/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Create an Account");

        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute User user, String verify) {
        if (user.getPassword().equals(verify)) {
            return "redirect:";
        } else {
            model.addAttribute("title", "Create an Account");
            model.addAttribute("passwordError", "Error");
            model.addAttribute("uName", user.getUsername());
            model.addAttribute("uMail", user.getEmail());

            return "user/add";
        }
    }
}
