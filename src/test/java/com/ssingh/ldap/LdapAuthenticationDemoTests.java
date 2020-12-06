package com.ssingh.ldap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc()
class LdapAuthenticationDemoTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void redirectedToLogin() throws Exception {
        mockMvc.perform(formLogin("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    public void redirectedAfterLogin() throws Exception {
        mockMvc.perform(formLogin("/login").user("iamsingh").password("password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/api/whoami"));
    }

    @Test
    public void unSuccessfulLogin() throws Exception {
        mockMvc.perform(formLogin("/login").user("iamsingh").password("invalid-password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"));
    }

    @WithMockUser
    @Test
    public void whoAmI() throws Exception {
        mockMvc.perform(get("/api/whoami"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Now you see me!!!.")));
    }

}
