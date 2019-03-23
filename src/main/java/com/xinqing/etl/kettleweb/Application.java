package com.xinqing.etl.kettleweb;

import com.xinqing.etl.kettleweb.repository.impl.SimpleJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 入口
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@EnableJpaRepositories(repositoryBaseClass = SimpleJpaRepositoryImpl.class)
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
