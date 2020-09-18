package info.diwe.handbuch.controller;

import info.diwe.handbuch.model.BookEntry;
import info.diwe.handbuch.repository.BookEntryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookEntry")
public class BookEntryController {

    private BookEntryRepository bookEntryRepository;

    public BookEntryController(BookEntryRepository bookEntryRepository) {
        this.bookEntryRepository = bookEntryRepository;
    }

    @GetMapping("/list")
    public ModelAndView getAllEintraege(ModelAndView modelAndView) {
        List<BookEntry> bookEntries = bookEntryRepository.findAll();
        modelAndView.addObject("bookEntries", bookEntries);
        modelAndView.setViewName("bookEntry/list");
        return modelAndView;
    }

    @GetMapping("/addEntry")
    public ModelAndView addGet(ModelAndView modelAndView) {

        BookEntry bookEntry = new BookEntry();
        modelAndView.addObject("bookEntry", bookEntry);

        modelAndView.setViewName("bookEntry/add");
        return modelAndView;
    }

    @PostMapping("/addEntry")
    public ModelAndView addPost(@ModelAttribute("bookEntry") BookEntry bookEntry, BindingResult bindingResult
            , ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("message", "BookEntry - Fehler beim speichern");
            modelAndView.addObject("alertClass", "alert-danger");
            modelAndView.addObject("bookEntry", bookEntry);
            modelAndView.setViewName("bookEntry/add");
            return modelAndView;
        }

        bookEntryRepository.save(bookEntry);
        modelAndView.addObject("message", "BookEntry hinzugefügt");
        modelAndView.addObject("alertClass", "alert-success");

        modelAndView.addObject("bookEntry", bookEntry);
        modelAndView.setViewName("bookEntry/add");

        return modelAndView;
    }


    @GetMapping("/updateEntry/{id}")
    public ModelAndView addGet(@PathVariable("id") Long id, ModelAndView modelAndView) {

        Optional<BookEntry> bookEntry = bookEntryRepository.findById(id);

        modelAndView.addObject("bookEntry", bookEntry.get());

        modelAndView.setViewName("bookEntry/update");
        return modelAndView;
    }

    @PostMapping("/updateEntry")
    public ModelAndView updatePost(@Valid BookEntry bookEntry, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("bookEntry", bookEntry);
            modelAndView.setViewName("bookEntry/update");
            return modelAndView;
        }

        bookEntryRepository.save(bookEntry);
        modelAndView.addObject("message", "BookEntry geändert");
        modelAndView.addObject("alertClass", "alert-success");

        modelAndView.addObject("bookEntry", bookEntry);
        modelAndView.setViewName("bookEntry/update");

        return modelAndView;
    }

    @GetMapping("/deleteEntry/{id}")
    public ModelAndView deleteGet(@PathVariable("id") Long id, ModelAndView modelAndView) {
        bookEntryRepository.deleteById(id);
        List<BookEntry> bookEntries = bookEntryRepository.findAll();

        modelAndView.addObject("message", "BookEntry gelöscht");
        modelAndView.addObject("alertClass", "alert-success");

        modelAndView.addObject("bookEntries", bookEntries);
        modelAndView.setViewName("bookEntry/list");

        return modelAndView;
    }
}
