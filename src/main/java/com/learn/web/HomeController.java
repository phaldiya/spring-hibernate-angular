package com.learn.web;

import com.learn.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    //private static final Logger LOG = LoggerFactory.getLogger("edu.ucdavis.its.safetyinspection.web.JavaScript");

    @Autowired
    private Environment environment;

    @Autowired
    private PersonService personService;


    @RequestMapping("/")
    public String index(Model model) throws Exception {
        populateSiteInformation(model);
        return "index";
    }

    @RequestMapping("/signoff")
    public String exit(Model model, HttpServletRequest request) {
        //request.getSession().removeAttribute(ShibHeaderSessionInjector.SHIB_DEFAULT_SESSION_INJECTION_KEY);
        SecurityContextHolder.clearContext();

        String env = environment.getProperty("spring.profiles.active");
        if (env.compareToIgnoreCase("demo") == 0) {
            String url = request.getRequestURL().toString();
            url = url.substring(0, url.indexOf(request.getRequestURI()));
            return "redirect:" + url + "/auth-demo/";
        } else {
            populateSiteInformation(model);
            return "signoff";
        }
    }

    @Profile({"local", "dev", "demo", "ci", "postprod"})
    @RequestMapping("/demo/")
    public String demo(Model model) {
        return  "demo";
    }

    @RequestMapping(value = "/secure/", method = RequestMethod.GET)
    public String secure(Model model, HttpServletRequest request) throws Exception {
        populateSiteInformation(model);
        return "secure";
    }

    @RequestMapping(value = "/secure/", method = RequestMethod.POST)
    public Object secure() throws Exception {
        return "redirect:/secure/";
    }

    @RequestMapping(value = "/secure/signin/", method = RequestMethod.GET)
    public String signin() throws Exception {
        return "redirect:/secure/";
    }

    @RequestMapping(value = "/sit.manifest")
    public View manifest2() {
        InternalResourceView view = new InternalResourceView("/resources/sit.manifest");
        view.setContentType("text/cache-manifest");
        return view;
    }

//    @RequestMapping(value = "/api/log", method = RequestMethod.POST)
//    @ResponseBody
//    public void log(String stack) {
//        LOG.error(stack);
//    }
//
//    @RequestMapping(value = "/api/ping", method = RequestMethod.GET)
//    @ResponseBody
//    public Object log() {
//        return "success";
//    }

    protected void populateSiteInformation(Model model) {
        String env = environment.getProperty("spring.profiles.active");

        model.addAttribute("siteUrl", environment.getProperty("site.url"));
        model.addAttribute("siteTitle", environment.getProperty("site.title"));
        model.addAttribute("enviroment", env);

        if (env.compareToIgnoreCase("local") == 0 ||
                env.compareToIgnoreCase("dev") == 0 ||
                env.compareToIgnoreCase("demo") == 0 ||
                env.compareToIgnoreCase("ci") == 0 ||
                env.compareToIgnoreCase("postprod") == 0) {
            model.addAttribute("devtool", true);
        } else {
            model.addAttribute("devtool", false);
        }


    }
}
