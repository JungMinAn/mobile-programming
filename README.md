# mobile-programming
1. 첫번째 화면 (Relative Layout 사용) - 달성
- 앱 접속 페이지, 회원 ID/비밀번호(EditView), 로그인/회원가입(Button) - 달성
- 첫번째 화면 초기화시에 파일에서 개인정보 읽어 오기 - 회원가입 시 내부 텍스트 파일에 계정 정보 저장, 첫번째 화면 초기화 시 파일에서 읽어 와 EditView에 
  자동입력.
- ID, 비밀번호 입력 시 기존에 가입한 회원 ID, 비밀번호 체크 오류 시 에러 메시지 출력 - id없을 경우 출력, id는 맞으나 비밀번호 오류 시 출력.
- ID, 비밀번호 입력이 정상이고 로그인 버튼 클릭 시 세번째 페이지 이동 - 계정 정보 저장된 텍스트 파일 읽어 와 id, 비밀번호 모두 일치할 경우 페이지 이동.

2. 두번째 화면 (Linear Layout 사용) - 달성
- 회원가입 페이지, 첫번째 페이지에서 회원가입 버튼 클릭 시 출력 - 달성
- ID(EditView, 중복검사), 비밀번호(EditView, 자릿수/특수키 등 규칙 체크) - 첫번째 화면에서 넘어올 떄 idlist를 받아 와 id 입력 후 중복확인 버튼 클릭 시
  중복여부 검사. 비밀번호 8자리 이상 체크, 비밀번호 재확인 체크.
- 이름/전화번호/주소(EditView) - 이름, 전화번호의 경우 입력 여부 체크, 주소의 경우 필수가 아니라 판단하여 체크하지 않음.
- 개인정보 사용 동의 간략 약관(TextView), 동의 여부(Radio Button, Decline/Accept) - Radio 버튼 체크 여부 체크.
- 회원정보는 파일로 저장하고 첫번째 페이지로 이동 - 새 계정 정보를 첫 번째 화면으로 전달하여 계정 정보 파일에 추가.

3. 세번째 화면 (Constraint Layout, Table Layout, Grid Layout, Frame Layout 중 하나 사용) - Grid Layout과 Linear Layout 혼용 사용.
- 첫번째 페이지에서 ID, 비밀번호 입력 시 정상이고 로그인 버튼 클릭 시 화면 출력 - 달성
- 세번째 화면을 간단한 기능을 수행하도록 구성 (ex. 간편 계산기 등) - 간편 계산기 구현. 숫자, 수식 버튼으로 계산식을 만들어 = 버튼 클릭 시 계산식 계산.

실행 os - 리눅스 우분투 16.04, 

java -version 결과
openjdk version "1.8.0_222"
OpenJDK Runtime Environment (build 1.8.0_222-8u222-b10-1ubuntu1~16.04.1-b10)
OpenJDK 64-Bit Server VM (build 25.222-b10, mixed mode)

SDK 플랫폼
- Android 9.0
- android emulator 27.3.10
- android sdk platform-tools 28.0.1
- android sdk tools 26.1.1
