package com.learn.web;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.learn.domain.User;
import com.learn.repository.UserRepository;
import com.learn.service.PersonService;
import com.learn.stateless.security.TokenAuthenticationService;
import com.learn.stateless.security.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    //private static final Logger LOG = LoggerFactory.getLogger("edu.ucdavis.its.safetyinspection.web.JavaScript");

    @Autowired
    private Environment environment;

    @Autowired
    private PersonService personService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    private DataSource dataSource;

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

        model.addAttribute("appUrl", environment.getProperty("app.url"));
        model.addAttribute("appTitle", environment.getProperty("app.title"));
        model.addAttribute("enviroment", env);

        if (isDevTool()) {
            model.addAttribute("devtool", true);
        } else {
            model.addAttribute("devtool", false);
        }
    }

    @Profile({"local", "dev", "ci", "postprod"})
    @RequestMapping(value = "/devtool/token/{user}", method = RequestMethod.GET)
    @ResponseBody
    public String token(@PathVariable String user) throws Exception {
        if (isDevTool()) {
            StringWriter sw = new StringWriter();
            JsonFactory factory = new JsonFactory();
            JsonGenerator json = factory.createGenerator(sw);

            json.writeStartObject();
            json.writeStringField("token", tokenAuthenticationService.getTokenHandler().createTokenForUser(userRepo.findByUsername(user)));
            json.writeEndObject();
            json.close();

            return sw.toString();
        }

        throw new Exception("URL Not Found");
    }

    @RequestMapping(value = "/devtool/e2e/prepare", method = RequestMethod.GET)
    @ResponseBody
    public String e2ePrepare() throws Exception {
        if ( isDevTool() && isH2()) {
            System.out.println("Reset database ...");
            ClassPathResource reset = new ClassPathResource("data/reset.sql");
            ClassPathResource insert = new ClassPathResource("data/data.sql");
            ScriptUtils.executeSqlScript(dataSource.getConnection(), reset);
            ScriptUtils.executeSqlScript(dataSource.getConnection(), insert);
        }

        String token = token("user");
        System.out.println(token);
        return token;
    }

    private boolean isH2(){
        return environment.getProperty("hibernate.dialect").compareToIgnoreCase("org.hibernate.dialect.H2Dialect") == 0;
    }

    private boolean isDevTool(){
        String env = environment.getProperty("spring.profiles.active");

        return (env.compareToIgnoreCase("local") == 0 ||
                env.compareToIgnoreCase("dev") == 0 ||
                env.compareToIgnoreCase("demo") == 0 ||
                env.compareToIgnoreCase("ci") == 0 ||
                env.compareToIgnoreCase("postprod") == 0);
    }
}
