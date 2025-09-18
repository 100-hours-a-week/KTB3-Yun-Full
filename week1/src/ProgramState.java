import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public enum ProgramState {

    ONBOARDING{
        @Override
        ProgramState handle(ProgramContext context){
            System.out.println("[사용할 기능의 번호를 선택해주세요]");
            System.out.println("-------------------------------------------------");
            System.out.println("1. 등록된 전자책 목록 열람 || 2. 전자책 상세 정보 열람 || 3. 전자책 대여 신청 || 4. 종료");
            int userChoice = context.input.nextInt();
            switch (userChoice) {
                case 1:
                    return LIST;
                case 2:
                    return DETAIL;
                case 3:
                    return RENT;
                case 4:
                    System.out.println("프로그램을 이용해주셔서 감사합니다.");
                    return EXIT;
                default:
                    return ONBOARDING; //예외 처리
            }
        }
    },
    LIST{
        @Override
        ProgramState handle(ProgramContext context){
            System.out.println("\n현재 시스템에 등록된 전자책 목록입니다.\n");
            for (int i = 0; i<ProgramContext.bookList.length; i++){
                System.out.printf("[%d] ", i + 1);
                System.out.printf("제목: %s | ", ProgramContext.bookList[i].title);
                System.out.printf("작가: %s\n", ProgramContext.bookList[i].author);
            }
            System.out.println("-------------------------------------------------");
            System.out.println("1. 전자책 상세 정보 열람 || 2. 전자책 대여 신청 || 3. 종료");
            int userChoice = context.input.nextInt();
            switch (userChoice) {
                case 1:
                    return DETAIL;
                case 2:
                    return RENT;
                case 3:
                    System.out.println("프로그램을 이용해주셔서 감사합니다.");
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
            int bookId = context.input.nextInt();
            if (bookId > ProgramContext.bookList.length || bookId < 1) {
                    System.out.println("올바른 번호를 입력해주세요.");
                    return DETAIL;
                }
            System.out.println("\n[도서 상세 정보]");
            System.out.printf("제목: %s\n", ProgramContext.bookList[bookId - 1].title);
            System.out.printf("저자: %s\n", ProgramContext.bookList[bookId - 1].author);
            System.out.printf("분류: %s\n", ProgramContext.bookList[bookId - 1].genre);
            System.out.printf("출판사: %s\n", ProgramContext.bookList[bookId - 1].publisher);
            System.out.printf("쪽수: %d\n", ProgramContext.bookList[bookId - 1].pages);
            System.out.printf("대여 가능 플랫폼: %s\n", ProgramContext.bookList[bookId - 1].platform);
            System.out.println("-------------------------------------------------");
            System.out.println("1. 전자책 대여 신청 || 2. 이전 화면 || 3. 종료");
            int userChoice = context.input.nextInt();
            switch (userChoice) {
                case 1:
                    return RENT;
                case 2:
                    return DETAIL;
                case 3:
                    System.out.println("프로그램을 이용해주셔서 감사합니다.");
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
            int bookId = context.input.nextInt();
            if (bookId > ProgramContext.bookList.length || bookId <= 0) {
                System.out.println("올바른 번호를 입력해주세요.");
                return RENT;
            }
            if (ProgramContext.bookList[bookId - 1].isAvailable == false) {
                System.out.println("현재 해당 도서는 eBook 대여가 불가능합니다.");
            } else {
                System.out.println("\n대여자 분의 성함과 전화번호를 입력해주세요.");
                System.out.println("이름: ");
                String name = context.input.next();
                System.out.println("전화번호: ");
                String phone = context.input.next();
                System.out.println("\n반납 기한은 대여 당일로부터 14일까지입니다.\n");
                LocalDateTime today = LocalDateTime.now();
                String rentalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss").format(today);
                String returnDate = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss").format(today.plusDays(14));
                System.out.println("[대여 정보]");
                System.out.printf("제목: %s\n", ProgramContext.bookList[bookId - 1].title);
                System.out.printf("저자: %s\n", ProgramContext.bookList[bookId - 1].author);
                System.out.printf("출판사: %s\n", ProgramContext.bookList[bookId - 1].publisher);
                System.out.printf("대여 플랫폼: %s\n", ProgramContext.bookList[bookId - 1].platform);
                System.out.printf("대여일: %s\n", rentalDate);
                System.out.printf("반납일: %s\n", returnDate);
                System.out.println("\n[사용자 정보]");
                System.out.printf("이름: %s\n", name);
                System.out.printf("전화번호: %s\n", phone);
            }
            System.out.println("-------------------------------------------------");
            System.out.println("1. 책 더 빌리기 || 2. 메인 화면 || 3. 종료");
            int userChoice = context.input.nextInt();
            switch (userChoice) {
                case 1:
                    return RENT;
                case 2:
                    return ONBOARDING;
                case 3:
                    System.out.println("프로그램을 이용해주셔서 감사합니다.");
                    return EXIT;
                default:
                    return ONBOARDING; //예외 처리
            }
        }
    },
    EXIT{
        @Override
        ProgramState handle(ProgramContext context){
            return this;
        }
    };

    abstract ProgramState handle(ProgramContext context);
}
