package org.francescobasile.tisano;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebApplication;

public class TemplateEngineSetup {
    public static final TemplateEngine registraTemplateEngine(IWebApplication application) {

        // Inizializziazione templateEngine con Thymeleaf
        WebApplicationTemplateResolver tmplres = new WebApplicationTemplateResolver(application);
        tmplres.setTemplateMode(TemplateMode.HTML);
        tmplres.setPrefix("/WEB-INF/views/");
        tmplres.setSuffix(".html");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(tmplres);
        return templateEngine;
    }
}
