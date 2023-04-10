### Q. entity와 model은 왜 나뉘어 있나요?
PostRequest와 Post가 유사하게 생겼는데 클라이언트로부터 Post객체 그대로 요청 받아서 Post를 그대로 저장하면 편하고 관리할 클래스도 적어지지 않을까요?
### A. Entity와 DTO를 구분하기 위함입니다.
우선 Entity와 DTO의 정의부터 살펴보겠습니다.

**Entity**는 DB 테이블에 존재하는 컬럼들을 필드로 가지는 객체를 뜻합니다. (즉, Entity는 DB 테이블 설계와 동일한 모습을 하고있겠죠.) 
Entity는 DB 테이블과 1:1 대응이며, 테이블을 가지지 않는 컬럼을 필드로 갖지 않습니다. 

**DTO**는 Data Transfer Object의 준말로 데이터가 계층을 오갈 때(transfer) 사용하는 객체입니다. DTO는 로직을 갖지 않는 순수한 데이터 객체이며,
getter/setter 메서드만을 가집니다. 

Entity와 DTO를 구분지어 사용하는 이유는 '각 레이어간의 의존도를 줄이기 위함'입니다. 더 쉽게 예시를 들어보면 아래와 같습니다.
1. Post와 Tag를 각각 저장하도록 테이블을 별도로 설계했다고 해봅시다.
   * 현재 우리 코드에서는 Post에 Tag를 같이 저장하도록 했지만, Tag의 내용이 많아 Post와 Tag를 각각 저장하도록 설계를 변경했습니다.
     Post와 Tag의 테이블은 분리했지만 보통 우리가 게시글을 작성할 때 태그도 함께 작성하니 Request Body도 Post와 Tag를 한 번에 받아야 좋겠죠.
     Post 클래스 하나로 요청과 DB 저장을 모두 처리했다면 위와 같은 상황 대처가 어려웠을텐데 Post와 PostRequest가 나뉘어 있으니 요청은 현재 PostRequest가 받던대로 게시글 내용과 Tag를 함께 받고
     저장할 때 Post와 Tag 엔티티로 저장하면 위와 같은 상황에 대처가 가능해집니다.
2. 기획자가 게시글 작성과 관련한 기획을 변경한 상황을 가정해봅시다.
   * 기능의 요구사항은 언제든 추가/변경/삭제가 생깁니다. 그럼 요청을 받아야하는 객체의 필드에도 추가/변경/삭제가 언제든 발생할 수 있습니다.
     PostRequest가 없었다면, Post 클래스의 필드가 수시로 변경되어 DB에 데이터를 저장할 때 장애가 발생하기 쉽습니다. (없는 필드를 저장하려고 한다던지 등등)

---

### Q. WebMVCConfig 파일은 왜 있나요?
### A. CORS을 허용하기 위해서입니다.
http://localhost:8080 이라는 url이 있다고 합시다.

Origin은 host부분인 localhost와 포트인 8080을 모두 합친 것을 말합니다. http://localhost:9090은 포트가 다르니 Origin이 같다고 할 수 없죠. 
서버가 이미 http://localhost:8080에서 돌고 있으니, 프론트 프로그램은 8080포트가 아닌 다른 포트(예를들면 9090)에서 돌게 됩니다. 
브라우저는 보안상의 이유로 SOP(Same Origin Policy) 정책을 요구하고 있습니다. 프론트에서 api를 통해 정보를 얻어왔지만, 
브라우저가 정보를 요청한 쪽(http://localhost:9090)과 정보를 준 쪽(http://localhost:8080)의 Origin이 달라 서버로부터 받아온 응답을 거절합니다.

하지만 위처럼 서로 다른 Origin끼리 데이터를 주고 받아야하는 상황이 발생하기 때문에 별도의 예외 사항을 두었습니다. CORS가 바로 이 예외상황에 해당합니다.
CORS(Cross Origin Resource Sharing)는 다른 Origin으로 요청을 보내기 위해 지켜야하는 정책으로, 원래대로라면 SOP에 의해 막히게 될 요청을 풀어주는 정책입니다.
WebMvcConfig에서 CORS를 위해 추가한 파일이라고 알아주시면 될 것 같습니다!(프론트 수업을 위해 추가한 설정파일) - ❗️프론트엔드 수업하려면 해당 파일이 필수이니 프론트엔드 수업 전까지 반드시 pull하기❗️

글솜씨가 없어 간단히 적었지만, 토이프로젝트를 하다보면 정말 자주 마주치는 문제 중 하나가 CORS입니다!!! 기본적인 지식이기도 하니 시간되실 때 아래의 블로그를 읽어보셔도 좋을 것 같습니다

📌 https://it-eldorado.tistory.com/163

realcoding..
