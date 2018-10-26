package at.fh.swenga.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

public class ThymeleafServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected ServletContextTemplateResolver resolver;
    
    protected TemplateEngine engine;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        resolver = new ServletContextTemplateResolver(config.getServletContext());
        
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setCacheable(false);
        resolver.setCharacterEncoding("utf-8");
        
        engine = new TemplateEngine();
        engine.addDialect(new LayoutDialect());
        engine.setTemplateResolver(resolver);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
    }

    protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	response.setCharacterEncoding(resolver.getCharacterEncoding());

        WebContext ctx = new WebContext(request, response, getServletContext(), request.getLocale());
        ctx.setVariable("message", "Hello Thymeleaf");
        ctx.setVariable("helloworld", "Hello <b>Thymeleaf</b>");
        String templateName = "/index.html";
        String result = engine.process(templateName, ctx);

        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println(result);
        } finally {
            out.close();
        }
    }
}

