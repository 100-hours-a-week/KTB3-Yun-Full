import java.util.Scanner;

public class ProgramContext {
    final Scanner input;
    static RealBook[] bookList = new RealBook[7];

    static{
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

    }

    public ProgramContext(Scanner input){
        this.input = input;
    }

}
