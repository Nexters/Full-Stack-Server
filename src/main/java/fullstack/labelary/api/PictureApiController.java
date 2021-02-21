package fullstack.labelary.api;

import fullstack.labelary.domain.Picture;
import fullstack.labelary.service.PictureService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PictureApiController {

    private final PictureService pictureService;

    /**
     * 모든 사진 조회
     *
     * @return Picture List
     */
    @GetMapping("/api/v1/pictures")
    public List<Picture> getPicturesV1() {
        return pictureService.findPictures();
    }

    /**
     * 사진 저장 V2
     *
     * @param request RequestBody 정보
     * @return Response Json 응답
     */
    @PostMapping("/api/v2/pictures")
    public PictureApiController.CreatePictureResponse savePicturesV2(@RequestBody @Valid PictureApiController.CreatePictureRequest request) {
        Picture picture = new Picture();
        picture.setOriginUrl(request.getOriginUrl());
        picture.setThumbnail(request.getThumbnail());
        picture.setPictureDt(LocalDateTime.now());

        Long idx = pictureService.savePicture(picture);
        return new PictureApiController.CreatePictureResponse(idx);
    }


    /**
     * 사진 생성 요청
     */
    @Data
    static class CreatePictureRequest {
        private LocalDateTime pictureDt;    // 사진 생성 시간
        private String thumbnail;           // 썸네일 URL
        private String originUrl;           // 원본 URL
        private Boolean bookmark;           // 즐겨찾기 유무
    }

    /**
     * 생성한 사진 정보 Response
     */
    @Data
    static class CreatePictureResponse {
        private Long pictureIdx;

        public CreatePictureResponse(Long id) {
            this.pictureIdx = id;
        }
    }


    /**
     * 사진 데이터 Response
     */
    @Data
    @AllArgsConstructor
    static class DeletePictureResponse {
        private Long pictureIdx;
    }
}