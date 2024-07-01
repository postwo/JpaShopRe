distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-8.1.1-bin.zip
networkTimeout=10000
validateDistributionUrl=true
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists


이걸로 해야 빌드가 된다 버전이 이게 맞다

![test코드하는데 계속 gradle오류 뜨는이유.PNG](..%2F..%2FUsers%2F%EC%B5%9C%EC%B9%98%EC%96%B8%2FOneDrive%2F%EC%82%AC%EC%A7%84%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%2Ftest%EC%BD%94%EB%93%9C%ED%95%98%EB%8A%94%EB%8D%B0%20%EA%B3%84%EC%86%8D%20gradle%EC%98%A4%EB%A5%98%20%EB%9C%A8%EB%8A%94%EC%9D%B4%EC%9C%A0.PNG)

intellij로 변경안해서 test코드중 계속 에러뜨는거였다



<link rel="stylesheet" href="/css/regiestcss/regietst.css"> css넣는방법


오류
비밀번호 재확인 일치 하지 않으면 문자 띄워주기가 안됨

1.querydsl생성방법(querydsl은 jpql(=nativequery)보다 상위버전이라고 생각하면 된다)
gradle에다가 의존성을 다주입후
gradle에 들어가서 other 에서 compilejava를 누르면 q(entity)가 생성된다
![querydsl생성방법1.PNG](..%2F..%2FUsers%2F%EC%B5%9C%EC%B9%98%EC%96%B8%2FOneDrive%2F%EC%82%AC%EC%A7%84%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%2Fquerydsl%EC%83%9D%EC%84%B1%EB%B0%A9%EB%B2%951.PNG)

![Q클래스 생성.PNG](..%2F..%2FUsers%2F%EC%B5%9C%EC%B9%98%EC%96%B8%2FOneDrive%2F%EC%82%AC%EC%A7%84%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%2FQ%ED%81%B4%EB%9E%98%EC%8A%A4%20%EC%83%9D%EC%84%B1.PNG)



querydsl 생성하고 이러한 에러가 뜨면(Qentity 재생성 오류)
java: Attempt to recreate a file for type com.example.startshopping.entity.QOauth2Entity




<나중에 이방식으로 다 대체하기(중요)>
세션 대신 JWT를 사용하여 각 브라우저나 기기에서 독립적인 인증 상태를 유지할 수 있습니다.
JWT는 클라이언트 측에서 저장하고, 요청 시마다 포함하여 서버에 전송합니다.
서버는 JWT를 검증하여 사용자를 인증하고, SecurityContextHolder를 통해 인증 정보를 관리합니다.
이 방법을 통해 사용자는 각 브라우저나 기기에서 독립적인 인증 상태를 유지할 수 있으며, 다른 계정으로 로그인할 수 있습니다.


post 요청에는 시큐리티를 사용하면 꼭 프론트 단에
<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"/>
이거를 넣어준다

ajax 요청 같은경우는 밑의 방식처럼 처리할 수 있다

**<!-- Thymeleaf를 사용하여 CSRF 토큰을 설정 -->
<input type="hidden" id="_csrf" name="${_csrf.parameterName}" value="${_csrf.token}">

<script>
    // JavaScript에서 CSRF 토큰 값을 가져와서 Ajax 요청에 포함
    var csrfToken = document.getElementById("_csrf").value;

    // 예시: jQuery를 사용한 Ajax 요청
    $.ajax({
        url: '/your-endpoint',
        type: 'POST',
        beforeSend: function(xhr) {
            xhr.setRequestHeader('${_csrf.headerName}', csrfToken); // CSRF 헤더 설정
        },
        data: {
            // Ajax 요청 데이터
        },
        success: function(data) {
            // 성공적으로 요청 처리 후의 작업
        },
        error: function(xhr, status, error) {
            // 오류 발생 시 처리
        }
    });
</script>



Name for argument of type [java.lang.String] not specified, and parameter name information not available via reflection. Ensure that the compiler uses the '-parameters' flag
이렇나 에러가 뜨면 requestparam으로 명칭을똑같이 맞춰주든가 pathvariable써서 똑같이 맞춰주든가 해야한다




//url 경로
URL이 중복되어 http://localhost:8080/member/member/memberRegist와 같은 형식으로 나타나는 문제는 링크를 생성할 때 경로를 절대 경로로 지정하지 않아서 발생합니다. HTML a 태그의 href 속성에 경로를 지정할 때, 절대 경로와 상대 경로의 차이를 이해하는 것이 중요합니다.

절대 경로 vs 상대 경로
절대 경로: 웹사이트의 루트 디렉토리(/)를 기준으로 경로를 지정합니다.
예: /member/memberRegist
상대 경로: 현재 URL을 기준으로 경로를 지정합니다.
예: member/memberRegist
지금 주어진 코드에서 상대 경로를 사용하고 있기 때문에, 현재 페이지의 URL을 기준으로 경로가 해석됩니다. 따라서, /member 페이지에서 상대 경로 member/memberRegist를 사용하면, 현재 페이지 URL에 상대 경로가 추가되어 /member/member/memberRegist가 됩니다.


