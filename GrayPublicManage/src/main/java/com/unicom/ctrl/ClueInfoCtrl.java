package com.unicom.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clue")
public class ClueInfoCtrl {

    @RequestMapping("/page")
    public String page() {
        return "/clue/page";
    }
}
