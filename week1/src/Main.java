/*
main 메서드에서 ProgramContext 인스턴스를 ProgramState의 handle 메서드에 파라미터로 넘기는 방식인데,
ProgramState에서 BookService를 사용하고 있는 방식이 옳은가 의구심이 듭니다.
굳이 동일한 클래스의 인스턴스를 두 번 넣어줘야 하나...? 하는 의문이 생겼습니다.
구조가 무엇인가 잘못되었다는 느낌을 지울 수가 없는데 정확히 무엇이 잘못되었는지는 감이 당최 오지 않습니다...(09.19)
 */
public class Main {
    public static void main(String[] args) {
        ProgramState state = ProgramState.ONBOARDING;
        ProgramContext context = new ProgramContext();

        System.out.println("---전자 도서 정보 열람 시스템에 오신 것을 환영합니다---");
        while (!state.equals(ProgramState.EXIT)) {
            state = state.handle(context);
        }
    }
}