package com.cisdi.transaction.config.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yuw
 * @version 1.0
 * @date 2022/8/3 17:27
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket buildDocket() {
//        ParameterBuilder token=new ParameterBuilder();
//        List<Parameter> pars=new ArrayList<>();
//        token.name("accessToken").description(
//                        "<p>登录令牌在请求头的格式</p>"+
//                                "<p>\"accessToken\":\"Bearer staticToken\"</p>")
//                .modelRef(new ModelRef("string")).parameterType("header")
//                .required(false).build();
//        pars.add(token.build());


        return new Docket(DocumentationType.SWAGGER_2)
                .protocols(Collections.singleton("http"))
                .apiInfo(buildApiInf()) // .apiInfo(apiInfo())
                //.globalOperationParameters(pars)
                .enable(true)//开启swagger 默认true 开启
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cisdi.transaction"))//哪些包名下可以是用swagger
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder().title("swagger-bootstrap-ui RESTFUL APIS 接口文档")
                .description("接口文档")
                .termsOfServiceUrl("http://wapp.ncyunqi.com")
                .contact(new Contact("指尖上跳动的旋律","www.baidu.com","1907586822@qq.com"))
                .version("1.0.0")
                .build();
    }
}
