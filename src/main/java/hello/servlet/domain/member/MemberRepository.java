package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); //static 사용
    private static long sequence = 0L; //static 사용
    private static final MemberRepository instance = new MemberRepository();//싱글톤으로 만들기
    public static MemberRepository getInstance() {
        return instance;
    }
    private MemberRepository() {//싱글톤을 만들때는 프라이빗으로 아무나 생성 못하게 막아줘야 함.
    }
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    public Member findById(Long id) {
        return store.get(id);
    }
    public List<Member> findAll() {
        return new ArrayList<>(store.values());//어레이리스트에 뭘 넣거나 조작해도 스토어의 값을 건들지 않게 하기 위해서 이렇게 처리
    }
    public void clearStore() {
        store.clear();
    }
}