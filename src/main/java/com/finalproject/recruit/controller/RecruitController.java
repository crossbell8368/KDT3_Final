package com.finalproject.recruit.controller;

import com.finalproject.recruit.dto.Response;
import com.finalproject.recruit.dto.ResponseDTO;
import com.finalproject.recruit.dto.recruit.RecruitReq;
import com.finalproject.recruit.dto.recruit.RecruitRes;
import com.finalproject.recruit.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruit")
@RequiredArgsConstructor
public class RecruitController {
    private final RecruitService recruitService;

    /*===========================
        채용폼 목록조회
     ===========================*/
    // 채용상태 : recruit_status
    // ( true : 진행중 / false : 마감됨  )
    @GetMapping
    public ResponseDTO<List<RecruitRes>> selectAllRecruit(@RequestParam(name = "status") String recruitStatus){
        String memberId = null;
        List<RecruitRes> res = recruitService.selectALlRecruit(memberId, Boolean.parseBoolean(recruitStatus));
        return ResponseDTO.message(res);
    }

    /*===========================
        채용폼 검색
    ===========================*/
    // 채용상태 : recruit_status
    // ( true : 진행중 / false : 마감됨  )
    @GetMapping("/search")
    public ResponseDTO<List<RecruitRes>> searchRecruit(@RequestParam(name = "status") String recruitStatus,
                                                    @RequestParam(name = "title") String recruitTitle){
        String memberId = null;
        List<RecruitRes> res = recruitService.searchRecruit(memberId, Boolean.parseBoolean(recruitStatus), recruitTitle);
        return ResponseDTO.message(res);
    }

    /*===========================
        채용폼 상세조회
    ===========================*/
    @GetMapping("/{recruit_id}")
    public ResponseDTO<RecruitRes> detailRecruit(@PathVariable Long recruit_id){
        RecruitRes res = recruitService.selectRecruitDetail(recruit_id);
        return ResponseDTO.message(res);
    }

    /*===========================
        채용폼 수정
    ===========================*/
    @PutMapping("/{recruit_id}")
    public ResponseDTO<RecruitRes> editRecruit(@RequestBody RecruitReq req, @PathVariable Long recruit_id){
        RecruitRes res = recruitService.editRecruit(req, recruit_id);
        return ResponseDTO.message(res);
    }

    /*===========================
        채용폼 등록
    ===========================*/
    @PostMapping
    public ResponseDTO<RecruitRes> registRecruit(@RequestBody RecruitReq req){
        RecruitRes res = recruitService.registRecruit(req);
        return ResponseDTO.message(res);
    }

    /*===========================
        채용폼 삭제
    ===========================*/
    @PutMapping("/delete/{recruit_id}")
    public ResponseDTO<String> deleteRecruit(@PathVariable Long recruit_id){
        String res = recruitService.deleteRecruit(recruit_id);
        return ResponseDTO.message(res);
    }
}