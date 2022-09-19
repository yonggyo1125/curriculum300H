# 서비스

- 앱이 실행되어 있다고 해서 항상 화면이 보이는 것은 아닙니다. 예를 들어, 카카오톡은 앱이 실행되어있지 않거나 화면이 사용자에게 보이지 않는 상태에서도 다른 사람이 보낸 메시지를 받을 수 있습니다. 왜 그럴까요? 화면 없이 백그라운드에서 실행되는 서비스(Service)가 있기 때문입니다. 이때 화면 뒤의공간을 뒷단 또는 백그라운드라고 부릅니다. 지금부터는 백그라운드라는 말을 많이 사용하겠습니다.

- 서비스란 백그라운드에서 실행되는 앱의 구성 요소를 말합니다. 이미 만들어보았던 액티비티는 앱의구성 요소라고 불리는데 서비스도 동일하게 앱의 구성 요소 역할을 합니다. 서비스도 앱의 구성 요소이므로 시스템에서 관리합니다. 그래서 액티비티를 만들 때 매니페스트 파일에 등록했던 것처럼 새로 만든 서비스도 매니페스트 파일에 꼭 등록해야 합니다

## 서비스의 실행 원리와 역할

- 그러면 서비스는 어떻게 실행될까요? 그리고 앱에서 어떤 역할을 할까요? 서비스를 실행하려면 메인 액티비티에서 startService 메서드를 호출하면 됩니다. 서비스의 주요 역할 중 하나는 단말이 항상 실행되어 있는 상태로 다른 단말과 데이터를 주고받거나 필요한 기능을 백그라운드에서 실행하는 것입니다. 그래서 서비스는 실행된 상태를 계속 유지하기 위해 서비스가 비정상적으로 종료되더라도 시스템이 자동으로 재실행합니다. 다음 그림을 통해 이해해 보세요.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/4.%20%EC%84%9C%EB%B9%84%EC%8A%A4/images/image1.png)

- 서비스를 시작시키기 위해 startService 메서드를 호출할 때는 인텐트 객체를 파라미터로 전달합니다.이 인텐트 객체는 어떤 서비스를 실행할 것인지에 대한 정보를 담고 있으며 시스템은 서비스를 시작시킨 후 인텐트 객체를 서비스에 전달합니다.

- 그런데 서비스가 실행 중이면 실행 이후에 startService 메서드를 여러 번 호출해도 서비스는 이미 메모리에 만들어진 상태로 유지됩니다. 따라서 startService 메서드는 서비스를 시작하는 목적 이외에 인﻿텐트를 전달하는 목적으로도 자주 사용됩니다. 예를 들어, 액티비티에서 서비스로 데이터를 전달하려면 인텐트 객체를 만들고 부가 데이터(Extra data)를 넣은 후 startService 메서드를 호출하면서 전달하면 됩니다. 그런데 앞에서 가정하고 있는 상태는 서비스가 startService 메서드에 의하여 메모리에 만들어져 있는 상태입니다. 이런 경우에는 시스템이 onCreate 메서드가 아니라 onStartCommand 메서드를 실행합니다. onStartCommand 메서드는 서비스로 전달된 인텐트 객체를 처리하는 메서드입니다. 일단 서비스의 실행 원리와 역할은 여기까지만 설명하겠습니다. 나머지는 실습을 진행하며 자세히 설명하겠습니다.

- 서비스가 동작하는 방식을 이해하기 위해 새로운 프로젝트를 만들고 그 안에 서비스 클래스를 정의하여 실습해 보겠습니다. 안드로이드 스튜디오에서 새로운 SampleService 프로젝트를 만듭니다. 프로젝트를 만들 때 패키지 이름은 org.koreait.service로 입력합니다. 프로젝트가 만들어지면 왼쪽 프로젝트 창에서 app 폴더를 선택하고 마우스 오른쪽 버튼을 누릅니다. 팝업 메뉴가 보이면 [New → Service → Service] 메뉴를 선택합니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/4.%20%EC%84%9C%EB%B9%84%EC%8A%A4/images/image2.png)

- 그러면 새로운 서비스를 만들 수 있는 대화상자가 표시됩니다. Class Name: 입력란에는 디폴트 값으로 MyService가 입력되어 있습니다. 입력된 값을 그대로 두고 아래쪽 [Finish] 버튼을 누릅니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/4.%20%EC%84%9C%EB%B9%84%EC%8A%A4/images/image3.png)

﻿- 새로운 서비스가 만들어지면 MyService.java 파일이 만들어지는 것뿐만 아니라 AndroidManifest.xml 파일 안에 \<service\> 태그도 추가됩니다. 앞에서도 설명했지만 서비스는 시스템에서 관리하므로 매니페스트에 넣어 앱 설치 시에 시스템이 알 수 있게 해야 합니다. MyService.java 파일에는 자동으로 만들어진 생성자 메서드와 onBind 메서드만 있습니다. 하지만 서비스의 수명주기를 관리하기 위하여 onCreate, onDestroy 메서드와 인텐트 객체를 전달받기 위한 onStartCommand 메서드를 추가하겠습니다.

- MyService 클래스 안에 마우스 커서를 둔 상태로 마우스 오른쪽 버튼을 누릅니다. 팝업 메뉴가 보이면 [Generate → Override Methods] 메뉴를 선택합니다. 부모 클래스의 메서드를 재정의할 수 있는 대화상자가 표시되면 [Ctrl] 을 누른 상태로 Create, onDestroy, onStartCommand 메서드를 선택하고  [OK] 버튼을 누릅니다.

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/6~7%EC%9D%BC%EC%B0%A8(6h)%20-%20%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8%2C%20%EC%95%A1%EC%85%98%EB%B0%94%2C%20%EB%B7%B0%ED%8E%98%EC%9D%B4%EC%A0%80%20%EB%93%B1/4.%20%EC%84%9C%EB%B9%84%EC%8A%A4/images/image4.png)

