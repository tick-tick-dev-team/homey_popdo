package hello.hellospring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;

public class MemoryMemberRepositoryTest {
	
	MemberRepository repository = new MemoryMemberRepository();
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		
		Member result = repository.findByid(member.getId()).get();
		
		//System.out.println("result = " + ( result == member ));
		Assertions.assertThat(member).isEqualTo(result);		
	}
	
	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		Member result = repository.findByName("spring1").get();
		
		Assertions.assertThat(result).isEqualTo(member1);
		
		
	}

}
