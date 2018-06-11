package xyz.welab.wmap.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @package: xyz.welab.wmap.controller
 * @author: qing
 * @date: 2018/5/15
 * @time: 9:33
 **/
@Controller
public class PageController {

    private final Logger LOGGER = LogManager.getLogger(PageController.class);

    @RequestMapping("/index")
    public String index(Model model) {
        LOGGER.info("访问index界面");
        return "index";
    }

}
