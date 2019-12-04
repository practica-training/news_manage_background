package com.demo.practical_training.config;

/**
 * <Description> <br>
 *
 * @author luoluocaihong<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate Oct 24, 2016 <br>
 * @since V8.1<br>
 * @see com.demo.practical_training <br>
 */
//@Configuration
//@EnableWebMvc
//@EnableSwagger2
//@ComponentScan(basePackages = { "com.demo.practical_training" })
//public class SwaggerConfig {
//    /**
//     *
//     * Description: <br>
//     *
//     * @author luoluocaihong<br>
//     * @taskId <br>
//     * @return <br>
//     */
//    ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("XXX Web SelfService APIs")
//                .description("")
//                .license("")
//                .licenseUrl("")
//                .termsOfServiceUrl("")
//                .version("1.0.0")
//                .build();
//    }
//
//    /**
//     *
//     * Description: <br>
//     *
//     * @author luoluocaihong<br>
//     * @taskId <br>
//     * @return <br>
//     */
//    @Bean
//    public Docket customImplementation() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("XXXX"))
//                .build()
//                .directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class)
//                .directModelSubstitute(org.joda.time.DateTime.class, java.util.Date.class)
//                .apiInfo(apiInfo());
//    }
//
//}