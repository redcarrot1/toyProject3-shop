# ToyProject를 진행하며 배운 내용



### modelMapper은 setter을 기준으로 매핑된다.

- 세터가 없으면 안된다..



### DB에 INSERT할 때 자동으로 시간대를 입력하고 싶다면 다음의 코드를 사용하면 된다.

```java
//초기 데이터가 들어갈때 자동으로 시간대를 입력하고, 앞으로 변경하지 않음.
@Column(nullable = false, updatable = false, insertable = false)
@ColumnDefault(value="CURRENT_TIMESTAMP") //h2 db에서 현재 시간을 자동으로 넣어주는 함수
private Date signupAt;
```





### BCryptPasswordEncoder은 같은 문자열을 매번 다르게 인코딩해준다.

- 두 문자열 비교를 위해선 `.matches( {암호되기 전}, { 암호화된 문자열 })`을 사용해야한다.

```java
public void test() {
    String rawPassword = "password";
    String encodedPassword = passwordEncoder.encode(rawPassword);
    assertThat(passwordEncoder.matches(rawPassword, encodedPassword), is(true));
}
```

