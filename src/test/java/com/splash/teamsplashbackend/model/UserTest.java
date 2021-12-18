//package com.splash.teamsplashbackend.model;
//
//
//import com.splash.teamsplashbackend.dto.user.UserRequestDto;
//import com.splash.teamsplashbackend.dto.user.UserResponseDto;
//import com.splash.teamsplashbackend.jwt.JwtTokenProvider;
//import com.splash.teamsplashbackend.repository.UserRepository;
//import com.splash.teamsplashbackend.service.UserService;
//import com.splash.teamsplashbackend.validator.UserValidator;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.annotation.Rollback;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//
////@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
////@TestInstance(TestInstance.Lifecycle.PER_CLASS)
////@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
//
//@ExtendWith(MockitoExtension.class)


//@Transactional @Rollback(value = true)
//class UserTest {
//
////    @Autowired
////    private UserService userService;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @Mock
//    private JwtTokenProvider jwtTokenProvider;
//
////    @Mock
////    private UserValidator userValidator;
//
//
//    private String username;
//    private String password;
//    private String name;
//    private String nickname;
//
//
//    @BeforeEach
//    void setup() {
//        username = "nao@naver.com";
//        password = "qwerr";
//        name = "Lim";
//        nickname = "nao";
//    }
//
//    @Nested
//    @DisplayName("회원가입 테스트")
//    class CreateUserTest {
//
//        @Nested
//        @DisplayName("정상 케이스")
//        class SuccessCases {
//            @Test
//            @DisplayName("케이스1")
//            void createUser_Normal() {
//                // given
//                UserRequestDto userRequestDto = new UserRequestDto(
//                        username
//                        , password
//                        , name
//                        , nickname
//                );
//
//                // when
//                User user = User.builder()
//                        .username(userRequestDto.getUsername())
//                        .password(userRequestDto.getPassword())
//                        .name(userRequestDto.getName())
//                        .nickname(userRequestDto.getNickname())
//                        .build();
//
//                // then
//
//                assertNull(user.getId());
//                assertEquals(username, user.getUsername());
//                assertEquals(password, user.getPassword());
//                assertEquals(name, user.getName());
//                assertEquals(nickname, user.getNickname());
//            }
//
//
//            @Test
//            @DisplayName("케이스2")
//            void createUser_Normal2() {
//                // given
//                UserRequestDto userRequestDto = new UserRequestDto(
//                        username
//                        , password
//                        , name
//                        , nickname
//                );
//
//                UserValidator userValidator = new UserValidator();
//                UserService mockUserService = new UserService(
//                        userRepository,
//                        passwordEncoder,
//                        jwtTokenProvider,
//                        userValidator
//                );
//
//                // when + then
//                String succedStr = mockUserService.joinProcess(userRequestDto);
//
//                assertEquals("Success Join", succedStr);
//            }
//        }
//
//
//
//
//        @Nested
//        @DisplayName("실패 케이스")
//        class FailCases {
//            @Nested
//            @DisplayName("회원 Id")
//            class userId {
//                @Test
//                @DisplayName("빈 값")
//                void userIdIsEmpty() {
//                    username = "";
//
//                    UserRequestDto userRequestDto = new UserRequestDto(
//                            username,
//                            password,
//                            name,
//                            nickname
//                    );
//
//                    UserValidator userValidator = new UserValidator();
//                    UserService mockUserService = new UserService(
//                            userRepository,
//                            passwordEncoder,
//                            jwtTokenProvider,
//                            userValidator
//                    );
//
//                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//                        mockUserService.joinProcess(userRequestDto);
//                    });
//
//                    assertEquals("이메일은 필수 입력 값입니다", exception.getMessage());
//                }
//
//                @Test
//                @DisplayName("이메일 중복")
//                void duplicate() {
//                    username = "email@naver.com";
//
//                    UserRequestDto userRequestDto = new UserRequestDto(
//                            username,
//                            password,
//                            name,
//                            nickname
//                    );
//
//                    UserValidator userValidator = new UserValidator();
//                    UserService mockUserService = new UserService(
//                            userRepository,
//                            passwordEncoder,
//                            jwtTokenProvider,
//                            userValidator
//                    );
//
//                    User user = User.builder()
//                                .username(username)
//                                .password(password)
//                                .name(name)
//                                .nickname(nickname)
//                                .build();
//
//
//                    when(userRepository.save(user))
//                            .thenReturn(user);
//
//                    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//                        mockUserService.joinProcess(userRequestDto);
//                    });
//
//                    assertEquals("이미 존재하는 이메일입니다", exception.getMessage());
//                }
//            }
//
//            @Test
//            @DisplayName("비밀번호 빈 값")
//            void passwordIsEmpty() {
//                password = "";
//
//                UserRequestDto userRequestDto = new UserRequestDto(
//                        username,
//                        password,
//                        name,
//                        nickname
//                );
//
//                UserValidator userValidator = new UserValidator();
//                UserService mockUserService = new UserService(
//                        userRepository,
//                        passwordEncoder,
//                        jwtTokenProvider,
//                        userValidator
//                );
//
//
//                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//                    mockUserService.joinProcess(userRequestDto);
//                });
//
//                assertEquals("패스워드는 필수 입력 값입니다", exception.getMessage());
//            }
//
//            @Test
//            @DisplayName("닉네임 빈 값")
//            void nicknameIsEmpty() {
//                nickname = "";
//
//                UserRequestDto userRequestDto = new UserRequestDto(
//                        username,
//                        password,
//                        name,
//                        nickname
//                );
//
//                UserValidator userValidator = new UserValidator();
//                UserService mockUserService = new UserService(
//                        userRepository,
//                        passwordEncoder,
//                        jwtTokenProvider,
//                        userValidator
//                );
//
//
//                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//                    mockUserService.joinProcess(userRequestDto);
//                });
//
//                assertEquals("닉네임은 필수 입력 값입니다", exception.getMessage());
//            }
//
//
//            @Test
//            @DisplayName("이름 빈 값")
//            void nameIsEmpty() {
//                name = "";
//
//                UserRequestDto userRequestDto = new UserRequestDto(
//                        username,
//                        password,
//                        name,
//                        nickname
//                );
//
//                UserValidator userValidator = new UserValidator();
//                UserService mockUserService = new UserService(
//                        userRepository,
//                        passwordEncoder,
//                        jwtTokenProvider,
//                        userValidator
//                );
//
//                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//                    mockUserService.joinProcess(userRequestDto);
//                });
//
//                assertEquals("이름은 필수 입력 값입니다", exception.getMessage());
//            }
//        }
//    }
//
//
//    @Nested
//    @DisplayName("로그인 테스트")
//    class LoginTest {
//
//        @Test
//        @DisplayName("성공 케이스")
//        void LoginSuccessCase() {
//            username = "nanao@naver.com";
//            UserRequestDto userRequestDto = new UserRequestDto(
//                    username
//                    , password
//                    , name
//                    , nickname
//            );
//
//            UserValidator userValidator = new UserValidator();
//            UserService mockUserService = new UserService(
//                    userRepository,
//                    passwordEncoder,
//                    jwtTokenProvider,
//                    userValidator
//            );
//
//            MockHttpServletResponse res = new MockHttpServletResponse();
//            mockUserService.joinProcess(userRequestDto);
//            mockUserService.loginProcess(userRequestDto, res);
//
//            assertNotNull(res.getHeader("Authorization"));
//        }
//
//        @Nested
//        @DisplayName("실패 케이스")
//        class LoginFailCase {
//
//            @Test
//            @DisplayName("실패1 / 가입되지 않은 username")
//            void LoginFail1() {
//                username = "na@naver.com";
//                UserRequestDto userRequestDto = new UserRequestDto(
//                        username
//                        , password
//                        , name
//                        , nickname
//                );
//
//                UserValidator userValidator = new UserValidator();
//                UserService mockUserService = new UserService(
//                        userRepository,
//                        passwordEncoder,
//                        jwtTokenProvider,
//                        userValidator
//                );
//
//                MockHttpServletResponse res = new MockHttpServletResponse();
//
//                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//                    mockUserService.loginProcess(userRequestDto, res);
//                });
//
//                assertEquals("가입되지 않은 username 입니다.", exception.getMessage());
//            }
//
//
//            @Test
//            @DisplayName("실패2 / 잘못된 비밀번호")
//            void LoginFail2() {
//                username = "nanao@naver.com";
//                password = "qwerqwer";
//                UserRequestDto userRequestDto = new UserRequestDto(
//                        username
//                        , password
//                        , name
//                        , nickname
//                );
//
//
//                UserValidator userValidator = new UserValidator();
//                UserService mockUserService = new UserService(
//                        userRepository,
//                        passwordEncoder,
//                        jwtTokenProvider,
//                        userValidator
//                );
//
//                MockHttpServletResponse res = new MockHttpServletResponse();
//
//                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//                    mockUserService.loginProcess(userRequestDto, res);
//                });
//
//                assertEquals("잘못된 비밀번호입니다.", exception.getMessage());
//            }
//        }
//    }
//
//}