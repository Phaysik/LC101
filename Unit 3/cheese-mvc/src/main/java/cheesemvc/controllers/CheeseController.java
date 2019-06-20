package cheesemvc.controllers;

import cheesemvc.models.Cheese;
import cheesemvc.models.Category;
import cheesemvc.models.data.CategoryDao;
import cheesemvc.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping("")
    public String index(Model model) {
        String title = "My Cheeses";

        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", title);
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheese(Model model, @ModelAttribute @Valid Cheese newCheese, Errors errors, @RequestParam int categoryId) {

        /*
         * Cheese newCheese = new Cheese();
         * newCheese.setName(Request.getParameter("name"));
         * newCheese.setDescription(Request.getParameter("description"));
         */
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/add";
        }

        Optional<Category> category = categoryDao.findById(categoryId);
        newCheese.setCategory(category.get());
        cheeseDao.save(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String removePage(Model model) {
        String title = "Remove Cheese";

        model.addAttribute("title", title);
        model.addAttribute("cheeses", cheeseDao.findAll());

        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String deleteCheese(@RequestParam int[] cheeseIds) {

        for (int cheeseId: cheeseIds) {
            cheeseDao.deleteById(cheeseId);
        }

        return "redirect:";
    }

    @RequestMapping(value = "category", method = RequestMethod.GET)
    public String category(Model model, @RequestParam int id) {

        Optional<Category> category = categoryDao.findById(id);
        List<Cheese> cheeses = category.get().getCheeses();
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Cheeses in Category: " + category.get().getName());
        return "cheese/index";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        model.addAttribute("cheese", cheeseDao.findById(cheeseId));
        model.addAttribute("title", "Edit Cheese");
        model.addAttribute("cheeseTypes", categoryDao.findAll());

        return "cheese/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(Model model, int cheeseId, @ModelAttribute @Valid Cheese newCheese, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Cheese");
            model.addAttribute("cheeseTypes", categoryDao.findAll());

            return "cheese/edit";
        }

        Optional<Cheese> oldCheese = cheeseDao.findById(cheeseId);

        oldCheese.get().setCategory(newCheese.getCategory());
        oldCheese.get().setName(newCheese.getName());
        oldCheese.get().setDescription(newCheese.getDescription());
        oldCheese.get().setRating(newCheese.getRating());

        return "redirect:";
    }
}
