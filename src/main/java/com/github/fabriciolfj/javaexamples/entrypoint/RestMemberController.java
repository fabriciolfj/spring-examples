package com.github.fabriciolfj.javaexamples.entrypoint;

import com.github.fabriciolfj.javaexamples.entity.Member;
import com.github.fabriciolfj.javaexamples.entity.Members;
import com.github.fabriciolfj.javaexamples.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class RestMemberController {

    private final MemberService memberService;
    private final TaskExecutor taskExecutor;

    @GetMapping
    public ResponseBodyEmitter getRestMembers() {
        var emitter = new SseEmitter();
        taskExecutor.execute(() -> {
            try {
                var members = memberService.findAll().getMembers();
                for (var member : members) {
                    emitter.send(member);
                    Thread.sleep(900);
                }

                emitter.complete();;
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }

    @GetMapping("/{memberid}")
    public ResponseEntity<Member> getMember(@PathVariable("memberid") int memberId) {
        return ResponseEntity.of(memberService.findById(memberId));
    }

    @PostMapping
    public ResponseEntity<Member> save(@RequestBody @Valid final Member member) {
        return ResponseEntity.ok(memberService.save(member));
    }
}
