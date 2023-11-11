package org.francescobasile.tisano;

import jakarta.inject.Inject;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.francescobasile.tisano.entity.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.IWebRequest;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@WebServlet(
        name = "UserController",
        urlPatterns = {
                "/user",
                "/user/*"
        },
        initParams = {
                @WebInitParam(name = "param1", value = "value1"),
                @WebInitParam(name = "param2", value = "value2"),
        }
)
public class UserController extends HttpServlet {

    JakartaServletWebApplication application;
    TemplateEngine templateEngine;

//    @Inject
//    User utente;//TODO: cancellare questa prova di injection

    public void init(final FilterConfig filterConfig) {

//        this.application =
//                JakartaServletWebApplication.buildApplication(getServletContext());
//        this.templateEngine = TemplateEngineSetup.registraTemplateEngine(this.application);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        this.application = JakartaServletWebApplication.buildApplication(request.getServletContext());
        this.templateEngine = TemplateEngineSetup.registraTemplateEngine(this.application);
//        UserService userService = new UserService();

//        bufferedReader.readLine(); // get chars from request buffered reader
//        ServletInputStream inputStream = request.getInputStream();//get binary data from request stream
        BufferedReader bufferedReader = request.getReader();

        //http://[host]:[port][request-path]?[query-string]
        List<String> ls2 = bufferedReader.lines().toList();
        List<String> ls = Arrays.asList(
                request.getContextPath(),
                request.getServletPath(),
                request.getPathInfo(),
                request.getQueryString()
        );


//        getServletContext()
//                .getRequestDispatcher(url)
//                .forward(request, response);
        final IWebExchange webExchange = this.application.buildExchange(request, response);
        final IWebRequest webRequest = webExchange.getRequest();
        final Writer writer = response.getWriter();
//        final String path = webRequest.getPathWithinApplication();

        User utente = new User();
        utente.setId(2);
        utente.setUsername("username01");
        utente.setPassword("segreto01");
        User utente2 = new User("username02", "password01");

        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
//        ctx.setVariable("users", userService.findAll());
        ctx.setVariable("users", List.of(new User[]{utente, utente2}));
        ctx.setVariable("utente", utente);
        ctx.setVariable("ls", ls);
        ctx.setVariable("ls2", ls2);
//        ctx.setVariable("users", (new UserService()).findAll());
//        ctx.setVariable("path", request.getContextPath()); // inserire qui variabili per il view
        templateEngine.process("user", ctx, writer);
    }

    public void destroy() {
    }
}