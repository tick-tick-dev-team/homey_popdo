package hello.hellospring.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

public class MemberServiceTest {

	MemberService memberService;
	MemoryMemberRepository memberRepository;
	
	// memberSerivce에서 사용하는 Repository 동일한 레포지터리 사용하도록 설정
	@BeforeEach
	public void beforeEash() {
		memberRepository = new MemoryMemberRepository();
 		memberService = new MemberService(memberRepository);
	}
	
	// 하나의 테스트가 끝날 때마다 저장소 안을 clean함
	@AfterEach
	public void afterEash() {
		memberRepository.clearStore();
	}

	@Test
	void join() {
		// given
		Member member = new Member();
		member.setName("Spring1");

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
		member1.setName("Spring1");
		
		Member member2 = new Member();
		member2.setName("Spring1");
		
		//when
		memberService.join(member1);
		
		IllegalStateException e = assertThrows(IllegalStateException.class,() -> memberService.join(member2));
		Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		
		
		/*memberService.join(member1);
		try {
			memberService.join(member2);
			fail();
		} catch (IllegalStateException e) {
			Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		}*/
		
		//then
	}

	@Test
	void findMembers() {

	}

	@Test
	void findeOne() {

	}

}
