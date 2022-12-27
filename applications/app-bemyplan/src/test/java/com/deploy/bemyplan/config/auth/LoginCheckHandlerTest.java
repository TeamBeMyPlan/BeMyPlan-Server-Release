package com.deploy.bemyplan.config.auth;

import com.deploy.bemyplan.jwt.JwtHeader;
import com.deploy.bemyplan.jwt.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        mockRequest.addHeader(JwtHeader.AUTH, "token");
        given(spyJwtService.getSubject("token")).willReturn("1");
        given(spyJwtService.verifyToken("token")).willReturn(true);

        Long result = sut.getUserId(mockRequest);

        assertThat(result).isEqualTo(1L);
    }

    @Test
    void getUserId_throwsNotAuthException_whenDoesNotValidToken() {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.addHeader(JwtHeader.AUTH, "token");
        given(spyJwtService.getSubject("token")).willReturn("1");
        given(spyJwtService.verifyToken("token")).willReturn(false);

        assertThatThrownBy(() -> sut.getUserId(mockRequest))
                .isInstanceOf(NotAuthException.class);
    }

    @Test
    void getUserId_throwsNotAuthException_whenDoesNotExistToken() {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();

        assertThatThrownBy(() -> sut.getUserId(mockRequest))
                .isInstanceOf(NotAuthException.class);
    }

    @Test
    void getUserId_throwsNotAuthException_whenNotRightToken() {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.addHeader(JwtHeader.AUTH, "token");
        given(spyJwtService.getSubject("token")).willReturn("not long number");
        given(spyJwtService.verifyToken("token")).willReturn(true);

        assertThatThrownBy(() -> sut.getUserId(mockRequest))
                .isInstanceOf(NotAuthException.class);
    }
}