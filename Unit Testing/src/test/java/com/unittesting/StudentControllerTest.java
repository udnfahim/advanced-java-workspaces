package com.unittesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unittesting.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {


    /*
    I Use to complete this take help from AI and Search Engine

    Extra needed:

        An GlobalExceptionalHandler class
        <java.version>21</java.version>  java version
        <version>3.2.2</version>  spring boot  version

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>

     */

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setup() throws Exception {
        Student s = new Student(1L, "Fahim");

        mockMvc.perform(post("/students")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(s)))
                .andExpect(status().isOk());
    }

    @Test
    void addStudent() throws Exception {
        Student s = new Student(2L, "Alex");

        mockMvc.perform(post("/students")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(s)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alex"));
    }

    @Test
    void getStudentById() throws Exception {
        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Fahim"));
    }

    @Test
    void deleteStudent() throws Exception {
        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isInternalServerError());
    }


    @Test
    void studentNotFound() throws Exception {
        mockMvc.perform(get("/students/999"))
                .andExpect(status().isInternalServerError());
    }


    @Test
    void loginPermitAll() throws Exception {
        mockMvc.perform(get("/students/login"))
                .andExpect(status().isOk());
    }

    @Test
    void adminDenied() throws Exception {
        mockMvc.perform(get("/students/admin/dashboard"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    void dashboardAuthenticated() throws Exception {
        mockMvc.perform(get("/students/dashboard"))
                .andExpect(status().isOk());
    }
}
/*
Required Tests:
-- Add student
-- Get student by ID
-- Delete student
-- Exception when the student is not found
-- Call the following controllers and check the HTTP body and status is 200 or appropriate as given scenario.

 */
