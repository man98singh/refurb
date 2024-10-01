//package com.houstondirectauto.refurb.controller;
//
//import com.houstondirectauto.refurb.model.Request2FA;
//import com.houstondirectauto.refurb.model.Verify2FA;
//import com.houstondirectauto.refurb.service.TwoFactorAuthService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
//
//@WebMvcTest(TwoFactorAuthController.class)
//public class TwoFactorAuthControllerTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private TwoFactorAuthService twoFactorAuthService;
//
//    @Test
//    public void testRequest2FACode() throws Exception {
//        Mockito.doNothing().when(twoFactorAuthService).sendCode(anyString());
//
//        String requestBody = "{\"username\": \"testuser\"}";
//
//        mockMvc.perform(post("/2fa/request")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestBody))
//                .andExpect(status().isOk())
//                .andExpect(content().string("2FA code sent."));
//    }
//
//    @Test
//    public void testVerify2FACode() throws Exception {
//        Mockito.when(twoFactorAuthService.verifyCode(anyString(), anyString())).thenReturn(true);
//
//        String verifyBody = "{\"username\": \"testuser\", \"code\": \"123456\"}";
//
//        mockMvc.perform(post("/2fa/verify")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(verifyBody))
//                .andExpect(status().isOk())
//                .andExpect(content().string("true"));
//    }
//}
