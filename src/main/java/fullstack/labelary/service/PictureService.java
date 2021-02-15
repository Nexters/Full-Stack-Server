package fullstack.labelary.service;

import fullstack.labelary.domain.Label;
import fullstack.labelary.domain.Picture;
import fullstack.labelary.repository.LabelRepository;
import fullstack.labelary.repository.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PictureService {

    private final PictureRepository pictureRepository;

    /**
     * 사진 저장
     *
     * @param picture 사진 정보
     */
    @Transactional
    public void savePicture(Picture picture) {
        pictureRepository.save(picture);
    }

    /**
     * 모든 사진 조회
     *
     * @return 사진 List
     */
    public List<Picture> findPictures() {
        return pictureRepository.findAll();
    }

    /**
     * Idx 일치하는 사진 1건 조회
     *
     * @param pictureIdx 사진 Idx
     * @return 일치하는 사진
     */
    public Picture findOne(Long pictureIdx) {
        return pictureRepository.findOne(pictureIdx);
    }
}