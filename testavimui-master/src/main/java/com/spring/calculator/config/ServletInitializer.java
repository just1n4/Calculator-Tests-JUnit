package com.spring.calculator.config;

import com.spring.calculator.CalculatorApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// Servlet yra Java programa, veikianti WEB serveryje.
// Servlet paleidžiamas, kai vartotojas spustelėja nuorodą, pateikia formą ar atlieka kito tipo veiksmus svetainėje.
// Kiekvienas kliento request'as praeina per Servlet, kuris jį perduoda Controller'io RequestMapping atributui.
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CalculatorApplication.class);
    }

}
