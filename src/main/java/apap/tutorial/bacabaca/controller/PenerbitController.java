package apap.tutorial.bacabaca.controller;

import apap.tutorial.bacabaca.DTO.PenerbitMapper;
import apap.tutorial.bacabaca.DTO.request.CreatePenerbitRequestDTO;
import apap.tutorial.bacabaca.model.Penerbit;
import apap.tutorial.bacabaca.service.BukuService;
import apap.tutorial.bacabaca.service.PenerbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PenerbitController {
    @Autowired
    PenerbitService penerbitService;

    @Autowired
    BukuService bukuService;

    @Autowired
    PenerbitMapper penerbitMapper;

    @GetMapping("penerbit/create")
    public String formAddPenerbit(Model model) {
        var penerbitDTO = new CreatePenerbitRequestDTO();

        model.addAttribute("penerbitDTO", penerbitDTO);

        return "form-create-penerbit";
    }

    @PostMapping("penerbit/create")
    public String addPenerbit(@ModelAttribute CreatePenerbitRequestDTO createPenerbitRequestDTO, Model model) {
        var penerbit = penerbitMapper.createPenerbitRequestDTOToPenerbit(createPenerbitRequestDTO);

        penerbit = penerbitService.createPenerbit(penerbit);

        model.addAttribute("penerbit", createPenerbitRequestDTO);

        return "success-create-penerbit";
    }

    @GetMapping("penerbit/viewall")
    public String listPenerbit(Model model) {
        var listPenerbit = penerbitService.getAllPenerbit();

        model.addAttribute("listPenerbit", listPenerbit);

        return "viewall-penerbit";
    }

    @GetMapping("penerbit/{idPenerbit}")
    public String detailPenerbit(@PathVariable("idPenerbit") long idPenerbit, Model model) {
        var penerbit = penerbitService.getPenerbitById(idPenerbit);

        model.addAttribute("penerbit", penerbit);

        return "view-penerbit";
    }
}
