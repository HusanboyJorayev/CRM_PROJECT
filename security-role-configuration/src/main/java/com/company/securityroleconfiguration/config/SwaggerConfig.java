package com.company.securityroleconfiguration.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicUserApi(){
        return GroupedOpenApi.builder()
                .group("User")
                .pathsToMatch("/user/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicCourseFeesApi(){
        return GroupedOpenApi.builder()
                .group("CourseFees")
                .pathsToMatch("/coFeeCon/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicEmployeeApi(){
        return GroupedOpenApi.builder()
                .group("Employee")
                .pathsToMatch("/emp/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicFinanceApi(){
        return GroupedOpenApi.builder()
                .group("Finance")
                .pathsToMatch("/finance/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicGroupsApi(){
        return GroupedOpenApi.builder()
                .group("Groups")
                .pathsToMatch("/groups/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicRoleApi(){
        return GroupedOpenApi.builder()
                .group("Role")
                .pathsToMatch("/role/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicRoomApi(){
        return GroupedOpenApi.builder()
                .group("Room")
                .pathsToMatch("/room/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicSalaryApi(){
        return GroupedOpenApi.builder()
                .group("Salary")
                .pathsToMatch("/salary/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicStudentApi(){
        return GroupedOpenApi.builder()
                .group("Student")
                .pathsToMatch("/student/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicSubjectApi(){
        return GroupedOpenApi.builder()
                .group("Subject")
                .pathsToMatch("/subject/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicTeacherApi(){
        return GroupedOpenApi.builder()
                .group("Teacher")
                .pathsToMatch("/teacher/**")
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes
                        ("Bearer Authentication", createAPIKeyScheme()));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }


}
