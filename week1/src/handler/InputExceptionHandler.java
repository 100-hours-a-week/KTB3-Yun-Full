package handler;

/*
입력값을 받아 parseInt로 형변환을 하는데, 이때 NumberFormatException이 발생하면
오류 메시지를 출력하고 재입력 받는 예외 처리 메서드입니다.
해당 메서드를 작성하면서 고민했던 부분이 사용자 입력을 해당 클래스에서 받을까? 하는 것이었는데
입력값은 그냥 파라미터로 넘겨 받고, '예외 처리' 하나의 기능만 수행하는 것이 좋을 것 같아 분리했습니다. (09.20)

입력을 nextInt()로 받고 InputMismatchException을 바로 처리하는 방식이 더 나았을까요?
next()로 받고 parseInt()로 num을 반환하는 게 불필요한 형변환 같다는 생각이 듭니다. (09.21)
 */
public class InputExceptionHandler {
    public int convertToInteger(String input) {
        int num = 0 ;
        try {
            num = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("유효하지 않은 입력입니다.");
        }
        return num;
    }
}
