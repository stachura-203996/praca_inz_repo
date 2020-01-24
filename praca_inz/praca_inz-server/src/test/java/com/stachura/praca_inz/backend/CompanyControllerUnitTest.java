package com.stachura.praca_inz.backend;

import com.stachura.praca_inz.backend.controller.CompanyController;
import com.stachura.praca_inz.backend.service.CompanyService;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructuresListElementDto;
import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CompanyControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService service;

    @Test
    @Transactional
    @WithMockUser(username = "user", authorities = "COMPANY_LIST_READ")
    public void getAllCompaniesShouldReturnCompanies() throws Exception {
        when(service.getAllCompanies()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(get("/secured/structure/company")).andExpect(status().isOk());
//                .andExpect(content().string(containsString("Hello, Mock")));
    }
}