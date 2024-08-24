package apap.tutorial.bacabaca.controller;

import apap.tutorial.bacabaca.DTO.BukuMapper;
import apap.tutorial.bacabaca.DTO.request.CreateBukuRequestDTO;
import apap.tutorial.bacabaca.DTO.request.UpdateBukuRequestDTO;
import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.service.BukuService;
import apap.tutorial.bacabaca.service.PenerbitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class BukuController {

    @Autowired
    private BukuMapper bukuMapper;

    @Autowired
    private BukuService bukuService;

    @Autowired
    private PenerbitService penerbitService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("buku/create")
    public String formAddBuku(Model model) {
        var bukuDTO = new CreateBukuRequestDTO();

        model.addAttribute("bukuDTO", bukuDTO);
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());

        return "form-create-buku";
    }

    @PostMapping("buku/create")
    public String addBuku(@Valid @ModelAttribute CreateBukuRequestDTO bukuDTO, Model model) {
        if (bukuService.isJudulExist(bukuDTO.getJudul())){
            var errorMessage = "Judul sudah ada";
            model.addAttribute("errorMessage", errorMessage);
            return "error-view";
        }

        var buku = bukuMapper.createBukuRequestDTOToBuku(bukuDTO);

        bukuService.saveBuku(buku);

        model.addAttribute("id", buku.getId());

        model.addAttribute("judul", buku.getJudul());

        return "success-create-buku";
    }

    @GetMapping("buku/viewall")
    public String listBuku(Model model) {
        List<Buku> listBuku = bukuService.getAllBuku();

        model.addAttribute("listBuku", listBuku);
        return "viewall-buku";
    }

    @GetMapping("buku")
    public String detailBuku(@RequestParam("id") UUID id, Model model) {
        var buku = bukuService.getBukuById(id);

        model.addAttribute("buku", buku);
        return "view-buku";
    }

    @GetMapping(value="buku/{id}")
    public String detailBukuPath(@PathVariable("id") UUID id, Model model) {
        var buku = bukuService.getBukuById(id);

        model.addAttribute("buku", buku);
        return "view-buku";
    }

    @GetMapping("/buku/{id}/update")
    public String formUpdateBuku(@PathVariable("id") UUID id, Model model) {
        var buku = bukuService.getBukuById(id);

        var bukuDTO = bukuMapper.bukuToUpdateBukuRequestDTO(buku);

        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());
        model.addAttribute("bukuDTO", bukuDTO);

        return "form-update-buku";
    }

    @PostMapping("/buku/update")
    public String updateBuku(@ModelAttribute UpdateBukuRequestDTO bukuDTO, Model model) {
        if (bukuService.isJudulExist(bukuDTO.getJudul(), bukuDTO.getId())){
            var errorMessage = "Judul sudah ada";
            model.addAttribute("errorMessage", errorMessage);
            return "error-view";
        }

        var bukuFromDto = bukuMapper.updateBukuRequestDTOToBuku(bukuDTO);

        var buku = bukuService.updateBuku(bukuFromDto);

        model.addAttribute("id", buku.getId());

        model.addAttribute("judul", buku.getJudul());

        return "success-update-buku";
    }

    @GetMapping("/buku/{id}/delete")
    public String deleteBuku(@PathVariable("id") UUID id, Model model) {
        var buku = bukuService.getBukuById(id);

        bukuService.deleteBuku(buku);

        model.addAttribute("id", id);

        return "success-delete-buku";
    }
}
