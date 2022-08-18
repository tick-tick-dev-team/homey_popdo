package hello.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;

@Configuration
public class SpringConfig {
	
/*	Jdbc 
 * 	private DataSource dataSource;
	
	public SpringConfig(DataSource dataSource){
	    this.dataSource = dataSource;
	}
	*/
	
/*	JPA
 *  private EntityManager em;
	
	@Autowired
	public SpringConfig(EntityManager em) {
		this.em = em;
	}
*/	
	
	private final MemberRepository memberRepository;
	
	@Autowired
	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}
	
/*	@Bean
	public TimeTraceAop timeTraceAop() {
		return new TimeTraceAop();
	}*/
	
/*	@Bean
	public MemberRepository memberRepository() {
		// return new MemoryMemberRepository();  기존 메모리
		// return new JdbcMemberRepository(dataSource); jdbc 
		// return new JdbcTemplateMemberRepository(dataSource); JdbcTemplate
		return new JpaMemberRepository(em); JPA
	}*/
	
	/**
	 * 
	 * */
}
