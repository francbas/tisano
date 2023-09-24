package org.francescobasile.tisano;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(
        filterName = "FaiQualcosaFilter",
        urlPatterns = {
                "/securedarea/*",
                "/filtro/*"
        }
)
public class FaiQualcosaFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                
    }
}
