# 사용 방법

- GET 방식 매개변수 구성 예시 : URL?변수=변수값1&변수2=...

Open APi 
- 일반 검색
- 상세 정보 검색

샘플 URL

"토지"를 검색할 경우    
`:https://www.nl.go.kr/NL/search/openApi/search.do?key=[발급된키값]&apiType=xml&srchTarget=total&kwd=토지&pageSize=10&pageName=1&category=&sort=`

반드시 URL을 한글로 인코딩해야한다.UTF-8

response에서 필요한 정보
- title_info
- author_info
- pub_info
- pub_year_info
- image_url
- kdc_code_1s
- kdc_name_1s
- media_name


고민해보기

서지정보 api를 사용하는 이유가 무엇인가?
- 도서관 사서가 책을 등록할 때, isbn을 일일이 검색하여 입력하는 것의 불편함을 해소시켜주고자 
- 책 이름과 작가만 주었을 경우 결과값이 너무 방대함. 
- 이름, 작가, 출판사까지 입력해야 결과값이 많이 줄어듦
- 이미지도 함께 제공해주고 싶은데, api 반환값에 비어있는 경우가 다수
- 발행년도에 따라 isbn 값이 달라짐
```json
{
      "titleInfo": "(개정 16종 국어 교과서 전 작품을 실은) 고등중장편소설 30. 상, 하",
      "typeName": "도서",
      "placeInfo": "어린이청소년도서관(역삼동) 연구자료실(서고자료, 3층)",
      "authorInfo": "지은이: 최인훈 외 ;엮은이: 김형주,박찬영 ;그린이: 아이완",
      "pubInfo": "<span class=\"searching_txt\">리베르</span>스쿨",
      "menuName": "오프라인자료",
      "mediaName": "인쇄자료(책자형)",
      "manageName": "어린이청소년도서관",
      "pubYearInfo": "2011",
      "controlNo": "KJU201200528",
      "docYn": "N",
      "orgLink": "",
      "id": "144404780",
      "typeCode": "B1",
      "licYn": "N",
      "licText": "[관외이용]-무료",
      "regDate": "20120229",
      "detailLink": "/NL/contents/search.do#viewKey=144404780&viewType=AH1",
      "isbn": "9788965820031 9788965820086 9788965820093",
      "callNo": "아813.6-12-5-1-2",
      "kdcCode1s": "8",
      "kdcName1s": "문학",
      "classNo": "813.6",
      "imageUrl": ""
    }
```
결과값을 받아와서 어떤 데이터를 사용할지, 사용하지 않을지? 

저장할 필요는 없을 것 같은데 같은 요청이 여러번 가지 않도록 구현해보고 싶음

결과값에 예상과는 다르개 여러 개가 있는 경우가 있어서 isbn을 검색해보니 조금 세분화된 책들이 존재했음
예

```json
"titleInfo": "이것이 자바다 : 교육 현장에서 가장 많이 쓰이는 JAVA 프로그래밍 기본서",
    "isbn": "9791169212298 9791169212274 9791169212281",
```
다음 책의 isbn의 결과가 3개 이는 다음과 같이 세분화 되엇음
- [종이책] 1. 이것이 자바다 2권
- [종이책] 2. 이것이 자바다(3판)
- [종이책] 3. 이것이 자바다 1권
이럴 경우 어떻게 처리해주고 싶은가 ?