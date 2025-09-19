public enum ProgramState {

    /*
    enum 내에 abstract method를 정의하고, 각 상태 별로 해당 메서드를 오버라이딩해 사용하게끔 구현했습니다.
    다만, 각 기능들을 따로 분리해 상태마다 메서드를 호출하는 게 나을지 고민 중이고
    만약 그 방법이 더 낫다면 어떻게 구현을 해야 할지 또한 고민 중입니다. (09.18)

    BookService를 분리해 각 동작을 내부 메서드로 구현하고,
    ProgramState의 오버라이딩된 handle 메서드 내에서 호출하게끔 구조를 변경했습니다.
    다만 걸리는 것은
    1. BookService를 static final로 선언한 점
    2. BookService 객체를 생성할 때 new 키워드를 사용해 ProgramContext와 Scanner 객체를 주입하는 점
    이렇게 두 개입니다.
    적절하지 않은 구조와 메서드 사용 방식인 것 같은데,
    AI를 최대한 사용하지 않고 생각해보는 과정에서 예상보다 많은 시간이 들었습니다.

    위 고민을 하다가 ProgramContext에 선언되었던 Scanner 객체를 BookService로 옮겼고,
    해당 클래스에는 책 정보가 담긴 배열(bookList)만 존재합니다.
    남은 고민은 main과 ProgramState에 ProgramContext 객체가 중복으로(?) 넘겨지는 구조를 어떻게 리팩토링할까입니다.
    (09.19)
     */
    ONBOARDING{
        @Override
        ProgramState handle(ProgramContext context){
            bookService.programOnboarding();
            int userChoice = bookService.input.nextInt();
            switch (userChoice) {
                case 1:
                    return LIST;
                case 2:
                    return DETAIL;
                case 3:
                    return RENT;
                case 4:
                    System.out.println("\n프로그램을 이용해주셔서 감사합니다.");
                    return EXIT;
                default:
                    return ONBOARDING; //예외 처리
            }
        }
    },
    LIST{
        @Override
        ProgramState handle(ProgramContext context){
            bookService.showBookList();
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("1. 전자책 상세 정보 열람 || 2. 전자책 대여 신청 || 3. 종료");
            int userChoice = bookService.input.nextInt();
            switch (userChoice) {
                case 1:
                    return DETAIL;
                case 2:
                    return RENT;
                case 3:
                    System.out.println("\n프로그램을 이용해주셔서 감사합니다.");
                    return EXIT;
                default:
                    return ONBOARDING; //예외 처리
            }
        }
    },
    DETAIL{
        @Override
        ProgramState handle(ProgramContext context){
            System.out.println("\n상세 정보를 열람할 책의 번호를 입력해주세요.");
            int bookId = bookService.input.nextInt();
            if (bookId > ProgramContext.bookList.length || bookId < 1) {
                    System.out.println("올바른 번호를 입력해주세요.");
                    return DETAIL;
                }
            bookService.showBookDetails(bookId);
            System.out.println("-------------------------------------------------");
            System.out.println("1. 전자책 대여 신청 || 2. 이전 화면 || 3. 종료");
            int userChoice = bookService.input.nextInt();
            switch (userChoice) {
                case 1:
                    return RENT;
                case 2:
                    return DETAIL;
                case 3:
                    System.out.println("\n프로그램을 이용해주셔서 감사합니다.");
                    return EXIT;
                default:
                    return ONBOARDING; //예외 처리
            }
        }
    },
    RENT{
        @Override
        ProgramState handle(ProgramContext context){
            System.out.println("\n대여하려는 책의 번호를 입력해주세요.");
            int bookId = bookService.input.nextInt();
            if (bookId > ProgramContext.bookList.length || bookId <= 0) {
                System.out.println("올바른 번호를 입력해주세요.");
                return RENT;
            }
            bookService.rentBook(bookId);
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("1. 책 더 빌리기 || 2. 메인 화면 || 3. 종료");
            int userChoice = bookService.input.nextInt();
            switch (userChoice) {
                case 1:
                    return RENT;
                case 2:
                    return ONBOARDING;
                case 3:
                    System.out.println("\n프로그램을 이용해주셔서 감사합니다.");
                    return EXIT;
                default:
                    return ONBOARDING; //예외 처리
            }
        }
    },
    EXIT{
        @Override
        ProgramState handle(ProgramContext context){
            return bookService.exitProgram();
        }
    };

    abstract ProgramState handle(ProgramContext context);
    static final BookService bookService = new BookService(new ProgramContext());
}
