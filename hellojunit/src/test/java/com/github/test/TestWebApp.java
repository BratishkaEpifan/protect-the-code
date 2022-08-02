package com.github.test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/*
	TODO
	Добавить WebApplicationContext для тестов
	Добавить MockMvc
	Создать метод с аннотацией @Before которая создает mockMvc на основе webApplicationContext
	Написать метод тестирования метода /employee
		Должен проверяться HTTP статус ответа
		Должен проверять contentType ответа
		Должно проверять значение поля "name"
		Должно проверять значение поля "designation"
		Должно проверять значение поля "salary"
		Должно проверять значение поля "empId"
*/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebAppConfiguration
public class TestWebApp extends SpringBootHelloWorldTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    @Order(1)
    @DisplayName("Http status is 200")
    void testStatus() {
        try {
            this.mockMvc.perform(get("/employee")).andDo(print()).andExpect(status().isOk()).andReturn();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(2)
    @DisplayName("Content type is json")
    void testContentType() {
        try {
            this.mockMvc.perform(get("/employee")).andDo(print()).andExpect(content().contentType("application/json")).andReturn();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(3)
    @DisplayName("Name is emp1")
    void testName() {
        try {
            this.mockMvc.perform(get("/employee")).andDo(print()).andExpect(jsonPath("$.name").value("emp1")).andReturn();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(4)
    @DisplayName("Designation is manager")
    void testDesignation() {
        try {
            this.mockMvc.perform(get("/employee")).andDo(print()).andExpect(jsonPath("$.designation").value("manager")).andReturn();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(5)
    @DisplayName("Salary is 3000")
    void testSalary() {
        try {
            this.mockMvc.perform(get("/employee")).andDo(print()).andExpect(jsonPath("$.salary").value(3000)).andReturn();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(6)
    @DisplayName("Id is 1")
    void testId(){
        try {
            this.mockMvc.perform(get("/employee")).andDo(print()).andExpect(jsonPath("$.empId").value(1)).andReturn();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }







	// your solution

}
