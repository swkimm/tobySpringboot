package tobyspring.helloboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
    public static void run(Class<?> applicationClass, String... args) {
        // 스프링 컨테이너 만들기
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                // 서블릿 컨테이너 초기화
                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);

                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet",dispatcherServlet)
                            .addMapping("/*");
                });
                webServer.start();
            }
        };
        applicationContext.register(applicationClass);
        applicationContext.refresh(); // 스프링 컨테이너 초기화
    }
}
