//package fullstack.labelary.controller;
//
//import fullstack.labelary.domain.Picture;
//import fullstack.labelary.dto.picture.CreatePictureRequest;
//import fullstack.labelary.dto.picture.CreatePictureResponse;
//import fullstack.labelary.dto.picture.DeletePictureResponse;
//import fullstack.labelary.service.PictureService;
//import io.swagger.v3.oas.annotations.Operation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class PictureApiController {
//
//    private final PictureService pictureService;
//
//    /**
//     * 사진 저장
//     *
//     * @param request RequestBody 정보
//     * @return Response Json 응답
//     */
//    @Operation(summary = "[C] 사진 저장 API")
//    @PostMapping("/api/v1/pictures")
//    public CreatePictureResponse savePicturesV2(@RequestBody @Valid CreatePictureRequest request) {
//        Picture picture = new Picture();
//        picture.setOriginUrl(request.getOriginUrl());
//        picture.setThumbnail(request.getThumbnail());
//        picture.setPictureDt(LocalDateTime.now());
//
//        Long idx = pictureService.savePicture(picture);
//        return new CreatePictureResponse(idx);
//    }
//
//    /**
//     * 사진 정보 조회
//     *
//     * @param id 사진 idx
//     * @return Picture 정보
//     */
//    @Operation(summary = "[R] 사진 조회 API")
//    @GetMapping("/api/v1/pictures/{id}")
//    public Picture getPictureForIdx(@PathVariable("id") String id) {
//        return pictureService.findOne(Long.valueOf(id));
//    }
//
//    /**
//     * 사진 삭제
//     *
//     * @param idx 삭제할 사진 idx
//     * @return 삭제된 사진 idx
//     */
//    @Operation(summary = "[D] 사진 삭제 API")
//    @DeleteMapping("api/v1/pictures/{idx}")
//    public DeletePictureResponse deletePicture(@PathVariable("idx") Long idx) {
//        pictureService.deletePicture(idx);
//        return new DeletePictureResponse(idx);
//    }
//
//    /**
//     * 모든 사진 조회
//     *
//     * @return Picture List
//     */
//    @GetMapping("/api/v1/pictures")
//    public List<Picture> getPicturesV1() {
//        return pictureService.findPictures();
//    }
//
//}