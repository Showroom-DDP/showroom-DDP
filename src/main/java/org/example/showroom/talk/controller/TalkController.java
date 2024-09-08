
package org.example.showroom.talk.controller;

import org.example.showroom.openfeign.AiConnection;
import org.example.showroom.openfeign.ChatAnswerDTO;
import org.example.showroom.openfeign.ChatQuestDTO;
import org.example.showroom.talk.dto.TalkRequestDto;
import org.example.showroom.talk.dto.TalkResponseDto;
import org.example.showroom.talk.service.TalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/talks")
public class TalkController {

    private final TalkService talkService;
    private final AiConnection aiConnection;
    @Autowired
    public TalkController(TalkService talkService, AiConnection aiConnection) {
        this.talkService = talkService;
        this.aiConnection = aiConnection;
    }

    @PostMapping
    public ResponseEntity<TalkResponseDto> createTalk(@RequestBody TalkRequestDto talkRequestDto,
                                                      @AuthenticationPrincipal UserDetails userDetails) {
        // 현재 인증된 사용자의 username 가져오기
        String email = userDetails.getUsername();

        // 사용자의 정보를 사용하여 대화 저장 로직 처리
        TalkResponseDto savedTalk = talkService.saveTalk(talkRequestDto, email);
        return ResponseEntity.ok(savedTalk);
    }
    @PostMapping("aitest")
    public TalkResponseDto getTest5(@RequestBody TalkRequestDto talkRequestDto){
        System.out.println(talkRequestDto);

        TalkResponseDto answer = aiConnection.postSomeData(talkRequestDto);
        System.out.println(answer);
        return answer;
    }

    @GetMapping
    public ResponseEntity<List<TalkResponseDto>> getAllTalks() {
        List<TalkResponseDto> talks = talkService.getAllTalks();
        return ResponseEntity.ok(talks);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTalk(@PathVariable Long id) {
        talkService.deleteTalk(id);
        return ResponseEntity.noContent().build();
    }
}
