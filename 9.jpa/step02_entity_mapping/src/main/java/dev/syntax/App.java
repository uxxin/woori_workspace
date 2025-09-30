package dev.syntax;

public class App 
{
    public static void main( String[] args )
    {

        Student 종혁 = Student.builder()
                            .id(1)
                            .name("종혁")
                            .build();
        System.out.println("종혁 = " + 종혁);

        // 종혁이에게 학과를 부여

        //학과 객체 생성
        Major 컴공 = Major.builder().name("컴공").build();

        종혁.setMajor(컴공);

        //종혁이의 학과 조회( 단방향 )
        Major major = 종혁.getMajor();
        System.out.println("major = " + major);
        // 객체 지향 프로그래밍 방식에서는 객체들 간의 연관 관계를 참조 연산자(dot, .기호)를 통해 탐색할 수 있음.
    }
}
