package com.splash.teamsplashbackend.model;

import com.splash.teamsplashbackend.dto.photoBoard.PhotoBoardRequestDto;
import com.splash.teamsplashbackend.dto.photoBoard.PhotoBoardResponseDto;
import com.splash.teamsplashbackend.repository.LikeRepository;
import com.splash.teamsplashbackend.repository.PhotoBoardRepository;
import com.splash.teamsplashbackend.repository.TagRepository;
import com.splash.teamsplashbackend.repository.UserRepository;
import com.splash.teamsplashbackend.service.PhotoBoardService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)

@ExtendWith(MockitoExtension.class)
class PhotoBoardTest {

    @Autowired
    private PhotoBoardService photoBoardService;

    @Autowired
    private PhotoBoardRepository photoBoardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private TagRepository tagRepository;

    @Mock
    private PhotoBoardRepository mockPhotoBoardRepository;

    @Mock
    private LikeRepository mockLikeRepository;


    private String nickname;
    private String location;
    private String tagname;
    private String description;
    private String size = "medium";



    @BeforeEach
    void setup() {
        nickname = "닉네임";
        location = "로케이션";
        tagname = "태그네임";
        description = "디스크립션";

    }

    @Nested
    @Order(1)
    @DisplayName("게시글 등록")
    class UploadPhotoBoard {
        User user;

        @BeforeEach
        void CreateBoardSetUp() {
            user = User.builder()
                    .username("유저네임@google.com")
                    .password("qwer")
                    .name("이름")
                    .nickname("닉네임")
                    .build();
        }


        @Nested
        @DisplayName("게시글 등록 성공 케이스")
        class UploadPhotoBoard_success {

            @Test
            @DisplayName("정상 케이스1")
            void UploadPhotoBoard_Normal1() {
                // given
                String img = "이미지URL";
                LocalDateTime createdAt = LocalDateTime.now();
                LocalDateTime modifiedAt = LocalDateTime.now();

                // when
                PhotoBoardRequestDto photoBoardRequestDto = new PhotoBoardRequestDto(
                        nickname,
                        location,
                        tagname,
                        description,
                        size
                );

                PhotoBoard photoBoard = PhotoBoard.builder()
                        .location(photoBoardRequestDto.getLocation())
                        .tagname(photoBoardRequestDto.getTagname())
                        .description(photoBoardRequestDto.getDescription())
                        .createdAt(createdAt)
                        .modifiedAt(modifiedAt)
                        .img(img)
                        .build();


                // then
                assertNull(photoBoard.getId());
                assertEquals(img, photoBoard.getImg());
                assertEquals(location, photoBoard.getLocation());
                assertEquals(description, photoBoard.getDescription());
                assertEquals(0, photoBoard.getViews());
                assertEquals(tagname, photoBoard.getTagname());
                assertEquals(createdAt, photoBoard.getCreatedAt());
                assertEquals(modifiedAt, photoBoard.getModifiedAt());
            }

            @Test
            @DisplayName("정상 케이스2")
            void UploadPhotoBoard_Normal2() {
                PhotoBoardRequestDto photoBoardRequestDto = new PhotoBoardRequestDto(
                        nickname,
                        location,
                        tagname,
                        description,
                        size
                );

                userRepository.save(user);


                PhotoBoardService mockPhotoBoardService = new PhotoBoardService(
                        photoBoardRepository,
                        likeRepository,
                        tagRepository
                );

                MockMultipartFile mockMultipartFile = new MockMultipartFile(
                        "image1", "image1", "application/doc", "image".getBytes());
                mockPhotoBoardService.uploadPhotoPost(photoBoardRequestDto, mockMultipartFile, user);
            }
        }

        @Nested
        @DisplayName("게시글 등록 실패 케이스")
        class UploadPhotoBoard_fail {
            @Test
            @DisplayName("실패 케이스1 / 이미지 == null")
            void UploadPhotoBoard_fail1() {
                PhotoBoardRequestDto photoBoardRequestDto = new PhotoBoardRequestDto(
                        nickname,
                        location,
                        tagname,
                        description,
                        size
                );
                MockMultipartFile mockMultipartFile = null;


                Exception exception = assertThrows(NullPointerException.class, () -> {
                    photoBoardService.uploadPhotoPost(photoBoardRequestDto, mockMultipartFile, user);
                });

                assertEquals("등록하려는 게시글에 이미지가 없습니다.", exception.getMessage());
            }


            @Test
            @DisplayName("실패 케이스2 / 이미지 사이즈 == 0")
            void UploadPhotoBoard_fail2() {
                PhotoBoardRequestDto photoBoardRequestDto = new PhotoBoardRequestDto(
                        nickname,
                        location,
                        tagname,
                        description,
                        size
                );

                MockMultipartFile mockMultipartFile = new MockMultipartFile(
                        "image1", "image1", "application/doc", "".getBytes());


                Exception exception = assertThrows(NullPointerException.class, () -> {
                    photoBoardService.uploadPhotoPost(photoBoardRequestDto, mockMultipartFile, user);
                });

                assertEquals("등록하려는 게시글에 이미지가 없습니다.", exception.getMessage());
            }


            @Test
            @DisplayName("실패 케이스3 / location 빈 값")
            void UploadPhotoBoard_fail3() {
                location = "";
                PhotoBoardRequestDto photoBoardRequestDto = new PhotoBoardRequestDto(
                        nickname,
                        location,
                        tagname,
                        description,
                        size
                );

                MockMultipartFile mockMultipartFile = new MockMultipartFile(
                        "image", "image", "application/doc", "image".getBytes());


                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    photoBoardService.uploadPhotoPost(photoBoardRequestDto, mockMultipartFile, user);
                });

                assertEquals("location은 필수 입력 값입니다", exception.getMessage());
            }


            @Test
            @DisplayName("실패 케이스4 / description 빈 값")
            void UploadPhotoBoard_fail4() {
                description = "";
                PhotoBoardRequestDto photoBoardRequestDto = new PhotoBoardRequestDto(
                        nickname,
                        location,
                        tagname,
                        description,
                        size
                );


                MockMultipartFile mockMultipartFile = new MockMultipartFile(
                        "image", "image", "application/doc", "image".getBytes());


                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    photoBoardService.uploadPhotoPost(photoBoardRequestDto, mockMultipartFile, user);
                });

                assertEquals("description은 필수 입력 값입니다", exception.getMessage());
            }

            @Test
            @DisplayName("실패 케이스5 / tagname 빈 값")
            void UploadPhotoBoard_fail5() {
                tagname = "";
                PhotoBoardRequestDto photoBoardRequestDto = new PhotoBoardRequestDto(
                        nickname,
                        location,
                        tagname,
                        description,
                        size
                );


                MockMultipartFile mockMultipartFile = new MockMultipartFile(
                        "image", "image", "application/doc", "image".getBytes());


                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    photoBoardService.uploadPhotoPost(photoBoardRequestDto, mockMultipartFile, user);
                });

                assertEquals("tagname은 필수 입력 값입니다", exception.getMessage());
            }
        }
    }


    @Nested
    @Order(2)
    @DisplayName("게시글 수정")
    class EditPhotoBoard {
        User user;
        PhotoBoard photoBoard;

        @BeforeEach
        void EditPhotoBoard_setup() {
            nickname = "nickname 변경";
            location = "location 변경";
            tagname = "tagname 변경";
            description = "description 변경";


            user = User.builder()
                    .username("유저네임@naver.com")
                    .password("qwer")
                    .name("이름")
                    .nickname("닉네임")
                    .build();

            photoBoard = PhotoBoard.builder()
                    .img("이미지URL")
                    .location(location)
                    .description(tagname)
                    .tagname(description)
                    .user(user)
                    .build();
        }

        @Test
        @DisplayName("성공 케이스")
        void EditPhotoBoard_success() {
            // given

            PhotoBoardRequestDto photoBoardRequestDto = new PhotoBoardRequestDto(
                    nickname,
                    location,
                    tagname,
                    description,
                    size
            );

            userRepository.save(user);
            photoBoardRepository.save(photoBoard);
            Long boardId = photoBoard.getId();
            photoBoardService.editPhotoBoard(boardId, photoBoardRequestDto, user);

            // when
            PhotoBoard photoBoard = photoBoardRepository.findById(boardId)
                    .orElse(null);

            // then
            assertEquals(boardId, photoBoard.getId());
            assertEquals(location, photoBoard.getLocation());
            assertEquals(tagname, photoBoard.getTagname());
            assertEquals(description, photoBoard.getDescription());

        }

        @Nested
        @DisplayName("실패 케이스")
        class EditPhotoBoard_fail {

            @Test
            @DisplayName("게시글이 없습니다.")
            void EditPhotoBoard_fail() {
                Long boardId = 10000L;
                PhotoBoardRequestDto photoBoardRequestDto = new PhotoBoardRequestDto(
                        nickname,
                        location,
                        tagname,
                        description,
                        size
                );

                Exception exception = assertThrows(NullPointerException.class, () -> {
                    photoBoardService.editPhotoBoard(boardId, photoBoardRequestDto, user);
                });

                assertEquals("해당 게시글이 없습니다.", exception.getMessage());
            }

            @Test
            @DisplayName("작성자가 아니라 게시글 수정 실패")
            void EditPhotoBoard_fail2() {
                Long boardId = 2L;
                PhotoBoardRequestDto photoBoardRequestDto = new PhotoBoardRequestDto(
                        nickname,
                        location,
                        tagname,
                        description,
                        size
                );

                User user2 = User.builder().build();

                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    photoBoardService.editPhotoBoard(boardId, photoBoardRequestDto, user2);
                });

                assertEquals("작성자가 아니라 게시글을 수정 할 수 없습니다.", exception.getMessage());
            }
        }
    }


    @Nested
    @Order(3)
    @DisplayName("게시글 전체조회")
    class FindAllPhotoBoard {

        @Test
        @DisplayName("성공")
        void FinaAllPhotoBoard_success() {
            List<PhotoBoardResponseDto> photoBoardList = photoBoardService.findAll();

            assertEquals(photoBoardList.size(), photoBoardRepository.count());
        }

        @Test
        @DisplayName("실패 / 조회할 게시글이 없음")
        void FinaAllPhotoBoard_fail() {

            PhotoBoardService mockPhotoBoardService  = new PhotoBoardService(
                    mockPhotoBoardRepository,
                    mockLikeRepository,
                    tagRepository
            );

            Exception exception = assertThrows(NullPointerException.class, () -> {
                mockPhotoBoardService.findAll();
            });

            assertEquals("조회할 게시글이 없습니다.", exception.getMessage());
        }
    }


    @Nested
    @Order(4)
    @DisplayName("게시글 상세조회")
    class FindOnePhotoBoard {
        @Test
        @DisplayName("성공")
        void FindOnePhotoBoard_success() {

            User user2 = User.builder()
                    .username("user2@google.com")
                    .password("qwer")
                    .name("이름")
                    .nickname("닉네임")
                    .build();

            userRepository.save(user2);

            PhotoBoard photoBoard = PhotoBoard.builder()
                    .user(user2)
                    .img("테스트URL")
                    .description(description)
                    .location(location)
                    .tagname(tagname)
                    .size(size)
                    .build();

            photoBoardRepository.save(photoBoard);

            photoBoardService.findPhotoBoard(photoBoard.getId());
        }

        @Test
        @DisplayName("실패")
        void FindOnePhotoBoard_fail() {
            Long boardId = 10000L;

            Exception exception = assertThrows(NullPointerException.class, () -> {
                photoBoardService.findPhotoBoard(boardId);
            });

            assertEquals("찾으려는 게시글이 없습니다.", exception.getMessage());
        }
    }

    @Nested
    @Order(5)
    @DisplayName("게시글 삭제")
    class DeletePhotoPost {
        @Test
        @DisplayName("성공 케이스")
        void DeletePhotoPost_success() {

            User user = User.builder()
                    .username("user5@google.com")
                    .password("qwer")
                    .name("이름")
                    .nickname("닉네임")
                    .build();

            userRepository.save(user);
            PhotoBoard photoBoard = PhotoBoard.builder()
                    .id(1L)
                    .user(user)
                    .img("테스트URL")
                    .description(description)
                    .location(location)
                    .tagname(tagname)
                    .size(size)
                    .build();

            photoBoardRepository.save(photoBoard);
            photoBoardService.deletePhotoBoard(photoBoard.getId(), user.getId());
        }

        @Nested
        @DisplayName("실패 케이스")
        class DeletePhotoPost_fail {

            @Test
            @DisplayName("실패1 / 삭제하려는 게시글이 없습니다.")
            void DeletePhotoPost_fail1() {
                User user = User.builder()
                        .username("user@google.com")
                        .password("qwer")
                        .name("이름")
                        .nickname("닉네임")
                        .build();

                userRepository.save(user);
                PhotoBoard photoBoard = PhotoBoard.builder()
                        .id(100L)
                        .user(user)
                        .img("테스트URL")
                        .description(description)
                        .location(location)
                        .tagname(tagname)
                        .size(size)
                        .build();


                Exception exception = assertThrows(NullPointerException.class, () -> {
                    photoBoardService.deletePhotoBoard(photoBoard.getId(), user.getId());
                });
                assertEquals("삭제하려는 게시글이 없습니다.", exception.getMessage());
            }

            @Test
            @DisplayName("실패2 / 글 작성자가 아닙니다.")
            void DeletePhotoPost_fail2() {
                User user = User.builder()
                        .username("user7@google.com")
                        .password("qwer")
                        .name("이름")
                        .nickname("닉네임")
                        .build();

                userRepository.save(user);
                PhotoBoard photoBoard = PhotoBoard.builder()
                        .id(1L)
                        .user(user)
                        .img("테스트URL")
                        .description(description)
                        .location(location)
                        .tagname(tagname)
                        .size(size)
                        .build();

                photoBoardRepository.save(photoBoard);
                Exception exception = assertThrows(NullPointerException.class, () -> {
                    photoBoardService.deletePhotoBoard(photoBoard.getId(), user.getId() + 1);
                });
                assertEquals("삭제하려는 게시글이 없습니다.", exception.getMessage());
            }
        }
    }
}