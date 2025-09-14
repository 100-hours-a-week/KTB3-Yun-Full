import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        LocalDateTime today = LocalDateTime.now();
        String rentalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss").format(today);
        String returnDate = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss").format(today.plusDays(14));


        /*
        프로그램 자체의 데이터베이스 느낌을 생각했습니다.
        이것을 어떻게 분리할지는 잘 떠오르지 않아 배열에 객체를 담아두는 형식으로 구현했습니다.
        */
        RealBook[] bookList = new RealBook[7];

        bookList[0] = new RealBook("호의에 대하여(무엇이 우리를 살아가게 하는가)", "문형배", "국내 에세이",
                "김영사", 408, "교보eBook", true);
        bookList[1] = new RealBook("절창", "구병모", "국내 소설",
                "문학동네", 352, "교보eBook", true);
        bookList[2] = new RealBook("월요일 수요일 토요일", "페트라 펠리니", "해외 소설",
                "북파머스", 376, "밀리의 서재", true);
        bookList[3] = new RealBook("세상은 실제로 어떻게 돌아가는가", "바츨라프 스밀", "인문교양",
                "김영사", 472, "대구광역시립 두류도서관", false);
        bookList[4] = new RealBook("트렌드 코리아 2026", "김난도, 전미영 외 3인", "경제전망",
                "미래의창", 513, "교보eBook", true);
        bookList[5] = new RealBook("렛뎀 이론", "멜 로빈스", "자기 계발",
                "비즈니스북스", 445, "밀리의 서재", true);
        bookList[6] = new RealBook("다정하지 못했던 모든 때에", "전윤철", "국내 에세이",
                "부크크", 102, "교보eBook", false);

        System.out.println("---전자 도서 정보 열람 시스템에 오신 것을 환영합니다---");
        System.out.println("[사용할 기능의 번호를 선택해주세요]");
        while (true) {
            System.out.println("-------------------------------------------------");
            //각 기능을 함수로 구현해 분리할 수 있을 것 같다는 생각을 했는데, 이것 역시 잘 떠오르진 않았습니다..
            System.out.println("1. 등록된 전자책 목록 열람 || 2. 전자책 상세 정보 열람 || 3. 전자책 대여 신청 || 4. 종료");
            int menu = input.nextInt();
            if (menu == 4) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (menu == 1) {
                while(true) {
                    System.out.println("\n현재 시스템에 등록된 전자책 목록입니다.\n");
                    for (int i = 0; i < bookList.length; i++) {
                        System.out.printf("[%d] ", i + 1);
                        System.out.printf("제목: %s | ", bookList[i].title);
                        System.out.printf("작가: %s\n", bookList[i].author);
                    }
                    System.out.println("\n뒤로 가시려면 0을 입력해주세요.");
                    int back = input.nextInt();
                    if (back == 0) {
                        break;
                    }
                }
            } else if (menu == 2) {
                while(true) {
                    System.out.println("\n상세 정보를 열람할 책의 번호를 입력해주세요.");
                    System.out.println("뒤로 가시려면 0을 입력해주세요.");
                    int bookInfo = input.nextInt();
                    if (bookInfo == 0) {
                        break;
                    } else {
                        if (bookInfo > bookList.length || bookInfo < 1) {
                            System.out.println("올바른 번호를 입력해주세요.");
                            continue;
                        }
                        System.out.println("\n[도서 상세 정보]");
                        System.out.printf("제목: %s\n", bookList[bookInfo - 1].title);
                        System.out.printf("저자: %s\n", bookList[bookInfo - 1].author);
                        System.out.printf("분류: %s\n", bookList[bookInfo - 1].genre);
                        System.out.printf("출판사: %s\n", bookList[bookInfo - 1].publisher);
                        System.out.printf("쪽수: %d\n", bookList[bookInfo - 1].pages);
                        System.out.printf("대여 가능 플랫폼: %s\n", bookList[bookInfo - 1].platform);
                    }
                }
            } else if (menu == 3) {
                while(true) {
                    System.out.println("\n대여하려는 책의 번호를 입력해주세요.");
                    System.out.println("뒤로 가시려면 0을 입력해주세요.");
                    int id = input.nextInt();
                    if (id == 0) {
                        break;
                    }
                    if (id > bookList.length || id < 0) {
                        System.out.println("올바른 번호를 입력해주세요.");
                        continue;
                    }
                    if (bookList[id - 1].isAvailable == false) {
                        System.out.println("현재 해당 도서는 eBook 대여가 불가능합니다.");
                    } else {
                        System.out.println("\n대여자 분의 성함과 전화번호를 입력해주세요.");
                        System.out.println("이름: ");
                        String name = input.next();
                        System.out.println("전화번호: ");
                        String phone = input.next();
                        System.out.println("\n반납 기한은 대여 당일로부터 14일까지입니다.\n");
                        System.out.println("[대여 정보]");
                        System.out.printf("제목: %s\n", bookList[id - 1].title);
                        System.out.printf("저자: %s\n", bookList[id - 1].author);
                        System.out.printf("출판사: %s\n", bookList[id - 1].publisher);
                        System.out.printf("대여 플랫폼: %s\n", bookList[id - 1].platform);
                        System.out.printf("대여일: %s\n", rentalDate);
                        System.out.printf("반납일: %s\n", returnDate);
                        System.out.println("\n[사용자 정보]");
                        System.out.printf("이름: %s\n", name);
                        System.out.printf("전화번호: %s\n", phone);
                    }
                }
            } else {
                System.out.println("올바른 번호를 입력해주세요.");
            }
        }
    }
}