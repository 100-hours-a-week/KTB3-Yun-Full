import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ProgramState state = ProgramState.ONBOARDING;
        ProgramContext context = new ProgramContext(input);

        System.out.println("---전자 도서 정보 열람 시스템에 오신 것을 환영합니다---");
        while (!state.equals(ProgramState.EXIT)) {
            state = state.handle(context);
        }
    }
}