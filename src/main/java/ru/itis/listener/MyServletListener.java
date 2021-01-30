package ru.itis.listener;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.repostories.*;
import ru.itis.services.*;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //context
        ServletContext servletContext = sce.getServletContext();

        //datasource
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://ec2-54-225-214-37.compute-1.amazonaws.com:5432/d2o4h020vn96oh");
        dataSource.setUsername("npoycscnujgbnj");
        dataSource.setPassword("81956dc309f6b8a0ae8d4276919525a59239ddcd678cfd3dab07db854d07e98e");

        //validation
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        //encoders
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        BASE64Encoder fileEncoder = new BASE64Encoder();

        //repositories
        UserRepository userRepository = new UserRepository(dataSource);
        SessionRepository sessionRepository = new SessionRepository(dataSource);
        MovieRepository movieRepository = new MovieRepository(dataSource);
        FavouriteRepository favouriteRepository = new FavouriteRepository(dataSource);
        DirectorRepository directorRepository = new DirectorRepository(dataSource);
        GalleryRepository galleryRepository = new GalleryRepository(dataSource);

        //services
        RegistrationService registrationService = new RegistrationServiceImpl(userRepository, passwordEncoder);
        LoginService loginService = new LoginServiceImpl(userRepository, passwordEncoder, sessionRepository);
        UserService userService = new UserServiceImpl(userRepository, sessionRepository, favouriteRepository, movieRepository);
        LogoutService logoutService = new LogoutServiceImpl(sessionRepository);
        MovieService movieService = new MovieServiceImpl(movieRepository);
        PhotoService photoService = new PhotoServiceImpl(fileEncoder, galleryRepository);
        DirectorService directorService = new DirectorServiceImpl(directorRepository);
        FavouriteService favouriteService = new FavouriteServiceImpl(favouriteRepository);

        //adding services to context
        servletContext.setAttribute("registrationService", registrationService);
        servletContext.setAttribute("loginService", loginService);
        servletContext.setAttribute("validator", validator);
        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("logoutService", logoutService);
        servletContext.setAttribute("movieService", movieService);
        servletContext.setAttribute("photoService", photoService);
        servletContext.setAttribute("directorService", directorService);
        servletContext.setAttribute("favouriteService", favouriteService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
