package org.openpaas.paasta.marketplace.web.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.openpaas.paasta.marketplace.web.user.domain.Category;
import org.openpaas.paasta.marketplace.web.user.domain.Software;
import org.openpaas.paasta.marketplace.web.user.domain.Type;
import org.openpaas.paasta.marketplace.web.user.domain.Yn;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/softwares")
@Slf4j
public class SoftwareController {

    private static List<Software> testData = new ArrayList<>();
    private static List<Category> categories = Arrays.asList(new Category(1L,"샘플데이터"),
                                                            new Category(2L, "샘플데이터2"),
                                                            new Category(3L, "샘플데이터3"),
                                                            new Category(4L, "샘플데이터4"));

    @GetMapping
    public String getSoftwares(Model model, @AuthenticationPrincipal OAuth2User oauth2User, HttpSession httpSession, Software software) {
        httpSession.setAttribute("yourName", oauth2User.getAttributes().get("user_name"));
        model.addAttribute("categories", categories);
        model.addAttribute("softwares", testData);
        return "contents/software-list";
    }

    @GetMapping(value = "/{id}")
    public String getSoftware(Model model, @PathVariable Integer id) {
        model.addAttribute("software", testData.get(id));
        return "contents/software-detail";
    }

    @PostMapping(value = "/{id}")
    public String updateSoftware(Model model, @PathVariable Integer id) {
        model.addAttribute("software", testData.get(id));
        return "redirect:/softwares/" + id;
    }

    @GetMapping(value = "/create")
    public String createSoftwareHtml(Model model, @ModelAttribute Software software) {
        model.addAttribute("types", Type.values());
        model.addAttribute("yns", Yn.values());
        model.addAttribute("categories", categories);
        return "contents/software-create";
    }

    @PostMapping
    public String createSoftware(@Valid Software software, BindingResult bindingResult, @RequestParam(value = "screenshots") MultipartFile[] screenshots, @RequestParam(value = "iconFile") MultipartFile iconFile,
                                 @RequestParam(value = "productFile") MultipartFile productFile, @RequestParam(value = "environmentFile") MultipartFile environmentFile) {
        if (bindingResult.hasErrors()) {
            return "contents/software-create";
        }

        log.info("소프트 웨어! {}", software);
        software.setIconFile(iconFile);
        software.setProductFile(productFile);
        software.setEnvironmentFile(environmentFile);
        software.setScreenshotFiles(screenshots);
        testData.add(software);
        return "redirect:/softwares";
    }
}
