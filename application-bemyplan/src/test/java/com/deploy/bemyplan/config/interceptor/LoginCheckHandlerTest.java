package com.deploy.bemyplan.config.interceptor;

import com.deploy.bemyplan.jwt.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static com.deploy.bemyplan.jwt.JwtHeader.AUTH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class LoginCheckHandlerTest {

    private LoginCheckHandler sut;
    private JwtService spyJwtService;

    @BeforeEach
    void setUp() {
        spyJwtService = mock(JwtService.class);
        sut = new LoginCheckHandler(spyJwtService);
    }

    @Test
    void getUserId_returnsJwtSubject() {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.addHeader(AUTH, "token");
        given(spyJwtService.getSubject("token")).willReturn("1");
        given(spyJwtService.verifyToken("token")).willReturn(true);

        Long result = sut.getUserId(mockRequest);

        assertThat(result).isEqualTo(1L);
    }

    @Test
    void getUserId_returnsMinusOne_whenDoesNotValidToken() {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.addHeader(AUTH, "token");
        given(spyJwtService.getSubject("token")).willReturn("1");
        given(spyJwtService.verifyToken("token")).willReturn(false);

        Long result = sut.getUserId(mockRequest);

        assertThat(result).isEqualTo(-1L);
    }

    @Test
    void getUserId_returnsMinusOne_whenDoesNotExistToken() {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();

        Long result = sut.getUserId(mockRequest);

        assertThat(result).isEqualTo(-1L);
    }

    @Test
    void getUserId_returnsMinusOne_whenNotRightToken() {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.addHeader(AUTH, "token");
        given(spyJwtService.getSubject("token")).willReturn("abcd");
        given(spyJwtService.verifyToken("token")).willReturn(true);

        Long result = sut.getUserId(mockRequest);

        assertThat(result).isEqualTo(-1L);
    }
}