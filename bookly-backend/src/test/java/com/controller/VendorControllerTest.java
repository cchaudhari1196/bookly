package com.controller;

import com.entities.Vendor;
import com.service.VendorService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(VendorController.class)
class VendorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VendorService vendorservice;

    @Test
    void singleVendor() throws Exception {
        when(vendorservice.getVendor(1)).thenReturn(new Vendor(1,"Chirag", 9403584585L, "Pune","cch@gmail.com","password"));
        mvc.perform(get("/vendor/getvendor/1")).
                andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.v_name").value("Chirag"));
    }
}