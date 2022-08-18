package hello.hellospring.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;


@SpringBootTest
@Transactional	// 테스트 실행시 트랜잭션을 실행하고 테스트 실행 후에는 해당 트랜잭션을 rollback 함
public class MemberServiceIntegrationTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;


	@Test
	void join() {
		// given
		Member member = new Member();
		member.setName("spring100");

		// when
		Long saveId = memberService.join(member);

		// then
		Member findMember = memberService.findOne(saveId).get();
		Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

	}

	@Test
	public void 중복_회원_예외() {
		//given
		Member member1 = new Member();
		member1.setName("spring1");
		
		Member member2 = new Member();
		member2.setName("spring2");
		
		//when
		memberService.join(member1);
		
		IllegalStateException e = assertThrows(IllegalStateException.class,() -> memberService.join(member2));
		Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		
		
	}


}
