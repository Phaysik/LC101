package cheesemvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    static HashMap<String, String> cheeses = new HashMap<>();
    // static ArrayList<String> cheeses = new ArrayList<>();

    @RequestMapping("")
    public String index(Model model) {
        String title = "My Cheeses";

        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", title);
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        String title = "Add Cheese";

        model.addAttribute("title", title);
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheese(@RequestParam String cheeseName, @RequestParam String cheeseDesc) {
        cheeses.put(cheeseName, cheeseDesc);

        // Redirect to /cheese
        return "redirect:";
    }
}
