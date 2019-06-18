package cheesemvc.controllers;

import cheesemvc.models.Cheese;
import cheesemvc.models.CheeseData;
import cheesemvc.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    @RequestMapping("")
    public String index(Model model) {
        String title = "My Cheeses";

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", title);
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheese(Model model, @ModelAttribute @Valid Cheese newCheese, Errors errors) {

        /*
         * Cheese newCheese = new Cheese();
         * newCheese.setName(Request.getParameter("name"));
         * newCheese.setDescription(Request.getParameter("description"));
         */
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "cheese/add";
        }
        CheeseData.add(newCheese);
        // Redirect to /cheese
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String removePage(Model model) {
        String title = "Remove Cheese";

        model.addAttribute("title", title);
        model.addAttribute("cheeses", CheeseData.getAll());

        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String deleteCheese(@RequestParam int[] cheeseIds) {

        for (int cheeseId: cheeseIds) {
            CheeseData.remove(cheeseId);
        }

        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        model.addAttribute("cheese", CheeseData.getById(cheeseId));
        model.addAttribute("title", "Edit Cheese");
        model.addAttribute("cheeseTypes", CheeseType.values());

        return "cheese/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(Model model, int cheeseId, @ModelAttribute @Valid Cheese newCheese, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Cheese");
            model.addAttribute("cheeseTypes", CheeseType.values());

            return "cheese/edit";
        }

        Cheese oldCheese = CheeseData.getById(cheeseId);

        oldCheese.setType(newCheese.getType());
        oldCheese.setName(newCheese.getName());
        oldCheese.setDescription(newCheese.getDescription());
        oldCheese.setRating(newCheese.getRating());

        return "redirect:";
    }
}
