package com.cos.blog.test;

import com.cos.blog.constant.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

//html 파일아니라 data 를 리턴하는 controller
@RestController
@RequiredArgsConstructor
public class DummyControllerTest {

    private final UserRepository userRepository;


    //save 함수는 id를 전달하지 않으면 insert 를 해주고
    //save 함수는 id를 전달하면 id 에 대한 데이터가 있으면 update 를 해주고
    //save 함수는 id를 전달하면 해당 id 에 대한 데이터가 없으면 insert 를 한다.
    //email, password
    @Transactional //함수 종료시에 자동 commit
    @PostMapping(value = "/dummy/user/{id}")
    public User updateUser(@PathVariable("id") Long id , @RequestBody User requestUser){
        //@RequestBody json 데이터를 요청 =>Java Object(MessageConverter  의 Jackson 라이브러리가 반환해서 받아줌)
        System.out.println("id : " +id);
        System.out.println("User  : " +requestUser.toString());

        User user =userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        //userRepository.save(user);
        //
        //@Transactional
        //영속화된 user 객체의 데이터 감지, 이것을 더티체킹, 더티체킹후 함수 종료시에 자동 commit
        return  user;
    }

    @DeleteMapping(value = "/dummy/user/{id}")
    public String delete(@PathVariable("id") Long id){
        try{
            userRepository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            return "삭제에 실패하였습니다. 해당  id는 DB 에 없습니다.";
        }
        return "삭제 되었습니다." +id;
    }



    @GetMapping(value = "/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    //http://localhost:8000/blog/dummy/user/page/
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
      Page<User> pagingUser= userRepository.findAll(pageable);

      List<User> users= pagingUser.getContent();
      return users;
    }




    //http://localhost:8000/blog/dummy/join (요청)
    @PostMapping("/dummy/join")
    public String join(User user){
        user.setRole(RoleType.USER);
        System.out.println("Role 정보2 :" + RoleType.USER);
        System.out.println("user Data: " + user.toString());
        System.out.println("userRepository : " +userRepository);

        userRepository.save(user);
        return "회원 가입이 완료되었습니다77!!";
    }


    //{id} 주소로 파라미터를 전달 받을 수 있음.
    //http://localhost:8000/blog/dummy/user/3
    @GetMapping(value = "/dummy/user/{id}")
    public User detail(@PathVariable(value = "id") Long id){
        //user/4을 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될것 아냐?
        //그럼 return null 이 리턴이 되자나, 그럼 프로그램에 문제가 있지 않겠니?
        //Optional 로 너의 User 객체를 감싸서 가져올테니 null 인지 아닌지 판단해서 return 해!!
        User user =userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

//        User user=userRepository.findById(id).orElseThrow(()->{
//            return  new IllegalArgumentException("해당 사용자는 없습니다.");
//        });

//        User user =userRepository.findById(id).orElseThrow(new Supplier<
//                IllegalArgumentException >() {
//            @Override
//            public IllegalArgumentException  get() {
//                return new IllegalArgumentException("해당 유저는 없습니다. id : " +id) ;
//            }
//        });

        //요청 :웹브라우저
        //user 객체 : 자바 오브젝트
        //변환 (웹브라우저가 이해할 수 있는 데이터 -> json(Gsnon 라이브러리)
        //스프링부트 = MessageConverter 라는 애가 응답시에 자동 작동
        //만약에 자바 오브젝트를 리턴하게 되면 MesageConverter 가 Jackson 라이브러리 호출해서
        //user 오브젝트를 json 으로 변환해서 브라우저에게 던져줍니다.
        return  user;
    }



}
